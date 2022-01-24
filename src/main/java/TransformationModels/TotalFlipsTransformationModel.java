package TransformationModels;

import ResultModels.ResultModel;

import java.io.Serializable;

public class TotalFlipsTransformationModel extends ResultModel implements Serializable {

    private String identifier;
    private String map;
    private String objective;
    private String owner;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "TotalFlipsTransformationModel{" +
                "identifier='" + identifier + '\'' +
                ", map='" + map + '\'' +
                ", objective='" + objective + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }

}
