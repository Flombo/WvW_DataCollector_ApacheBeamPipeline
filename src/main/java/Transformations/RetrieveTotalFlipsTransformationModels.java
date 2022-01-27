package Transformations;

import Models.Match;
import Models.WVWMap;
import Models.Objective;
import TransformationModels.ObjectiveFlip;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;

public class RetrieveTotalFlipsTransformationModels extends DoFn<Match, KV<String, ObjectiveFlip>>{

    @ProcessElement
    public void ProcessElement(@Element Match input, OutputReceiver<KV<String, ObjectiveFlip>> outputReceiver) {

        for(WVWMap map : input.getMaps()) {
            String mapName = map.getName();

            for (Objective objective: map.getObjectives()) {

                ObjectiveFlip totalFlipsTransformationModel = new ObjectiveFlip();
                totalFlipsTransformationModel.setTimestamp(input.getTimestamp());
                totalFlipsTransformationModel.setStarttime(input.getStartTime());
                totalFlipsTransformationModel.setEndtime(input.getEndTime());
                String owner = objective.getOwner();
                String objectiveType = objective.getType();
                totalFlipsTransformationModel.setMap(mapName);
                totalFlipsTransformationModel.setOwner(owner);
                totalFlipsTransformationModel.setIdentifier(mapName, objective.getId());
                totalFlipsTransformationModel.setObjective(objectiveType);

                outputReceiver.output(KV.of(totalFlipsTransformationModel.getIdentifier(), totalFlipsTransformationModel));
            }
        }

    }

}




