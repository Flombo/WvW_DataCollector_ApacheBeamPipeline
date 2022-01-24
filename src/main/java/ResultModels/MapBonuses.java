package ResultModels;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * {
 *      centermap : {
 *          name : '',
 *          bonuses : 10
 *      }
 *      redmap : {..},
 *      bluemap : {..},
 *      greenmap : {..},
 *      timestamp : '',
 *      starttime : '',
 *      endtime : ''
 * }
 */
public class MapBonuses extends ResultModel{

    private HashMap<String, Object> centerMap;
    private HashMap<String, Object> redMap;
    private HashMap<String, Object> blueMap;
    private HashMap<String, Object> greenMap;

    public MapBonuses() {
        centerMap = new HashMap<>();
        redMap = new HashMap<>();
        blueMap = new HashMap<>();
        greenMap = new HashMap<>();
    }

    public HashMap<String, Object> getCenterMap() {
        return centerMap;
    }

    public HashMap<String, Object> getRedMap() {
        return redMap;
    }

    public HashMap<String, Object> getBlueMap() {
        return blueMap;
    }

    public HashMap<String, Object> getGreenMap() {
        return greenMap;
    }
}
