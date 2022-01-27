package Transformations;

import ResultModels.TotalFlip;
import org.apache.beam.sdk.transforms.Partition;
import org.apache.beam.sdk.values.KV;

public class PartitionMatchCollectionTransformation implements Partition.PartitionFn<KV<String, TotalFlip>> {

        private int i = 0;

        @Override
        public int partitionFor(KV<String, TotalFlip> stringTotalFlipKV, int numPartitions) {

                int partitionNumber = i;

                i = i >= 1 ? 0 : 1;

                return partitionNumber;
        }
}