package Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WVWMap implements Serializable
{
    private String id;
    private String name;
    private String type;
    private TeamAttribute scores;
    private List<Bonus> bonuses;
    private List<Objective> objectives;
    private TeamAttribute deaths;
    private TeamAttribute kills;

    @Override
    public String toString() {
        return "Models.WVWMap{" +
                "id=" + id +
                "name=" + name +
                ", type='" + type + '\'' +
                ", scores=" + scores +
                ", bonuses=" + bonuses +
                ", objectives=" + objectives +
                ", deaths=" + deaths +
                ", kills=" + kills +
                '}';
    }
}