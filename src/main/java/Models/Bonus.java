package Models;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bonus implements Serializable
{
    private String type;
    private String owner;

    @Override
    public String toString() {
        return "Models.Bonus{" +
                "type='" + type + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
