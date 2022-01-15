package Transformations;

import Models.Match;
import org.apache.beam.sdk.transforms.DoFn;
import org.bson.Document;
import java.util.HashMap;

/**
 * Returns BSON-Document in this format:
 * {
 *     red : {
 *         victorypoints : 0,
 *         population : {
 *             name : '',
 *             population: 5
 *         }
 *     },
 *     blue: {..},
 *     green: {..},
 *     timestamp : ''
 * }
 */
public class RetrieveVictoryMetricsAsBSONDocumentForMatch extends DoFn<Match, Document>
{
    @ProcessElement
    public void processElement(@Element Match match, OutputReceiver<Document> outputReceiver) {
        Document document = new Document();
        HashMap<String, HashMap<String, Object>> victoryMetrics = new HashMap<>();
        HashMap<String, Object> populationAttributes = new HashMap<>();

        HashMap<String, Object> victoryMetricsRed = new HashMap<>();
        victoryMetricsRed.put("victorypoints", match.getVictoryPoints().getRed());
        populationAttributes.put("name", match.getWorlds().getRed().getName());
        populationAttributes.put("population", match.getWorlds().getRed().getPopulation().getNumericValue());
        victoryMetricsRed.put("population", populationAttributes.clone());
        populationAttributes.clear();

        HashMap<String, Object> victoryMetricsBlue = new HashMap<>();
        victoryMetricsBlue.put("victorypoints", match.getVictoryPoints().getBlue());
        populationAttributes.put("name", match.getWorlds().getBlue().getName());
        populationAttributes.put("population", match.getWorlds().getBlue().getPopulation().getNumericValue());
        victoryMetricsBlue.put("population", populationAttributes.clone());
        populationAttributes.clear();

        HashMap<String, Object> victoryMetricsGreen = new HashMap<>();
        victoryMetricsGreen.put("victorypoints", match.getVictoryPoints().getGreen());
        populationAttributes.put("name", match.getWorlds().getGreen().getName());
        populationAttributes.put("population", match.getWorlds().getGreen().getPopulation().getNumericValue());
        victoryMetricsGreen.put("population", populationAttributes.clone());
        populationAttributes.clear();

        victoryMetrics.put("red", victoryMetricsRed);
        victoryMetrics.put("blue", victoryMetricsBlue);
        victoryMetrics.put("green", victoryMetricsGreen);

        document.put("victorymetrics", victoryMetrics);
        document.put("timestamp", RetrieveProcessingTimestampHelper.retrieveTimestamp());

        outputReceiver.output(document);
    }
}