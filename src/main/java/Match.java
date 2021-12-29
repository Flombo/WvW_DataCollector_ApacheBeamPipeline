import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Match implements Serializable {

    @JsonProperty("id")
    public String id;
    @JsonProperty("start_time")
    public String start_time;

}
