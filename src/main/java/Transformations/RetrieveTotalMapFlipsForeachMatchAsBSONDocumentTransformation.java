package Transformations;

import TransformationModels.TotalFlip;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;
import org.bson.Document;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/***
 * Retrieves the flips foreach Match in a BSONDocument.
 * The transformation checks if the old lastFlip is not equal to the current-lastFlip foreach map.
 * Format:
 * {
 *     totalflip : [{
 *          mapname : '',
 *          flips : 100
 *     }],
 *     timestamp : '',
 *     starttime : '',
 *     endtime : ''
 * }
 */
public class RetrieveTotalMapFlipsForeachMatchAsBSONDocumentTransformation extends DoFn<KV<String, Iterable<KV<String, Iterable<TotalFlip>>>>, Document>
{

    @ProcessElement
    public void processElement(@Element KV<String, Iterable<KV<String, Iterable<TotalFlip>>>> input, OutputReceiver<Document> out)
    {
        Document document = new Document();

        Iterable<KV<String, Iterable<TotalFlip>>> totalFlipsGroupedByMapName = input.getValue();

        String timestamp = "";
        String endtime = "";
        String starttime = "";

        ArrayList<HashMap<String, Object>> totalFlips = new ArrayList<>();

        for (KV<String, Iterable<TotalFlip>> totalFlipGroupedByMapName : Objects.requireNonNull(totalFlipsGroupedByMapName)) {

            long flips = 0;

            Iterable<TotalFlip> totalFlipsOfMap = totalFlipGroupedByMapName.getValue();

            for (TotalFlip totalFlipByMapName : Objects.requireNonNull(totalFlipsOfMap)) {
                flips += totalFlipByMapName.getTotalFlips();
                timestamp = totalFlipByMapName.getTimestamp();
                endtime = totalFlipByMapName.getEndtime();
                starttime = totalFlipByMapName.getStarttime();
            }


            HashMap<String, Object> totalFlipsAttributesHashMap = new HashMap<>();
            totalFlipsAttributesHashMap.put("mapname", totalFlipGroupedByMapName.getKey());
            totalFlipsAttributesHashMap.put("flips", flips);

            totalFlips.add(totalFlipsAttributesHashMap);
        }

        document.put("totalflips", totalFlips);
        document.put("timestamp", timestamp);
        document.put("starttime", starttime);
        document.put("endtime", endtime);

        out.output(document);

    }

}
