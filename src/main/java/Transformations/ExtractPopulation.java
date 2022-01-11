package Transformations;

import Models.Match;
import Models.Population;
import Models.World;
import org.apache.beam.sdk.transforms.DoFn;
import java.util.HashMap;

public class ExtractPopulation extends DoFn<Match, HashMap<String, Population>>
{

    @ProcessElement
    public void processElement(@Element Match input, OutputReceiver<HashMap<String, Population>> out)
    {
        HashMap<String, Population> populationMap = new HashMap<>();

        World worlds = input.getWorlds();

        populationMap.put(worlds.getRed().getName(),worlds.getRed().getPopulation());
        populationMap.put(worlds.getBlue().getName(),worlds.getBlue().getPopulation());
        populationMap.put(worlds.getGreen().getName(),worlds.getGreen().getPopulation());

        out.output(populationMap);

    }



}
