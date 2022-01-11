package Models;

public enum Population {

    Low(1),
    Medium(2),
    High(3),
    VeryHigh(4),
    Full(5);

    private final int numericValue;

    Population(int numericValue)
    {
        this.numericValue= numericValue;
    }

    public int getNumericValue() {
        return this.numericValue;
    }

    @Override
    public String toString()
    {
        return "Population{" +
                "numericValue=" + numericValue +
                '}';
    }
}
