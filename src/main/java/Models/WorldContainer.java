package Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorldContainer implements Serializable {

    private World red;
    private World blue;
    private World green;

    @Override
    public String toString() {
        return "Models.WorldContainer{" +
                "red=" + red +
                ", blue=" + blue +
                ", green=" + green +
                '}';
    }
}
