import com.google.auto.value.AutoValue;
import org.apache.beam.sdk.schemas.AutoValueSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;
import org.apache.beam.sdk.schemas.annotations.SchemaCreate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


@DefaultSchema(AutoValueSchema.class)
@AutoValue
public abstract class Match {

    Date start_time;
    Date end_time;

    //with red, blue and green scores
    int[] scores;

    int[] worldsIds;

    int[] deaths;

    int[] kills;

    int[] vPoints;

    List<Object> maps;

    @SchemaCreate
    public Match()
    {
    }



    public Date getStart_time()
    {
        return start_time;
    }

    public void setStart_time(Date start_time)
    {
        this.start_time = start_time;
    }

    public Date getEnd_time()
    {
        return end_time;
    }

    public void setEnd_time(Date end_time)
    {
        this.end_time = end_time;
    }

    public int[] getScores()
    {
        return scores;
    }

    public void setScores(int[] scores)
    {
        this.scores = scores;
    }

    public int[] getWorldsIds()
    {
        return worldsIds;
    }

    public void setWorldsIds(int[] worldsIds)
    {
        this.worldsIds = worldsIds;
    }

    public int[] getDeaths()
    {
        return deaths;
    }

    public void setDeaths(int[] deaths)
    {
        this.deaths = deaths;
    }

    public int[] getKills()
    {
        return kills;
    }

    public void setKills(int[] kills)
    {
        this.kills = kills;
    }

    public int[] getvPoints()
    {
        return vPoints;
    }

    public void setvPoints(int[] vPoints)
    {
        this.vPoints = vPoints;
    }

    public List<Object> getMaps()
    {
        return maps;
    }

    public void setMaps(List<Object> maps)
    {
        this.maps = maps;
    }

    @Override
    public String toString() {
        return "Match{" +
                "startTime=" + start_time +
                ", endTime=" + end_time +
                ", scores=" + Arrays.toString(scores) +
                ", worldsIds=" + Arrays.toString(worldsIds) +
                ", deaths=" + Arrays.toString(deaths) +
                ", kills=" + Arrays.toString(kills) +
                ", vPoints=" + Arrays.toString(vPoints) +
                ", maps=" + maps +
                '}';
    }
}
