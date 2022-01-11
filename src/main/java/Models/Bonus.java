package Models;

import java.io.Serializable;

public class Bonus implements Serializable
{
    private String type;
    private String owner;

    @Override
    public String toString() {
        return "Models.Bonus{" +
                "type='" + type + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
