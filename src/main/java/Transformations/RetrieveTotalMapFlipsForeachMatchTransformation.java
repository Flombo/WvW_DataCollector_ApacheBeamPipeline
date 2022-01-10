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

        for (WVWMap map : input.maps) {
            long totalMapFlipAmount = getTotalFlipsPerObjective(map.objectives);
            totalFlipAmountForeachMap.put(map.id, totalMapFlipAmount);
        }

        out.output(totalFlipAmountForeachMap);

    }

    private long getTotalFlipsPerObjective(List<Objective> objectives) {

        long totalMapFlipAmount = 0;

        for (Objective objective : objectives) {
            if(lastFlip != null) {
                if(!lastFlip.equals(objective.lastFlipped)) {
                    totalMapFlipAmount++;
                }
            }
            lastFlip = objective.lastFlipped;
        }

        return totalMapFlipAmount;
    }

}
