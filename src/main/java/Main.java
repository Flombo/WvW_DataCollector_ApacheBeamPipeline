import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.PipelineResult;
import org.apache.beam.sdk.io.kafka.KafkaIO;
import org.apache.beam.sdk.io.kafka.KafkaRecord;
import org.apache.beam.sdk.values.PCollection;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.joda.time.Duration;

public class Main {

    public static void main(String[] args) {

        try {
            Pipeline pipeline = Pipeline.create();

            PCollection<KafkaRecord<String, String>> data = pipeline.apply(
                    KafkaIO.<String, String>read()
                            .withBootstrapServers("localhost:9092")
                            .withTopic("2-1")
                            .withKeyDeserializer(StringDeserializer.class)
                            .withValueDeserializer(StringDeserializer.class)
            );

            System.out.println(data.toString());

            PipelineResult run = pipeline.run();

            run.waitUntilFinish(Duration.standardSeconds(-1));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
