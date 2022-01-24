package Transformations;

import ResultModels.TotalFlip;
import org.apache.beam.sdk.transforms.DoFn;

public class PrintTotalFlip extends DoFn<TotalFlip, String>
{
    @ProcessElement
    public void processElement(@Element TotalFlip input, OutputReceiver<String> out)
    {
        System.out.println(input.toString());
        out.output(input.toString());
    }

}