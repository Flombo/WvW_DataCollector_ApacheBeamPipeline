package Transformations;

import Models.ResultModels.TotalFlip;
import org.apache.beam.sdk.transforms.DoFn;
import org.bson.Document;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/***
 * Returns BSONDocument for given TotalFlips.
 * TotalFlip-attributes have to be extracted and put into an ArrayList, because the MongoDBClient can't handle a List of objects.
 */
public class RetrieveBSONDocumentFromTotalFlipsTransformation extends DoFn<List<TotalFlip>, Document> {

    @ProcessElement
    public void processElement(@Element List<TotalFlip> totalFlips, OutputReceiver<Document> outputReceiver) {
        Document document = new Document();
        ArrayList<HashMap<String, Object>> totalFlipsList = new ArrayList<>();

        for (TotalFlip totalFlip : totalFlips) {
            HashMap<String, Object> totalFlipsAttributesHashMap = new HashMap<>();
            totalFlipsAttributesHashMap.put("mapname", totalFlip.getMapName());
            totalFlipsAttributesHashMap.put("flips", totalFlip.getTotalFlips());
            totalFlipsList.add(totalFlipsAttributesHashMap);
        }

        document.put("totalflips", totalFlipsList);
        outputReceiver.output(document);
    }

}
