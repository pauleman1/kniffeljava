package de.azubi.kniffel.core.game;

public class GameEngine {
    private static int MAX_PLAYERS;
    final Round rou;
    Score[] scoreboardArr;
    int currentPlayer = 0;

    GameEngine(int playerNumber) {
        MAX_PLAYERS = playerNumber;
        scoreboardArr = new Score[MAX_PLAYERS];

// scoreboard für jeden spieler machen
        for (int player = 0; player < MAX_PLAYERS; player++) {
            scoreboardArr[player] = new Score();
        }
        rou = new Round(this.scoreboardArr);
        rou.setPlayer(0);
    }


    private void reset() {
        this.currentPlayer++;
        if (this.currentPlayer == MAX_PLAYERS) {
            this.currentPlayer = 0;
        }

        this.rou.setPlayer(this.currentPlayer);
        this.rou.throwsLeft = 3;
        for (int dice = 0; dice < 5; dice++) {
            this.rou.dices[dice].setKeeper(false);
        }
    }
    // score updaten nach jedem wurf
    public void reRoll() {
        if (this.rou.throwsLeft > 0) {
            this.rou.throwsLeft--;
            this.rou.rollDices();
            for (Score scoreboard : this.scoreboardArr) {
                scoreboard.updateMaxScore(this.rou.dices);
            }
        }
    }

    // score speichern
    public boolean scoreSelect(int row) {
        if (this.rou.throwsLeft != 3) {
            boolean result = this.rou.scoreSelectCheck(row);
            if (!result) {
                return false;
            }
            // Reset for new round
            this.reset();

            return true;
        }
        return false;
    }

    // ist das spiel vorbei?
    public boolean isGameOver() {
        for (Score scoreboard : this.scoreboardArr) {
            if (!scoreboard.isFull()) {
                return false;
            }
        }
        return true;
    }


    public int[][] getWinners() {
        if (this.isGameOver()) {
            int[][] winnersArray = new int[MAX_PLAYERS][];
            for (int i = 0; i < MAX_PLAYERS; i++) {
                winnersArray[i] = new int[]{i, this.scoreboardArr[i].getScore()[15]};
            }
            return winnersArray;
        } else {
            return new int[1][0];
        }
    }

    // spieler der gewonnen hat zur+ck geben, unentschieden dann 0
    public int getWinner() {
        if (this.isGameOver()) {
            int maxScore = 0;
            int player = 0;
            for (int i = 0; i < MAX_PLAYERS; i++) {
                if (this.scoreboardArr[i].getScore()[15] > maxScore) {
                    player = i + 1;
                }
                if (this.scoreboardArr[i].getScore()[15] == maxScore) {
                    return 0;
                }
            }
            return player;
        }
        return -1;
    }
}
