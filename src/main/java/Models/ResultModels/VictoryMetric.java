package Models.ResultModels;

import Models.Population;
import java.io.Serializable;

public class VictoryMetric implements Serializable {

    private String teamIdentifier;
    private Long victoryPoints;
    private Population population;

    public String getTeamIdentifier() {
        return teamIdentifier;
    }

    public void setTeamIdentifier(String teamIdentifier) {
        this.teamIdentifier = teamIdentifier;
    }

    public Long getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(Long victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public Population getPopulation() {
        return population;
    }

    public void setPopulation(Population population) {
        this.population = population;
    }

    @Override
    public String toString()
    {
        return "VictoryMetric{" +
                "teamIdentifier='" + getTeamIdentifier() + '\'' +
                ", victoryPoints=" + getVictoryPoints() +
                ", population=" + getPopulation() +
                '}';
    }
}
