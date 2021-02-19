package yahtzee;

import de.azubi.kniffel.core.game.GameEngine;
import de.azubi.kniffel.core.game.Score;
import de.azubi.kniffel.core.utils.ScoreUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import yahtzee.table.ScoreRow;

import java.io.IOException;
import java.util.Optional;

public class GameController {
    private static final int MAX_SCORE_NAME = 17;
    private final Image[] img = new Image[6];
    public TableView tableView;
    public TableColumn player1;
    public TableColumn player2;
    public TableColumn player3;
    public TableColumn player4;
    public TableColumn player5;


    public TableColumn scoreName;
    public Button reRollButton;
    public GridPane rollingDice;
    public GridPane keptDice;
    public GridPane helpGrid;
    public Label title;
    public Label helpLabel;
    private int MAX_PLAYERS;
    public static int gewurfeltCounter;

    @FXML
    private Label scoreLabel;
    @FXML
    private ImageView dice1;
    @FXML
    private ImageView dice2;
    @FXML
    private ImageView dice3;
    @FXML
    private ImageView dice4;
    @FXML
    private ImageView dice5;
    private GameEngine gfx;

    private TextField[] playerNames;
    @FXML
    private TextField namePlayerOne;

    @FXML
    private TextField namePlayerTwo;

    @FXML
    private TextField namePlayerThree;

    @FXML
    private TextField namePlayerFour;

    @FXML
    private TextField namePlayerFive;

    @FXML
    private TextField namePlayerSix;

    @FXML
    private Button playButton;

    public GameController(int playerNumber) {
        MAX_PLAYERS = playerNumber;
    }

    public GameController() {

    }

    public void initialize() {



        hideHelpPanel();
        this.gfx = new GameEngine(MAX_PLAYERS);

        img[0] = new Image("/Wuerfelaugen1.png");
        img[1] = new Image("/Wuerfelaugen2.png");
        img[2] = new Image("/Wuerfelaugen3.png");
        img[3] = new Image("/Wuerfelaugen4.png");
        img[4] = new Image("/Wuerfelaugen5.png");
        img[5] = new Image("/Wuerfelaugen6.png");

        updateHelpLabel();
        updateScore();

        /*
        ist nützlich
         https://stackoverflow.com/questions/12499269/javafx-tableview-detect-a-doubleclick-on-a-cell
        */
        Callback<TableColumn, TableCell> cellFactory =
                new Callback<TableColumn, TableCell>() {
                    public TableCell call(TableColumn p) {
                        TableCell<ScoreRow, String> cell = new TableCell<ScoreRow, String>() {
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);

                                if (item != null && item.contains("R")) {
                                    setStyle("-fx-text-fill: red;");//ergebnisslistenfarbe der schrift bevor bestätigung
                                } else if (item != null) {
                                    setStyle("-fx-text-fill: black;");//ergebnisslistenfarbe der schrift bevor bestätigung
                                }
                                setText("Y");
                                setText(empty ? null : getString().replace("R", ""));
                                setGraphic(null);
                            }

                            private String getString() {
                                return getItem() == null ? "" : getItem();
                            }
                        };

                        // click event hinzufügen
                        cell.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                            if (event.getClickCount() > 1) {
                                TableCell c = (TableCell) event.getSource();
                                System.out.println("TEST Row Funktion " + c.getTableRow().getIndex());
                                if (c.getTableRow().getIndex() < MAX_SCORE_NAME) {
                                    clickCell(c.getTableRow().getIndex());
                                } else {
                                    // when zu viele bereichen angeklickt wurden kann kein weiterer geklickt werden
                                    setHelpPanel("Hier bringt dir das klicken nichts!");
                                    System.out.println("Test Bereich kann nicht angeklickt werden!");
                                }
                            }
                        });
                        return cell;
                    }
                };

        // spalten auf gesamte breite
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        player1.setCellFactory(cellFactory);


        //sortierung deaktiveren, da sonst [] durcheinander gebracht werden
        player1.setSortable(false);


        scoreName.setSortable(false);


        for (int player = 1; player < MAX_PLAYERS; player++) {
            TableColumn playerN = new TableColumn<>("Spieler " + (player + 1));
            playerN.setCellValueFactory(new PropertyValueFactory<>("player" + (player + 1)));
            playerN.setCellFactory(cellFactory);
            playerN.setSortable(false);
            tableView.getColumns().add(playerN);
        }


        //.setCellFactory(cellFactory);
        // würfel zurücksetzen
        this.resetDice();
    }

    private void updateDices() {
        dice1.setImage(this.img[this.gfx.rou.dices[0].value() - 1]);
        dice2.setImage(this.img[this.gfx.rou.dices[1].value() - 1]);
        dice3.setImage(this.img[this.gfx.rou.dices[2].value() - 1]);
        dice4.setImage(this.img[this.gfx.rou.dices[3].value() - 1]);
        dice5.setImage(this.img[this.gfx.rou.dices[4].value() - 1]);
    }

    private void updateHelpLabel() {
        if (this.gfx.rou.throwsLeft == 3) {
            scoreLabel.setText("Klicke  auf \"Würfeln\" um zu beginnen.");
        } else if (this.gfx.rou.throwsLeft == 0) {
            scoreLabel.setText("Wähle deinen Zug aus, in dem du in die Tabelle klickst.\n"
            + "               Alle Würfe wurden bereis verwendet.");


        } else {
            scoreLabel.setText("Klicke auf die Würfel, welche du behalten willst.\n" +
                    "                    Du hast " + this.gfx.rou.throwsLeft + " Würfe übrig.");
        }
    }

    //Verarbeitet Klick auf eine Zelle. GUI für neue Runde zurücksetzen.

    private void clickCell(int row) {
        if (gfx.scoreSelect(row)) {
            // setzt gui bei neuer runde zurück
            updateScoreRealOnly(gfx.scoreboardArr);
            updateHelpLabel();
            resetDice();
            updateDices();
            reRollButton.setDisable(false);
            if (gfx.isGameOver()) {
                this.gameOverDialog();
            }
            //fehler abfangen
        } else {
            System.out.println("Hier bringt dir das Klicken nichts!!");
            setHelpPanel("Der Bonus wird dir automatisch gutgeschrieben!!");
        }
    }

    // updatet würfel, ergebnis , text button und Spiellogik
    @FXML
    protected void rollEvent(ActionEvent event) {
        this.gfx.reRoll();
        this.moveToRolling();
        this.updateHelpLabel();
        this.updateDices();
        gewurfeltCounter += 1;
        this.updateScore(this.gfx.scoreboardArr, this.gfx.currentPlayer);
        //button deaktivieren , wenn kein Zug mehr übrig ist
        if (this.gfx.rou.throwsLeft == 0) {
            scoreLabel.setText("Du hattest schon 3 mal gewürfelt!");

        }
    }

    //alle würfel werfen, welche nicht "behalten" werden
    private void moveToRolling() {
        moveDiceToRolling(dice1, 0);
        moveDiceToRolling(dice2, 1);
        moveDiceToRolling(dice3, 2);
        moveDiceToRolling(dice4, 3);
        moveDiceToRolling(dice5, 4);
    }

    private void moveDiceToRolling(ImageView dice1, int index) {
        if (this.gfx.rou.dices[index].keep() && keptDice.getChildren().contains(dice1)) {
            keptDice.getChildren().remove(dice1);
            rollingDice.getChildren().add(dice1);
        }
    }

    // ergebniss anzeigen, letztes ergebniss löschen
    private void updateScore(Score[] scoreboards, int currentPlayer) {
        tableView.getItems().clear();
        int[][] scores = new int[MAX_PLAYERS][];
        for (int player = 0; player < MAX_PLAYERS; player++) {
            scores[player] = scoreboards[player].getScore();
        }
        int[] possibleScore = scoreboards[0].getMaxScore();

        for (int i = 0; i < MAX_SCORE_NAME; i++) {
            String[] scoreText = new String[MAX_PLAYERS];

            for (int player = 0; player < MAX_PLAYERS; player++) {
                String textPossibleScore = possibleScore[i] == 0 ? "" : Integer.toString(possibleScore[i]);
                String textRealScore = scores[player][i] == -1 ? "" : Integer.toString(scores[player][i]);
                if (currentPlayer == player && "".equals(textRealScore) && !"".equals(textPossibleScore)) {
                    scoreText[player] = textPossibleScore + "R";
                } else {
                    scoreText[player] = textRealScore;
                }
            }
            tableView.getItems().add(new ScoreRow(ScoreUtils.lower(i + 1), scoreText));
        }

    }

    // ergbniss anzeigen ohne klickbare fläche
    private void updateScoreRealOnly(Score[] scoreboards) {
        tableView.getItems().clear();


        // ergebnisliste auch bei mehreren Spielern anzeigen

//TODO Namen selber bestimmen können
        int[][] scores = new int[MAX_PLAYERS][];
        for (int player1 = 0; player1 < MAX_PLAYERS; player1++) {
            scores[player1] = scoreboards[player1].getScore();
        }

        for (int player2 = 0; player2 < MAX_PLAYERS; player2++) {
            scores[player2] = scoreboards[player2].getScore();
        }

        for (int player3 = 0; player3 < MAX_PLAYERS; player3++) {
            scores[player3] = scoreboards[player3].getScore();
        }

        for (int player4 = 0; player4 < MAX_PLAYERS; player4++) {
            scores[player4] = scoreboards[player4].getScore();
        }

        for (int player5 = 0; player5 < MAX_PLAYERS; player5++) {
            scores[player5] = scoreboards[player5].getScore();
        }


        for (int i = 0; i < MAX_SCORE_NAME; i++) {
            String[] scoreText = new String[MAX_PLAYERS];
            for (int player1 = 0; player1 < MAX_PLAYERS; player1++) {
                String textRealScore = scores[player1][i] == -1 ? "" : Integer.toString(scores[player1][i]);
                scoreText[player1] = textRealScore;
            }
            tableView.getItems().add(new ScoreRow(ScoreUtils.lower(i + 1), scoreText));
        }

        for (int i = 0; i < MAX_SCORE_NAME; i++) {
            String[] scoreText = new String[MAX_PLAYERS];
            for (int player2 = 0; player2 < MAX_PLAYERS; player2++) {
                String textRealScore = scores[player2][i] == -1 ? "" : Integer.toString(scores[player2][i]);
                scoreText[player2] = textRealScore;
            }
            tableView.getItems().add(new ScoreRow(ScoreUtils.lower(i + 2), scoreText));
        }

        for (int i = 0; i < MAX_SCORE_NAME; i++) {
            String[] scoreText = new String[MAX_PLAYERS];
            for (int player3 = 0; player3 < MAX_PLAYERS; player3++) {
                String textRealScore = scores[player3][i] == -1 ? "" : Integer.toString(scores[player3][i]);
                scoreText[player3] = textRealScore;
            }
            tableView.getItems().add(new ScoreRow(ScoreUtils.lower(i + 3), scoreText));
        }

        for (int i = 0; i < MAX_SCORE_NAME; i++) {
            String[] scoreText = new String[MAX_PLAYERS];
            for (int player4 = 0; player4 < MAX_PLAYERS; player4++) {
                String textRealScore = scores[player4][i] == -1 ? "" : Integer.toString(scores[player4][i]);
                scoreText[player4] = textRealScore;
            }
            tableView.getItems().add(new ScoreRow(ScoreUtils.lower(i + 4), scoreText));
        }

        for (int i = 0; i < MAX_SCORE_NAME; i++) {
            String[] scoreText = new String[MAX_PLAYERS];
            for (int player5 = 0; player5 < MAX_PLAYERS; player5++) {
                String textRealScore = scores[player5][i] == -1 ? "" : Integer.toString(scores[player5][i]);
                scoreText[player5] = textRealScore;
            }
            tableView.getItems().add(new ScoreRow(ScoreUtils.lower(i + 5), scoreText));
        }

    }

    // leeres Ergebnisfeld
    private void updateScore() {
        tableView.getItems().clear();
        for (int i = 0; i < MAX_SCORE_NAME; i++) {
            tableView.getItems().add(new ScoreRow(ScoreUtils.lower(i + 1), "", ""
            ));
        }
    }

    public void diceClick(MouseEvent mouseEvent) {
        if (this.gfx.rou.throwsLeft == 3 || this.gfx.rou.throwsLeft == 0) {
            //debuggen
            System.out.println("Es wurde noch nicht gewürfelt.");
            setHelpPanel("Du musst zuerst Würfeln. Drück dafür 'Würfeln' ");
            return;
        }

        int id = 0;
        ImageView diceImg;
        switch (mouseEvent.getPickResult().getIntersectedNode().getId()) {
            case "dice1":
                diceImg = dice1;
                id = 1;
                break;
            case "dice2":
                id = 2;
                diceImg = dice2;
                break;
            case "dice3":
                id = 3;
                diceImg = dice3;
                break;
            case "dice4":
                id = 4;
                diceImg = dice4;
                break;
            case "dice5":
                diceImg = dice5;
                id = 5;
                break;
            default:
                diceImg = dice1;
                break;
        }

        // Bereich für Würfel ändern, falls erforderlich
        if (rollingDice.getChildren().contains(diceImg)) {
            rollingDice.getChildren().remove(diceImg);
            keptDice.getChildren().add(diceImg);
        } else {
            keptDice.getChildren().remove(diceImg);
            rollingDice.getChildren().add(diceImg);
        }
        //ausgabe zu debug zwecken
        this.gfx.rou.toggleKeeperSolo(id);
        //System.out.println("Würfel übernommen ");
    }

    //    Alle Würfel wieder in eine Linie bringen

    private void resetDice() {
        if (rollingDice.getChildren().contains(dice1)) {
            rollingDice.getChildren().remove(dice1);
            keptDice.getChildren().add(dice1);
        }
        if (rollingDice.getChildren().contains(dice2)) {
            rollingDice.getChildren().remove(dice2);
            keptDice.getChildren().add(dice2);
        }
        if (rollingDice.getChildren().contains(dice3)) {
            rollingDice.getChildren().remove(dice3);
            keptDice.getChildren().add(dice3);
        }
        if (rollingDice.getChildren().contains(dice4)) {
            rollingDice.getChildren().remove(dice4);
            keptDice.getChildren().add(dice4);
        }
        if (rollingDice.getChildren().contains(dice5)) {
            rollingDice.getChildren().remove(dice5);
            keptDice.getChildren().add(dice5);
        }
    }

    private void gameOverDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        int win = gfx.getWinner();
        if (win < 1) {
            alert.setHeaderText("Unentschieden!");
        } else {
            alert.setHeaderText("Spieler " + win + " hat gewonnen!");
        }

        alert.setTitle("Das Spiel ist vorbei!");
        alert.setContentText("Ich hoffe dir hat es Spaß gemacht!");

        ButtonType buttonTypeOne = new ButtonType("Erneut spielen.");
        ButtonType buttonTypeCancel = new ButtonType("Verlassen", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeOne) {
            // Neues Game Starten
            this.gfx = new GameEngine(MAX_PLAYERS);
            this.updateHelpLabel();
            // ergebnissanzeige nur mit label hinzufügen
            this.updateScore();
            this.resetDice();

        } else {
            exitToMenu();
        }
    }

    public void exitToMenu(MouseEvent mouseEvent) {
        exitToMenu();
    }

    private void exitToMenu() {
        try {
            //fxml laden
            Parent root = FXMLLoader.load(getClass().getResource("/Menu.fxml"));
            // zurück zur stage
            Stage app_stage = (Stage) (title.getScene().getWindow());
            // szene hinzufügen + css  + szene setzen
            Scene menu_scene = new Scene(root);
            menu_scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            app_stage.setScene(menu_scene);
            app_stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void hideHelpPanel(ActionEvent event)
    {
        hideHelpPanel();
    }

    public void hideHelpPanel() {
        helpGrid.setVisible(false);
        helpGrid.setManaged(false);
    }

    private void setHelpPanel(String text) {
        helpGrid.setVisible(true);
        helpGrid.setManaged(true);
        helpLabel.setText(text);
    }
}
