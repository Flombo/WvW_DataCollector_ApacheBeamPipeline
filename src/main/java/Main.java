import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.PipelineResult;
import org.apache.beam.sdk.io.kafka.KafkaIO;
import org.apache.beam.sdk.io.kafka.KafkaRecord;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.joda.time.Duration;

public class Main {

    public static void main(String[] args) {

        try {
            Pipeline pipeline = Pipeline.create();

            PCollection<KafkaRecord<Long, String>> data = pipeline.apply(
                    KafkaIO.<Long, String>read()
                            .withBootstrapServers("localhost:9092")
                            .withTopic("2-1")
                            .withKeyDeserializer(LongDeserializer.class)
                            .withValueDeserializer(StringDeserializer.class)
            );


            PCollection<String> output;
            output = data.apply(ParDo.of(new Print()));


            PipelineResult run = pipeline.run();

            run.waitUntilFinish(Duration.standardSeconds(-1));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static class Print extends DoFn<KafkaRecord<Long,String>, String>
    {
        @ProcessElement
        public void processElement(@Element KafkaRecord<Long,String> input, OutputReceiver<String>out)
        {
            System.out.println(input.getKV().getValue());
            out.output(input.getKV().getValue());
        }
    }

}
