package Transformations;

import ResultModels.TotalFlip;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;

public class CombineKVTotalFlipsTransformation extends DoFn<KV<String, Iterable<TotalFlip>>, TotalFlip> {

    @ProcessElement
    public void processElement(@Element KV<String, Iterable<TotalFlip>> input, OutputReceiver<TotalFlip> outputReceiver) {
        TotalFlip ultimateTotalFlip = new TotalFlip();
        long flips = 0;

        for (TotalFlip totalFlip : input.getValue()) {

            flips += totalFlip.getTotalFlips();
            ultimateTotalFlip.setTimestamp(totalFlip.getTimestamp());
            ultimateTotalFlip.setStarttime(totalFlip.getStarttime());
            ultimateTotalFlip.setEndtime(totalFlip.getEndtime());

        }

        ultimateTotalFlip.setTotalFlips(flips);
        ultimateTotalFlip.setMapname(input.getKey());

        outputReceiver.output(ultimateTotalFlip);
    }

}
