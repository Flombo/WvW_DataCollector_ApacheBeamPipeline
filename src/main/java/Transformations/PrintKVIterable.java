package Transformations;

import ResultModels.TotalFlip;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;

import java.util.Objects;

public class PrintKVIterable extends DoFn<KV<String, Iterable<TotalFlip>>, String> {

    int count = 0;

    @ProcessElement
    public void processElement(@Element KV<String, Iterable<TotalFlip>> input, OutputReceiver<String> outputReceiver) {

        Objects.requireNonNull(input.getValue()).forEach(value -> {
            if(Objects.equals(input.getKey(), "EternalBattlegrounds")) {
                count++;
                System.out.println(input.getKey() +  " : " + value + " : " + count);
            }
        });

        outputReceiver.output(input.getKey());

    }

}
