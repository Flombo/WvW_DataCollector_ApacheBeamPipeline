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
    public TeamAttributes scores;
    public List<Bonus> bonuses;
    public List<Objectives> objectives;
    public TeamAttributes deaths;
    public TeamAttributes kills;

    @Override
    public String toString() {
        return "WVWMap{" +
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