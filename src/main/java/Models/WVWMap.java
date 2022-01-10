package Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WVWMap implements Serializable
{
    public int id;
    public String type;
    public TeamAttribute scores;
    public List<Bonus> bonuses;
    public List<Objective> objectives;
    public TeamAttribute deaths;
    public TeamAttribute kills;

    @Override
    public String toString() {
        return "Models.WVWMap{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", scores=" + scores +
                ", bonuses=" + bonuses +
                ", objectives=" + objectives +
                ", deaths=" + deaths +
                ", kills=" + kills +
                '}';
    }
}