package Transformations;

import TransformationModels.ObjectiveFlip;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.transforms.DoFn;

public class PrintKV extends DoFn<KV<String, Iterable<ObjectiveFlip>>, String>{

    @ProcessElement
    public void processElement(@Element KV<String, Iterable<ObjectiveFlip>> input, OutputReceiver<String> outputReceiver) {
        System.out.println(input.getKey() + " : " + input.getValue());
        outputReceiver.output(input.getKey());
    }

}
