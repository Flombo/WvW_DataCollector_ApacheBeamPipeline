import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamAttributes implements Serializable
{
    public int red;
    public int blue;
    public int green;
}