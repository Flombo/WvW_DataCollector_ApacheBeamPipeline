package Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamAttribute implements Serializable
{
    private int red;
    private int blue;
    private int green;
}