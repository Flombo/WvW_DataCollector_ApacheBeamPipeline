import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.PipelineResult;
import org.apache.beam.sdk.coders.SerializableCoder;
import org.apache.beam.sdk.io.kafka.KafkaIO;
import org.apache.beam.sdk.io.kafka.KafkaRecord;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.beam.sdk.extensions.jackson.*;

public class Main
{

    public static void main(String[] args)
    {

        try
        {
            Pipeline pipeline = Pipeline.create();

            /*
            Read from Kafka over KafkaIO.
             */
            PCollection<KafkaRecord<Long, String>> data = pipeline.apply(
                    KafkaIO.<Long, String>read()
                            .withBootstrapServers("localhost:9092")
                            .withTopic(args[0])
                            .withKeyDeserializer(LongDeserializer.class)
                            .withValueDeserializer(StringDeserializer.class)
            );

            /*
             * Extract the string values from the KafkaRecord for further processing.
             * The strings are needed, because only they hold the json data.
            */
            PCollection<String> extractedJSONStrings = data.apply(ParDo.of(new RetrieveStringFromKafkaRecord()));

            /*
             * Parses json-string to the class Match for oop-like data access.
             * PTransform needs coder for deserializing the string.
             */
            PCollection<Match> matches = extractedJSONStrings
                    .apply(ParseJsons.of(Match.class))
                    .setCoder(SerializableCoder.of(Match.class));

            matches.apply(ParDo.of(new PrintMatchCollection()));


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

    public static class PrintMatchCollection extends DoFn<Match, Match>
    {
        @ProcessElement
        public void processElement(@Element Match input, OutputReceiver<Match> out) {
            System.out.println(input.id);
            out.output(input);
        }
    }

    public static class PrintStringCollection extends DoFn<String, String>
    {
        @ProcessElement
        public void processElement(@Element String input, OutputReceiver<String> out)
        {
            System.out.println(input);
            out.output(input);
        }
    }

    /*
    Extracts the string value of the given KafkaRecord.
    Only the string value containing the json data will be further processed.
     */
    public static class RetrieveStringFromKafkaRecord extends DoFn<KafkaRecord<Long,String>, String>
    {
        @ProcessElement
        public void processElement(@Element KafkaRecord<Long,String> input, OutputReceiver<String>out)
        {
            out.output(input.getKV().getValue());

        }
    }



}
