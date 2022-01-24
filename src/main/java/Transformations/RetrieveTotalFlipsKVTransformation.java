package Transformations;

import ResultModels.TotalFlip;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;

public class RetrieveTotalFlipsKVTransformation extends DoFn<TotalFlip, KV<String, TotalFlip>> {

    @ProcessElement
    public void processElement(@Element TotalFlip input, OutputReceiver<KV<String, TotalFlip>> outputReceiver) {
        outputReceiver.output(KV.of(input.getMapname(), input));
    }

}
