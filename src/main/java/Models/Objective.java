package Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Objective implements Serializable
{
    public String id;
    public String type;
    public String owner;
    @JsonProperty("last_flipped")
    public String lastFlipped;
    @JsonProperty("claimed_by")
    public String claimedBy;

    @JsonProperty("claimed_at")
    public String claimedAt;

    @JsonProperty("points_tick")
    public int pointsTick;
    @JsonProperty("points_capture")
    public int pointsCapture;
    @JsonProperty("yaks_delivered")
    public int yaksDelivered;


    @Override
    public String toString() {
        return "Objectives{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", owner='" + owner + '\'' +
                ", lastFlipped='" + lastFlipped + '\'' +
                ", claimedBy='" + claimedBy + '\'' +
                ", claimedAt='" + claimedAt + '\'' +
                ", pointsTick=" + pointsTick +
                ", pointsCapture=" + pointsCapture +
                ", yaksDelivered=" + yaksDelivered +
                '}';
    }
}