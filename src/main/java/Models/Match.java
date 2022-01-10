package Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Match implements Serializable
{
    /**
     * Models.Match is a POJO implementation for the v2/wvw/matches/ API call of the Guild Wars 2 API
     * Modeling of the object is made possible through implementation of the 'serializable' interface
     * The attributes are extracted into seperate classes aside from attributes that are not nested
     */

    public String id;
    @JsonProperty("start_time")
    public String startTime;
    public TeamAttribute scores;
    public World worlds;
    public TeamAttribute deaths;
    public TeamAttribute kills;
    @JsonProperty("victory_points")
    public TeamAttribute victoryPoints;
    public List<WVWMap> maps;



    @Override
    public String toString() {
        return "Models.Match{" +
                "id='" + id + '\'' +
                ", startTime='" + startTime + '\'' +
                ", scores=" + scores +
                ", worlds=" + worlds +
                ", deaths=" + deaths +
                ", kills=" + kills +
                ", victoryPoints=" + victoryPoints +
                ", maps=" + maps +
                '}';
    }
}
