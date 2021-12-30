import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Match implements Serializable {

    @JsonProperty("id")
    public String id;
    @JsonProperty("start_time")
    public String startTime;
    public TeamAttributes scores;
    @JsonProperty("all_worlds")
    public AllWorlds allWorlds;
    public TeamAttributes deaths;
    public TeamAttributes kills;
    @JsonProperty("victory_points")
    public TeamAttributes victoryPoints;
    public List<WVWMap> maps;



    @Override
    public String toString() {
        return "Match{" +
                "id='" + id + '\'' +
                ", startTime='" + startTime + '\'' +
                ", scores=" + scores +
                ", allWorlds=" + allWorlds +
                ", deaths=" + deaths +
                ", kills=" + kills +
                ", victoryPoints=" + victoryPoints +
                ", maps=" + maps +
                '}';
    }
}
