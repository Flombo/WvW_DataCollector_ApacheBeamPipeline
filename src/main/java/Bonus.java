import java.io.Serializable;

public class Bonus implements Serializable
{
    public String type;
    public String owner;

    @Override
    public String toString() {
        return "Bonus{" +
                "type='" + type + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
