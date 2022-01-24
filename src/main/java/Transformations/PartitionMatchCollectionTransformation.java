package Transformations;

import Models.Match;
import org.apache.beam.sdk.transforms.Partition;

public class PartitionMatchCollectionTransformation implements Partition.PartitionFn<Match> {

        private int i = 0;

        @Override
        public int partitionFor(Match match, int numPartitions) {

                int partitionNumber = i;

                i = i >= 1 ? 0 : 1;

                return partitionNumber;
        }
}