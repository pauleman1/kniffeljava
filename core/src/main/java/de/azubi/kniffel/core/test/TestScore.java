package de.azubi.kniffel.core.test;


import de.azubi.kniffel.core.game.Dice;
import de.azubi.kniffel.core.utils.ScoreUtils;
import org.junit.Assert;
import org.junit.Test;


public class TestScore {
    public TestScore() {
    }

    @Test
    public void smallStraight() {
        Dice[] dices = new Dice[5];

        for(int i = 0; i < 5; ++i) {
            dices[i] = new Dice(6);
        }

        dices[0].setValue(2);
        dices[1].setValue(3);
        dices[2].setValue(4);
        dices[3].setValue(1);
        boolean expected = true;
        int actual = ScoreUtils.smallStraight(dices);
        Assert.assertEquals(30L, (long)actual);
    }

    @Test
    public void threeOfAKind() {
        Dice[] dices = new Dice[5];

        for(int i = 0; i < 5; ++i) {
            dices[i] = new Dice(6);
        }

        dices[0].setValue(2);
        dices[1].setValue(2);
        dices[2].setValue(2);
        dices[3].setValue(4);
        dices[4].setValue(5);
        boolean expected = true;
        int actual = ScoreUtils.threeOfKind(dices);
        Assert.assertEquals(15L, (long)actual);
    }

    @Test
    public void smallStraightBis() {
        Dice[] dices = new Dice[5];

        for(int i = 0; i < 5; ++i) {
            dices[i] = new Dice(6);
        }

        dices[0].setValue(2);
        dices[1].setValue(1);
        dices[2].setValue(3);
        dices[3].setValue(6);
        dices[3].setValue(6);
        boolean expected = false;
        int actual = ScoreUtils.smallStraight(dices);
        Assert.assertEquals(0L, (long)actual);
    }

    @Test
    public void fourOfAKind() {
        Dice[] dices = new Dice[5];

        for(int i = 0; i < 5; ++i) {
            dices[i] = new Dice(6);
        }

        dices[0].setValue(2);
        dices[1].setValue(2);
        dices[2].setValue(6);
        dices[3].setValue(2);
        dices[4].setValue(2);
        boolean expected = true;
        int actual = ScoreUtils.fourOfKind(dices);
        Assert.assertEquals(14L, (long)actual);
    }

    @Test
    public void fourOfAKindBis() {
        Dice[] dices = new Dice[5];

        for(int i = 0; i < 5; ++i) {
            dices[i] = new Dice(6);
        }

        dices[0].setValue(5);
        dices[1].setValue(5);
        dices[2].setValue(5);
        dices[3].setValue(4);
        dices[4].setValue(5);
        boolean expected = true;
        int actual = ScoreUtils.fourOfKind(dices);
        Assert.assertEquals(24L, (long)actual);
    }

    @Test
    public void fourOfAKindTris() {
        Dice[] dices = new Dice[5];

        for(int i = 0; i < 5; ++i) {
            dices[i] = new Dice(6);
        }

        dices[0].setValue(6);
        dices[1].setValue(6);
        dices[2].setValue(6);
        dices[3].setValue(6);
        dices[4].setValue(1);
        boolean expected = true;
        int actual = ScoreUtils.fourOfKind(dices);
        Assert.assertEquals(25L, (long)actual);
    }

    @Test
    public void largeStraight() {
        Dice[] dices = new Dice[5];

        for(int i = 0; i < 5; ++i) {
            dices[i] = new Dice(6);
        }

        dices[0].setValue(2);
        dices[1].setValue(5);
        dices[2].setValue(4);
        dices[3].setValue(3);
        dices[4].setValue(6);
        boolean expected = true;
        int actual = ScoreUtils.largeStraight(dices);
        Assert.assertEquals(40L, (long)actual);
    }

    @Test
    public void yahtzee() {
        Dice[] dices = new Dice[5];

        for(int i = 0; i < 5; ++i) {
            dices[i] = new Dice(6);
        }

        dices[0].setValue(1);
        dices[1].setValue(1);
        dices[2].setValue(1);
        dices[3].setValue(1);
        dices[4].setValue(1);
        boolean expected = true;
        int actual = ScoreUtils.yahtzee(dices);
        Assert.assertEquals(50L, (long)actual);
    }
}
