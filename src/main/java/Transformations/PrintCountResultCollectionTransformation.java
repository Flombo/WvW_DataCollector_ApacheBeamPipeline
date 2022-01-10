package Transformations;

import org.apache.beam.sdk.transforms.DoFn;

public class PrintCountResultCollectionTransformation extends DoFn<Long, Long>
{
    @ProcessElement
    public void processElement(@Element Long count, OutputReceiver<Long> out)
    {
        System.out.println("count result = " + count);
        out.output(count);
    }
}
