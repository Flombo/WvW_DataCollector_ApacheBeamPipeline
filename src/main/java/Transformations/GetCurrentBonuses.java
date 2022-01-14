package Transformations;

import Models.Bonus;
import Models.Match;
import Models.WVWMap;
import org.apache.beam.sdk.transforms.DoFn;

import java.util.HashMap;
import java.util.List;

public class GetCurrentBonuses extends DoFn<Match, HashMap<String, List<Bonus>>>
{
    @ProcessElement
    public void ProcessElement(@Element Match input, OutputReceiver<HashMap<String, List<Bonus>>> out)
    {
        HashMap<String, List<Bonus>> result = new HashMap<>();

        List<WVWMap> maps = input.getMaps();

        WVWMap Center = maps.get(0);
        WVWMap RedHome = maps.get(1);
        WVWMap BlueHome = maps.get(2);
        WVWMap GreenHome = maps.get(3);

        result.put(Center.getName(), Center.getBonuses());
        result.put(RedHome.getName(), RedHome.getBonuses());
        result.put(BlueHome.getName(), BlueHome.getBonuses());
        result.put(GreenHome.getName(), GreenHome.getBonuses());

        out.output(result);


    }


}
