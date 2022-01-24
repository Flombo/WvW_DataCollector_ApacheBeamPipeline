package ResultModels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *  BSON-Format:
 *  {
 *      totalflips : [{
 *        mapname : '',
 *        flips : 100
 *      }],
 *      timestamp : '',
 *      starttime : '',
 *      endtime : ''
 *  }
 */
public class TotalFlip extends ResultModel implements Serializable {

    private String mapname;
    private Long totalFlips;

    public void setMapname(String mapname) {
        this.mapname = mapname;
    }

    public String getMapname() {
        return mapname;
    }

    public Long getTotalFlips() {
        return totalFlips;
    }

    public void setTotalFlips(Long totalFlips) {
        this.totalFlips = totalFlips;
    }

    @Override
    public String toString() {
        return "TotalFlip{" +
                "mapname='" + mapname + '\'' +
                ", totalFlips=" + totalFlips +
                ", timestamp=" + getTimestamp() +
                '}';
    }
}
