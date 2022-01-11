package Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Objective implements Serializable
{
    private String id;
    private String type;
    private String owner;
    @JsonProperty("last_flipped")
    private String lastFlipped;
    @JsonProperty("claimed_by")
    private String claimedBy;

    @JsonProperty("claimed_at")
    private String claimedAt;

    @JsonProperty("points_tick")
    private int pointsTick;
    @JsonProperty("points_capture")
    private int pointsCapture;
    @JsonProperty("yaks_delivered")
    private int yaksDelivered;


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