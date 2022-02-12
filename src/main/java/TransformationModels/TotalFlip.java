package TransformationModels;

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
public class TotalFlip extends TransformationModel {

    private String mapname;
    private Long totalFlips;

    public void setMapName(String mapName) {
        this.mapname = mapName;
    }

    public String getMapName() {
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
