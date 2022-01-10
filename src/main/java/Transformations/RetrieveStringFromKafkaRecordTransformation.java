package Transformations;

import org.apache.beam.sdk.io.kafka.KafkaRecord;
import org.apache.beam.sdk.transforms.DoFn;

/**
 * Extracts the string value of the given KafkaRecord.
 * Only the string value containing the json data will be further processed.
 */
public class RetrieveStringFromKafkaRecordTransformation extends DoFn<KafkaRecord<Long,String>, String>
{
        @DoFn.ProcessElement
        public void processElement(@DoFn.Element KafkaRecord<Long,String> input, OutputReceiver<String>out)
        {
                out.output(input.getKV().getValue());
        }
}