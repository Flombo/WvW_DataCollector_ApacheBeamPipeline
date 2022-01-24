package TransformationModels;

import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.transforms.DoFn;

public class PrintTotalFlipsKV extends DoFn<KV<String, TotalFlipsTransformationModel>, String> {

    @ProcessElement
    public void processElement(@Element KV<String, TotalFlipsTransformationModel> input, OutputReceiver<String> outputReceiver) {
        System.out.println(input.getKey() + " / " + input.getValue());
        outputReceiver.output(input.getKey());
    }

}
