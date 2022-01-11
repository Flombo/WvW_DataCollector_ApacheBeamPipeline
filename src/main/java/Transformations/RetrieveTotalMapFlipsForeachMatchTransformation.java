package Transformations;

import Models.Match;
import Models.Objective;
import Models.WVWMap;
import org.apache.beam.sdk.transforms.DoFn;
import java.util.HashMap;
import java.util.List;

/***
 * Retrieves the flips foreach Match.
 * The transformation checks if the old lastFlip is not equal to the current-lastFlip foreach map.
 */
public class RetrieveTotalMapFlipsForeachMatchTransformation extends DoFn<Match, HashMap<Integer, Long>>
{

    private static String lastFlip;

    @ProcessElement
    public void processElement(@Element Match input, OutputReceiver<HashMap<Integer, Long>> out)
    {
        HashMap<Integer, Long> totalFlipAmountForeachMap = new HashMap<>();

        for (WVWMap map : input.getMaps()) {
            long totalMapFlipAmount = getTotalFlipsPerObjective(map.getObjectives());
            totalFlipAmountForeachMap.put(map.getId(), totalMapFlipAmount);
        }

        out.output(totalFlipAmountForeachMap);

    }

    private long getTotalFlipsPerObjective(List<Objective> objectives) {

        long totalMapFlipAmount = 0;

        for (Objective objective : objectives) {

            String currentLastFlipped = objective.getLastFlipped();

            if(lastFlip != null) {
                if(!lastFlip.equals(currentLastFlipped)) {
                    totalMapFlipAmount++;
                }
            }
            lastFlip = currentLastFlipped;
        }

        return totalMapFlipAmount;
    }

}
