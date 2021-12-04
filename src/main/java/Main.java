import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.PipelineResult;
import org.apache.beam.sdk.io.kafka.KafkaIO;
import org.apache.beam.sdk.options.*;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.Values;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.joda.time.Duration;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class Main {


    /**
     * Specific pipeline options.
     */
    private interface Options extends PipelineOptions {
        @Description("Kafka Bootstrap Servers")
        @Default.String("localhost:9092")
        String getKafkaServer();

        void setKafkaServer(String value);

        @Description("Kafka Topic Name")
        @Default.String("2-1")
        String getInputTopic();

        void setInputTopic(String value);

        @Description("Kafka Output Topic Name")
        @Default.String("2-2")
        String getOutputTopic();

        void setOutputTopic(String value);

        @Description("Pipeline duration to wait until finish in seconds")
        @Default.Long(-1)
        Long getDuration();

        void setDuration(Long duration);

        class GDELTFileFactory implements DefaultValueFactory<String> {
            public String create(PipelineOptions options) {
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                return format.format(new Date());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(args);
        Options options = PipelineOptionsFactory.fromArgs(args)
                .withValidation().as(Options.class);

        System.out.println(options.toString());
        Pipeline pipeline = Pipeline.create(options);

        // now we connect to the queue and process every event
        PCollection<String> data = pipeline.apply(
                "ReadFromKafka",
                KafkaIO.<String, String> read()
                        .withBootstrapServers(options.getKafkaServer())
                        .withTopics(
                                Collections.singletonList(options
                                        .getInputTopic()))
                        .withoutMetadata()).apply("ExtractPayload",
                Values.<String> create());

        data.apply(ParDo.of(new DoFn<String, String>() {
            @ProcessElement
            public void processElement(ProcessContext c) {
                System.out.println(String.format("** element |%s| **",
                        c.element()));
            }
        }));
        // We filter the events for a given country (IN=India) and send them to their own Topic
        final String country = "IN";
        PCollection<String> eventsInIndia = data.apply("FilterByCountry",
                ParDo.of(new DoFn<String, String>() {
                    @ProcessElement
                    public void processElement(ProcessContext c) {
                        //                    if (getCountry(c.element()).equals(country)){
                        c.output(c.element());
                        //                    }

                    }
                }));

        PCollection<KV<String, String>> eventsInIndiaKV = eventsInIndia
                .apply("ExtractPayload",
                        ParDo.of(new DoFn<String, KV<String, String>>() {
                            @ProcessElement
                            public void processElement(ProcessContext c)
                                    throws Exception {
                                c.output(KV.of("india", c.element()));
                            }
                        }));

        eventsInIndiaKV
                .apply("WriteToKafka",
                        KafkaIO.<String, String> write()
                                .withBootstrapServers(
                                        options.getKafkaServer())
                                .withTopic(options.getOutputTopic())
                                .withKeySerializer(
                                        org.apache.kafka.common.serialization.StringSerializer.class)
                                .withValueSerializer(
                                        org.apache.kafka.common.serialization.StringSerializer.class));
        PipelineResult run = pipeline.run();
        run.waitUntilFinish(Duration.standardSeconds(options.getDuration()));
    }

}
