package Transformations;

import ResultModels.TotalFlip;
import TransformationModels.ObjectiveFlip;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;
import java.util.Objects;

public class RetrieveTotalFlipsFromGroupedKVTransformation extends DoFn<KV<String, Iterable<ObjectiveFlip>>, TotalFlip> {

    @ProcessElement
    public void processElement(@Element KV<String, Iterable<ObjectiveFlip>> input, OutputReceiver<TotalFlip> outputReceiver) {
        String currentOwner = null;
        long flips = 0;

        TotalFlip totalFlip = new TotalFlip();

        for(ObjectiveFlip objectiveFlip : Objects.requireNonNull(input.getValue())) {

            if(currentOwner != null) {
                if(!currentOwner.equals(objectiveFlip.getOwner())) {
                    flips++;
                }
            }

            totalFlip.setTotalFlips(flips);
            totalFlip.setMapname(objectiveFlip.getMap().replaceAll(" ", ""));
            totalFlip.setEndtime(objectiveFlip.getEndtime());
            totalFlip.setStarttime(objectiveFlip.getStarttime());
            totalFlip.setTimestamp(objectiveFlip.getTimestamp());

            currentOwner = objectiveFlip.getOwner();

        }

        outputReceiver.output(totalFlip);

    }

}
