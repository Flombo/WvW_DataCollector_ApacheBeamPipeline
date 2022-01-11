package Transformations;

import Models.Match;
import Models.Population;
import Models.ResultModels.VictoryMetric;
import org.apache.beam.sdk.transforms.DoFn;
import java.util.LinkedList;
import java.util.List;

public class RetrieveVictoryMetricsForMatch extends DoFn<Match, List<VictoryMetric>>
{
    @ProcessElement
    public void processElement(@Element Match match, OutputReceiver<List<VictoryMetric>> outputReceiver) {
        List<VictoryMetric> victoryMetrics = new LinkedList<>();

        long victoryPointsRed = match.getVictoryPoints().getRed();
        long victoryPointsBlue = match.getVictoryPoints().getBlue();
        long victoryPointsGreen = match.getVictoryPoints().getGreen();

        Population populationRed = match.getWorlds().getRed().getPopulation();
        Population populationBlue = match.getWorlds().getBlue().getPopulation();
        Population populationGreen = match.getWorlds().getGreen().getPopulation();

        VictoryMetric victoryMetricRed = new VictoryMetric();
        VictoryMetric victoryMetricBlue = new VictoryMetric();
        VictoryMetric victoryMetricGreen = new VictoryMetric();

        victoryMetricRed.setPopulation(populationRed);
        victoryMetricBlue.setPopulation(populationBlue);
        victoryMetricGreen.setPopulation(populationGreen);

        victoryMetricRed.setVictoryPoints(victoryPointsRed);
        victoryMetricBlue.setVictoryPoints(victoryPointsBlue);
        victoryMetricGreen.setVictoryPoints(victoryPointsGreen);

        victoryMetricRed.setTeamIdentifier("red");
        victoryMetricBlue.setTeamIdentifier("blue");
        victoryMetricGreen.setTeamIdentifier("green");

        victoryMetrics.add(victoryMetricRed);
        victoryMetrics.add(victoryMetricBlue);
        victoryMetrics.add(victoryMetricGreen);

        outputReceiver.output(victoryMetrics);
    }
}