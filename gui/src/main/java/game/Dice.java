package game;

import java.util.Random;

public class Dice implements Comparable<Dice> {
    private final int sides;
    private final Random ran;
    private int value;
    private boolean keeper;

    public Dice(int num) {
        sides = num; // 6 seiten
        ran = new Random();
        value = roll();
    }

    public int roll() {
        value = ran.nextInt(sides) + 1;
        return value;
    }

    public int value()

    {
        return value;
    }

    public boolean keep()
    {
        return !keeper;
    }

    public void toggleKeeper()
    {
        keeper = !keeper;
    }

    public void setKeeper(boolean keep)
    {
        this.keeper = keep;
    }

    public void setValue(int newValue)
    {
        this.value = newValue;
    }

    @Override
    public int compareTo( Dice o) {
        if (o == null) throw new AssertionError();
        return Integer.compare(value, o.value);
    }
}