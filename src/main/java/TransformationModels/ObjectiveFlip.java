package TransformationModels;

public class ObjectiveFlip extends TransformationModel {

    private String identifier;
    private String map;
    private String objective;
    private String owner;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String mapName, String objectiveID) {
        this.identifier = (mapName + "~" + objectiveID).replaceAll(" ", "");
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
                "timestamp='" + timestamp + '\'' +
                ", starttime='" + starttime + '\'' +
                ", endtime='" + endtime + '\'' +
                ", identifier='" + identifier + '\'' +
                ", map='" + map + '\'' +
                ", objective='" + objective + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
