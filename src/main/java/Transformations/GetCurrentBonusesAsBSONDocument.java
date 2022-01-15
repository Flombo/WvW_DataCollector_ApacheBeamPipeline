package Transformations;

import Models.Bonus;
import Models.Match;
import Models.WVWMap;
import org.apache.beam.sdk.transforms.DoFn;
import org.bson.Document;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Returns CurrentBonuses as BSON-Document :
 * {
 *     centermap : {
 *         name : '',
 *         bonuses : [
 *              {
 *                  type : 'Bloodlusbuff',
 *                  owner : 'red'
 *              }
 *         ]
 *     },
 *     redmap : {..},
 *     bluemap : {..},
 *     greenmap : {..},
 *     timestamp : ''
 * }
 */
public class GetCurrentBonusesAsBSONDocument extends DoFn<Match, Document>
{
    @ProcessElement
    public void ProcessElement(@Element Match input, OutputReceiver<Document> out)
    {
        Document document = new Document();
        List<WVWMap> maps = input.getMaps();

        HashMap<String, HashMap<String, Object>> mapbonuses = new HashMap<>();

        HashMap<String, Object> centerMap = new HashMap<>();
        centerMap.put("name", maps.get(0).getName());
        centerMap.put("bonuses", buildBonusList(maps.get(0).getBonuses()));

        HashMap<String, Object> redMap = new HashMap<>();
        redMap.put("name", maps.get(1).getName());
        redMap.put("bonuses", buildBonusList(maps.get(1).getBonuses()));

        HashMap<String, Object> blueMap = new HashMap<>();
        blueMap.put("name", maps.get(2).getName());
        blueMap.put("bonuses", buildBonusList(maps.get(2).getBonuses()));

        HashMap<String, Object> greenMap = new HashMap<>();
        greenMap.put("name", maps.get(3).getName());
        greenMap.put("bonuses", buildBonusList(maps.get(3).getBonuses()));

        mapbonuses.put("centermap", centerMap);
        mapbonuses.put("redmap", redMap);
        mapbonuses.put("bluemap", blueMap);
        mapbonuses.put("greenmap", greenMap);

        HashMap<String, List<Bonus>> result = new HashMap<>();

        WVWMap Center = maps.get(0);
        WVWMap RedHome = maps.get(1);
        WVWMap BlueHome = maps.get(2);
        WVWMap GreenHome = maps.get(3);

        result.put(Center.getName(), Center.getBonuses());
        result.put(RedHome.getName(), RedHome.getBonuses());
        result.put(BlueHome.getName(), BlueHome.getBonuses());
        result.put(GreenHome.getName(), GreenHome.getBonuses());

        document.putAll(mapbonuses);
        document.put("timestamp", RetrieveProcessingTimestampHelper.retrieveTimestamp());

        out.output(document);

    }

    private List<HashMap<String, String>> buildBonusList(List<Bonus> bonuses) {
        List<HashMap<String, String>> bonusList = new ArrayList<>();

        for (Bonus bonus : bonuses) {
            HashMap<String, String> bonusHashMap = new HashMap<>();
            bonusHashMap.put("type", bonus.getType());
            bonusHashMap.put("owner", bonus.getOwner());
            bonusList.add(bonusHashMap);
        }

        return bonusList;
    }

}
