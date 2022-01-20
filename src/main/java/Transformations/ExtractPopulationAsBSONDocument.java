package Transformations;

import Models.Match;
import Models.WorldContainer;
import org.apache.beam.sdk.transforms.DoFn;
import org.bson.Document;
import java.util.HashMap;

/**
 * Format:
 * {
 *     red : {
 *         name : '',
 *         population : 1
 *     },
 *     blue : {},
 *     green : {},
 *     timestamp : '',
 *     starttime : '',
 *     endtime : ''
 * }
 */
public class ExtractPopulationAsBSONDocument extends DoFn<Match, Document>
{

    @ProcessElement
    public void processElement(@Element Match input, OutputReceiver<Document> out)
    {
        Document document = new Document();
        WorldContainer worlds = input.getWorlds();

        HashMap<String, HashMap<String, Object>> populationMap = new HashMap<>();

        HashMap<String, Object> populationRed = new HashMap<>();
        populationRed.put("name", worlds.getRed().getName());
        populationRed.put("population", worlds.getRed().getPopulation().getNumericValue());

        HashMap<String, Object> populationBlue = new HashMap<>();
        populationBlue.put("name", worlds.getBlue().getName());
        populationBlue.put("population", worlds.getBlue().getPopulation().getNumericValue());

        HashMap<String, Object> populationGreen = new HashMap<>();
        populationGreen.put("name", worlds.getGreen().getName());
        populationGreen.put("population", worlds.getGreen().getPopulation().getNumericValue());

        populationMap.put("red", populationRed);
        populationMap.put("blue", populationBlue);
        populationMap.put("green", populationGreen);

        document.putAll(populationMap);
        document.put("timestamp", input.getTimestamp());
        document.put("starttime", input.getStartTime());
        document.put("endtime", input.getEndTime());

        out.output(document);

    }



}
