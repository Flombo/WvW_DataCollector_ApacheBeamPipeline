import Models.Match;
import Transformations.*;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.PipelineResult;
import org.apache.beam.sdk.coders.SerializableCoder;
import org.apache.beam.sdk.extensions.jackson.ParseJsons;
import org.apache.beam.sdk.io.kafka.KafkaIO;
import org.apache.beam.sdk.io.kafka.KafkaRecord;
import org.apache.beam.sdk.io.mongodb.MongoDbIO;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.windowing.*;
import org.apache.beam.sdk.values.PCollection;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.bson.Document;
import org.joda.time.Duration;

public class Main
{

    public static void main(String[] args)
    {

        try
        {
            String topicName = args[0];
            Pipeline pipeline = Pipeline.create();

            /*
            Read from Kafka over KafkaIO.
             */
            PCollection<KafkaRecord<Long, String>> data = pipeline.apply(
                    KafkaIO.<Long, String>read()
                            .withBootstrapServers("localhost:9092")
                            .withTopic(topicName)
                            .withKeyDeserializer(LongDeserializer.class)
                            .withValueDeserializer(StringDeserializer.class)
            );

            /*
             * Extract the string values from the KafkaRecord for further processing.
             * The strings are needed, because only they hold the json data.
            */
            PCollection<String> extractedJSONStrings = data.apply(ParDo.of(new RetrieveStringFromKafkaRecordTransformation()));

            /*
             * Parses json-string to the class Models.Match for oop-like data access.
             * PTransform needs coder for deserializing the string.
             */
            PCollection<Match> matches = extractedJSONStrings
                    .apply(ParseJsons.of(Match.class))
                    .setCoder(SerializableCoder.of(Match.class));

            //Matches in SlidingWindow with trigger that should be triggered when 2 Matches are reached or 2min have passed.
            PCollection<Match> slidingWindowedMatches = matches.apply(
                    Window.<Match>configure()
                            .discardingFiredPanes()
                            .triggering(
                                    Repeatedly.forever(
                                            AfterFirst.of(
                                                    AfterPane.elementCountAtLeast(2),
                                                    AfterProcessingTime
                                                            .pastFirstElementInPane()
                                                            .plusDelayOf(Duration.standardMinutes(2))
                                            )
                                    )
                            )
            );

            PCollection<Document> totalFlipsDocuments = slidingWindowedMatches.apply(ParDo.of(new RetrieveTotalMapFlipsForeachMatchAsBSONDocumentTransformation()));

            PCollection<Document> populationPerWorld = matches.apply(ParDo.of(new ExtractPopulationAsBSONDocument()));

            PCollection<Document> victoryMetrics = matches.apply(ParDo.of(new RetrieveVictoryMetricsAsBSONDocumentForMatch()));

            PCollection<Document> bloodlustBuffs = matches.apply(ParDo.of(new GetCurrentBonusesAsBSONDocument()));

            //write totalFlipsDocuments into MongoDB totalflips-collection.
            totalFlipsDocuments.apply(
                    MongoDbIO.write()
                            .withUri("mongodb://141.28.73.145:27017")
                            .withDatabase(topicName)
                            .withCollection("totalflips")
            );

            populationPerWorld.apply(
                    MongoDbIO.write()
                            .withUri("mongodb://141.28.73.145:27017")
                            .withDatabase(topicName)
                            .withCollection("peaktime")
            );

            victoryMetrics.apply(
                    MongoDbIO.write()
                            .withUri("mongodb://141.28.73.145:27017")
                            .withDatabase(topicName)
                            .withCollection("victorymetrics")
            );

            bloodlustBuffs.apply(
                    MongoDbIO.write()
                            .withUri("mongodb://141.28.73.145:27017")
                            .withDatabase(topicName)
                            .withCollection("mapbonuses")
            );

            //Pipeline could crash due to exceptions while deserializing.
            try
            {
                PipelineResult pipelineResult = pipeline.run();
                pipelineResult.waitUntilFinish();
            }
            catch (Exception exc)
            {
                exc.printStackTrace();
            }

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

}
