package Transformations;

import Models.Match;
import Models.Objective;
import Models.WVWMap;
import org.apache.beam.sdk.transforms.DoFn;
import org.bson.Document;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/***
 * Retrieves the flips foreach Match in a BSONDocument.
 * The transformation checks if the old lastFlip is not equal to the current-lastFlip foreach map.
 * Format:
 * {
 *     totalflips : [{
 *          mapname : '',
 *          flips : 100
 *     }],
 *     timestamp : ''
 * }
 */
public class RetrieveTotalMapFlipsForeachMatchAsBSONDocumentTransformation extends DoFn<Match, Document>
{

    private static String lastFlip;

    @ProcessElement
    public void processElement(@Element Match input, OutputReceiver<Document> out)
    {
        Document document = new Document();
        ArrayList<HashMap<String, Object>> totalFlipsList = new ArrayList<>();

        for (WVWMap map : input.getMaps()) {
            long totalMapFlipAmount = getTotalFlipsPerObjective(map.getObjectives());
            HashMap<String, Object> totalFlipsAttributesHashMap = new HashMap<>();
            totalFlipsAttributesHashMap.put("mapname", map.getName());
            totalFlipsAttributesHashMap.put("flips", totalMapFlipAmount);
            totalFlipsList.add(totalFlipsAttributesHashMap);
        }

        document.put("totalflips", totalFlipsList);
        document.put("timestamp", RetrieveProcessingTimestampHelper.retrieveTimestamp());

        out.output(document);

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
