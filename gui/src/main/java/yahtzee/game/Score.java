package yahtzee.game;

import static yahtzee.game.ScoreUtils.*;


public class Score {

    private final int[] tempScoreBoard = new int[17];
    private final int[] scoreBoard = new int[17];

    public Score() {
        for (int i = 0; i < 17; i++) {
            this.tempScoreBoard[i] = 0;
        }
        for (int i = 0; i < 17; i++) {
            this.scoreBoard[i] = -1;
        }

    }

   // ergebniss updaten
    public void updateMaxScore(Dice[] dices) {
        // First reset score
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


/*
    public void Highscore(Dice [] dices) {
        for (int diceValue = 0; diceValue < 17; diceValue ++){
            tempScoreBoard[diceValue] = 0;
        }
        tempScoreBoard[16] = highscore(dices);
    }

*/


    private void upperScore(Dice[] dices) {
        for (int diceValue = 0; diceValue < 6; diceValue++) {
            for (Dice dice : dices) {
                if (dice.value() == diceValue + 1) {
                    tempScoreBoard[diceValue] += diceValue + 1;
                }
            }
        }
    }

    //nur für debug notwendig, da es am anfang nicht funktioniert hat
    public void printScore() {
        for (int i = 0; i < 17; i++) {
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

    // bonus wenn vergeben dem endergebnis zuteilen
    private void updateTotalScore() {
        scoreBoard[7] = totalUpper();
        scoreBoard[6] = bonus();
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
        int lastresult = 0;
        int newhighscore = 0;

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

            else
            {

            }


        }
        
        return counter;

    }

    private int bonus() {
        if (scoreBoard[7] >= 63) {
            return 35;
        } else if (scoreBoard[7] == -1) {
            return -1;
        } else {
            return 0;
        }
    }

    // checkn ob ergebnistafel bereits voll ist
    public boolean isFull() {
        for (int i = 0; i < 17; i++) {
            if (scoreBoard[i] == -1) {
                return false;
            }
        }
        return true;
    }
    /*
    int Highscore(Dice[] dices) {
        int highscore = 0;
        int wertneu = 0;
        tempScoreBoard[16] = wertneu;
        for (Dice dice : dices) {
            if (wertneu > highscore) {
                highscore == wertneu;
            }
            else
            {
                //nichts passiert , highscore bleibt wie bisher
            }
        }
    }*/

}













