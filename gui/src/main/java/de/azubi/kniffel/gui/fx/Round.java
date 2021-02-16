package de.azubi.kniffel.gui.fx;

public class Round {
    final Dice[] dices = new Dice[5];
    private final Score[] scoreboards;
    int throwsLeft;
    private int currentPlayer;


    public Round(Score[] scoreboards) {
        this.scoreboards = scoreboards;
        throwsLeft = 3;

        for (int i = 0; i < 5; i++) {
            dices[i] = new Dice(6);
        }
    }

    void rollDices() {
        for (int i = 0; i < 5; i++) {
            // System.out.println(i);
            if (this.dices[i].keep()) {
                this.dices[i].roll();
            }
        }
    }

    void toggleKeeperSolo(int keep)
    {
        this.dices[keep - 1].toggleKeeper();
    }

    boolean scoreSelectCheck(int score) {
        if (this.scoreboards[this.currentPlayer].isAvailable(score + 1)) {
            this.scoreboards[this.currentPlayer].selectScore(this.dices, score + 1);
            this.scoreboards[this.currentPlayer].printScore();
            return true;
        } else {
            return false;
        }
    }

    void setPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
