package Models.ResultModels;

import java.io.Serializable;

public class TotalFlip implements Serializable {

    private String mapName;
    private Long totalFlips;

    public TotalFlip() {}

    public TotalFlip(String mapName, Long totalFlips) {
        this.mapName = mapName;
        this.totalFlips = totalFlips;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
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
                "mapName='" + mapName + '\'' +
                ", totalFlips=" + totalFlips +
                '}';
    }
}
