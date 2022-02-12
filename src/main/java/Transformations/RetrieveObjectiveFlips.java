package Transformations;

import Models.Match;
import Models.WVWMap;
import Models.Objective;
import TransformationModels.ObjectiveFlip;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;

public class RetrieveObjectiveFlips extends DoFn<Match, KV<String, ObjectiveFlip>>{

    @ProcessElement
    public void ProcessElement(@Element Match input, OutputReceiver<KV<String, ObjectiveFlip>> outputReceiver) {

        for(WVWMap map : input.getMaps()) {
            String mapName = map.getName();

            for (Objective objective: map.getObjectives()) {

                ObjectiveFlip objectiveFlip = new ObjectiveFlip();
                objectiveFlip.setTimestamp(input.getTimestamp());
                objectiveFlip.setStarttime(input.getStartTime());
                objectiveFlip.setEndtime(input.getEndTime());
                String owner = objective.getOwner();
                String objectiveType = objective.getType();
                objectiveFlip.setMap(mapName);
                objectiveFlip.setOwner(owner);
                objectiveFlip.setIdentifier(mapName, objective.getId());
                objectiveFlip.setObjective(objectiveType);

                outputReceiver.output(KV.of(objectiveFlip.getIdentifier(), objectiveFlip));
            }
        }

    }

}




