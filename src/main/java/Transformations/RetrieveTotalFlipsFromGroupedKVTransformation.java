package Transformations;

import ResultModels.TotalFlip;
import TransformationModels.TotalFlipsTransformationModel;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;

import java.util.Objects;

public class RetrieveTotalFlipsFromGroupedKVTransformation extends DoFn<KV<String, Iterable<TotalFlipsTransformationModel>>, TotalFlip> {

    @ProcessElement
    public void processElement(@Element KV<String, Iterable<TotalFlipsTransformationModel>> input, OutputReceiver<TotalFlip> outputReceiver) {
        TotalFlip totalFlip = new TotalFlip();
        String currentOwner = null;
        long flips = 0;

        for(TotalFlipsTransformationModel totalFlipsTransformationModel : Objects.requireNonNull(input.getValue())) {

            if(currentOwner != null) {
                if(!currentOwner.equals(totalFlipsTransformationModel.getOwner())) {
                    flips++;
                }
            }

            totalFlip.setTotalFlips(flips);
            totalFlip.setMapname(totalFlipsTransformationModel.getMap());
            totalFlip.setEndtime(totalFlipsTransformationModel.getEndtime());
            totalFlip.setStarttime(totalFlipsTransformationModel.getStarttime());
            totalFlip.setTimestamp(totalFlipsTransformationModel.getTimestamp());

            currentOwner = totalFlipsTransformationModel.getOwner();
        }

        outputReceiver.output(totalFlip);

    }

}
