package Transformations;

import Models.Match;
import org.apache.beam.sdk.transforms.DoFn;

public class PrintMatchCollectionTransformation extends DoFn<Match, Match>
{
    @ProcessElement
    public void processElement(@Element Match match, OutputReceiver<Match> out)
    {
        System.out.println(match.getWorlds().toString());
        out.output(match);
    }
}