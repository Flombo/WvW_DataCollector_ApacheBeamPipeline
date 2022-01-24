package Transformations;

import TransformationModels.TotalFlipsTransformationModel;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.transforms.DoFn;

public class PrintKV extends DoFn<KV<String, Iterable<TotalFlipsTransformationModel>>, String>{

    @ProcessElement
    public void processElement(@Element KV<String, Iterable<TotalFlipsTransformationModel>> input, OutputReceiver<String> outputReceiver) {
        System.out.println(input.getKey() + " : " + input.getValue());
        outputReceiver.output(input.getKey());
    }

}
