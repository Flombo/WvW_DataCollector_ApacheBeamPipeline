package Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorldObject implements Serializable {

    public String name;
    public String population;

    @Override
    public String toString() {
        return "Models.WorldObject{" +
                "name='" + name + '\'' +
                ", population='" + population + '\'' +
                '}';
    }
}
