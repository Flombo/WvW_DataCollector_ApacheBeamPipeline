package Transformations;

import Models.Match;
import Models.Objective;
import Models.ResultModels.TotalFlip;
import Models.WVWMap;
import org.apache.beam.sdk.transforms.DoFn;
import java.util.LinkedList;
import java.util.List;

/***
 * Retrieves the flips foreach Match.
 * The transformation checks if the old lastFlip is not equal to the current-lastFlip foreach map.
 */
public class RetrieveTotalMapFlipsForeachMatchTransformation extends DoFn<Match, List<TotalFlip>>
{

    private static String lastFlip;

    @ProcessElement
    public void processElement(@Element Match input, OutputReceiver<List<TotalFlip>> out)
    {
        LinkedList<TotalFlip> totalFlips = new LinkedList<>();

        for (WVWMap map : input.getMaps()) {
            long totalMapFlipAmount = getTotalFlipsPerObjective(map.getObjectives());
            TotalFlip totalFlip = new TotalFlip(map.getName(), totalMapFlipAmount);
            totalFlips.add(totalFlip);
        }

        out.output(totalFlips);

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
