import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.PipelineResult;
import org.apache.beam.sdk.io.kafka.KafkaIO;
import org.apache.beam.sdk.io.kafka.KafkaRecord;
import org.apache.beam.sdk.schemas.AutoValueSchema;
import org.apache.beam.sdk.schemas.Schema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;
import org.apache.beam.sdk.schemas.transforms.Convert;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.JsonToRow;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.Row;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.joda.time.Duration;
import org.apache.beam.sdk.extensions.jackson.*;

public class Main {

    public static void main(String[] args) {

        try {
            Pipeline pipeline = Pipeline.create();

            pipeline.getSchemaRegistry().registerPOJO(Match.class);
            Schema s = pipeline.getSchemaRegistry().getSchema(Match.class);

            PCollection<Match> data = pipeline.apply(
                    KafkaIO.<Long, String>read()
                            .withBootstrapServers("localhost:9092")
                            .withTopic("2-1")
                            .withKeyDeserializer(LongDeserializer.class)
                            .withValueDeserializer(StringDeserializer.class)
            ).apply(ParDo.of(new ParseJsonToString()))
                    .apply(JsonToRow.withSchema(s))
                    .apply(Convert.to(Match.class));


            PCollection<String> output;

            data.apply(ParDo.of(new Print()));


            PipelineResult run = pipeline.run();

            run.waitUntilFinish(Duration.standardSeconds(-1));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static class Print extends DoFn<Match, String>
    {
        @ProcessElement
        public void processElement(@Element PCollection<Match> input, OutputReceiver<String> out)
        {
            System.out.println(input.toString());
            out.output(input.toString());
        }
    }

    public static class ParseJsonToString extends DoFn<KafkaRecord<Long,String>, String>
    {
        @ProcessElement
        public void processElement(@Element KafkaRecord<Long,String> input, OutputReceiver<String>out)
        {
            input.getKV().getValue();
            out.output(input.getKV().getValue());

        }
    }



}
