package Models;

import java.io.Serializable;

public class Bonus implements Serializable
{
    public String type;
    public String owner;

    @Override
    public String toString() {
        return "Models.Bonus{" +
                "type='" + type + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
