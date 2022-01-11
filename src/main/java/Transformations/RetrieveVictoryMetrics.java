package Transformations;

import Models.Match;
import Models.ResultModels.VictoryMetric;
import org.apache.beam.sdk.transforms.DoFn;
import java.util.List;

public class RetrieveVictoryMetrics extends DoFn<Match, List<VictoryMetric>>
{

}