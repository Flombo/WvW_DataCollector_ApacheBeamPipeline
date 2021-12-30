import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AllWorlds implements Serializable
{

    public List<Integer> red;
    public List<Integer> blue;
    public List<Integer> green;

    @Override
    public String toString() {
        return "AllWorlds{" +
                "red=" + red +
                ", blue=" + blue +
                ", green=" + green +
                '}';
    }
}