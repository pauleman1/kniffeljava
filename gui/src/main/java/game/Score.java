package game;

import static utils.ScoreUtils.*;

public class Score {

    private final int[] tempScoreBoard = new int[17];
    private final int[] scoreBoard = new int[17];
    private  int newhighscore;

    public Score() {
        for (int i = 0; i < 17; i++) {
            this.tempScoreBoard[i] = 0;
        }
        for (int i = 0; i < 17; i++) {
            this.scoreBoard[i] = -1;
        }
    }

    public void updateMaxScore(Dice[] dices) {
        for (int diceValue = 0; diceValue < 17; diceValue++) {
            tempScoreBoard[diceValue] = 0;
        }
        upperScore(dices); // für werte 0 bis 5 -> 6 werte

        tempScoreBoard[8] = threeOfKind(dices);
        tempScoreBoard[9] = fourOfKind(dices);
        tempScoreBoard[10] = fullHouse(dices);
        tempScoreBoard[11] = smallStraight(dices);
        tempScoreBoard[12] = largeStraight(dices);
        tempScoreBoard[13] = sumDices(dices);
        tempScoreBoard[14] = yahtzee(dices);
    }

    private void upperScore(Dice[] dices) {
        for (int diceValue = 0; diceValue < 6; diceValue++) {
            for (Dice dice : dices) {
                if (dice.value() == diceValue + 1) {
                    tempScoreBoard[diceValue] += diceValue + 1;
                }
            }
        }
    }

    public int[] getScore() {
        return this.scoreBoard;
    }

    public int[] getMaxScore() {
        return this.tempScoreBoard;
    }

    public boolean isAvailable(int scoreToCheck) {
        if (scoreToCheck == 7 || scoreToCheck == 8 || scoreToCheck == 17) {
            return false;
        }
        return this.scoreBoard[scoreToCheck - 1] == -1;
    }

    private void saveScore(int scorePos, int scoreValue) {
        this.scoreBoard[scorePos - 1] = scoreValue;
    }

    public void selectScore(Dice[] dices, int scorePos) {
        this.updateMaxScore(dices);
        this.saveScore(scorePos, tempScoreBoard[scorePos - 1]);
        this.updateTotalScore();
    }

    private void updateTotalScore() {
        scoreBoard[7] = totalUpper();
        scoreBoard[6] = addBonus();
        scoreBoard[15] = totalScore();
    }

    private int totalUpper() {
        int counter = 0;

        for (int i = 0; i < 6; i++) {
            if (scoreBoard[i] == -1) {
                return -1;
            }
            counter += scoreBoard[i];
        }
        return counter;
    }

    private int totalScore() {
        int counter = 0;
        int lastresult;

        for (int i = 6; i < 15; i++) {
            if (scoreBoard[i] == -1) {
                return -1;
            }
            counter += scoreBoard[i];
            lastresult = counter;


            if (lastresult > newhighscore)
            {
                newhighscore = lastresult;
                System.out.println("Test neuer Highscore - " + newhighscore);
            }
        }
        return counter;
    }

    private int addBonus() {
        if (scoreBoard[7] >= 63) {
            return 35;
        } else if (scoreBoard[7] == -1) {
            return -1;
        } else {
            return 0;
        }
    }

    public boolean isScoreboardFull() {
        for (int i = 0; i < 17; i++) {
            if (scoreBoard[i] == -1) {
                return false;
            }
        }
        return true;
    }
}