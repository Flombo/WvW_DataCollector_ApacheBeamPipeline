package Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class World implements Serializable {

    private WorldObject red;
    private WorldObject blue;
    private WorldObject green;

    @Override
    public String toString() {
        return "Models.World{" +
                "red=" + red +
                ", blue=" + blue +
                ", green=" + green +
                '}';
    }
}