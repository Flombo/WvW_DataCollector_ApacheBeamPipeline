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
     * The attributes are extracted into separate classes aside from attributes that are not nested
     */

    private String id;
    @JsonProperty("start_time")
    private String startTime;
    private TeamAttribute scores;
    private World worlds;
    private TeamAttribute deaths;
    private TeamAttribute kills;
    @JsonProperty("victory_points")
    private TeamAttribute victoryPoints;
    private List<WVWMap> maps;



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