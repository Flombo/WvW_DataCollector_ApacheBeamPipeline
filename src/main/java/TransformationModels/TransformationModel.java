package TransformationModels;

import java.io.Serializable;

public class TransformationModel implements Serializable {

    protected String timestamp;
    protected String starttime;
    protected String endtime;

    public TransformationModel() {
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
