package Transformations;

import Models.Bonus;
import org.apache.beam.sdk.transforms.DoFn;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintBonusesPerMap extends DoFn<HashMap<String, List<Bonus>>, String>
{
    @ProcessElement
    public void processElement(@Element HashMap<String, List<Bonus>> map, OutputReceiver<String> out)
    {
        for (Map.Entry<String, List<Bonus>> entry : map.entrySet())
        {
            System.out.println("Current bonuses on " + entry.getKey() + ": ");

            for (Bonus bonus : entry.getValue())
            {
                System.out.println(bonus.toString());
            }
        }

    }
}
