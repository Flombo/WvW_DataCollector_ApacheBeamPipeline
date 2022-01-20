package Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class World implements Serializable {

    private String name;
    private Population population;

    @Override
    public String toString() {
        return "Models.World{" +
                "name='" + name + '\'' +
                ", population='" + population + '\'' +
                ", population-numeric-value=" + population.getNumericValue() +
                '}';
    }

}
