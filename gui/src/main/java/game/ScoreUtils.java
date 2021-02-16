package game;

import java.util.Arrays;

public class ScoreUtils {

    private static int score;

    static public int fullHouse(Dice[] dices) {
        int[] counts = new int[6];
        for (Dice dice : dices) {
            counts[dice.value() - 1]++;
        }

        boolean check2 = false;
        boolean check3 = false;
        for (int i : counts) {
            check2 |= (i == 2);
            check3 |= (i == 3);
            if (i == 5) return 25;
        }
        return (check2 && check3) ? 25 : 0;
    }

    public static int smallStraight(Dice[] dices) {
        Dice[] sortDices;
        sortDices = dices.clone();
        Arrays.sort(sortDices);

        int counter = 0;

        for (int i = 0; i < sortDices.length - 1; i++) {
            if (sortDices[i + 1].value() == sortDices[i].value() + 1) {
                counter++;
            } else if (sortDices[i + 1].value() == sortDices[i].value()) {
                continue;
            } else {
                counter = 0;
            }
            if (counter == 3) {
                return 30;
            }
        }
        return 0;
    }

    public static int largeStraight(Dice[] dices) {
        Dice[] sortDices;
        sortDices = dices.clone();
        Arrays.sort(sortDices);

        int counter = 0;

        for (int i = 0; i < sortDices.length - 1; i++) {
            if (sortDices[i + 1].value() == sortDices[i].value() + 1) {
                counter++;
            } else if (sortDices[i + 1].value() == sortDices[i].value()) {
                continue;
            } else {
                counter = 0;
            }
            if (counter == 4) {
                return 40;
            }
        }
        return 0;
    }

    static int sumDices(Dice[] dices) {
        score = 0;
        for (Dice dice : dices) {

            score = score + dice.value();
        }
        return score;
    }

    public static int threeOfKind(Dice[] dices) {
        Dice[] sortedDices;
        sortedDices = dices.clone();
        Arrays.sort(sortedDices);

        if ((sortedDices[0].value() == sortedDices[1].value() && sortedDices[1].value() == sortedDices[2].value()) ||
                (sortedDices[1].value() == sortedDices[2].value() && sortedDices[2].value() == sortedDices[3].value()) ||
                (sortedDices[2].value() == sortedDices[3].value() && sortedDices[3].value() == sortedDices[4].value())) {
            return sumDices(dices);
        } else {
            return 0;
        }
    }

    public static int fourOfKind(Dice[] dices) {
        Dice[] sortedDices;
        sortedDices = dices.clone();
        Arrays.sort(sortedDices);

        if ((sortedDices[0].value() == sortedDices[3].value()) || (sortedDices[1].value() == sortedDices[4].value()))
            return sumDices(dices);
        return 0;
    }

    public static int yahtzee(Dice[] dices) {
        if (dices[0].value() == dices[1].value()
                && dices[1].value() == dices[2].value()
                && dices[2].value() == dices[3].value()
                && dices[3].value() == dices[4].value()) {
            return 50;
        } else
            return 0;
    }

    public static String lower(int i) {
        return switch (i) {
            case 0 -> "-";
            case 1 -> "Einser";
            case 2 -> "Zweier";
            case 3 -> "Dreier";
            case 4 -> "Vierer";
            case 5 -> "Fünfer";
            case 6 -> "Sechser";
            case 7 -> "Bonus";
            case 8 -> "Summe oben";
            case 9 -> "Dreierpasch";
            case 10 -> "Viererpasch";
            case 11 -> "Full House";
            case 12 -> "kleine Straße";
            case 13 -> "Große Straße";
            case 14 -> "Chance";
            case 15 -> "Kniffel";
            case 16 -> "Gesamtpunktzahl";
            default -> "---------------";
        };
    }
}