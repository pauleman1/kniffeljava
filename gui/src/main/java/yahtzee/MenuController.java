package yahtzee;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController{
    public Button singlePlayerButton;
    public Button multiPlayerButton;
    public Button exitButton;
    public Spinner spinner;
    public Button statistikbutton;
    public Button multiPlayerLaunchButton;
    public static long timerStart = 0;
    public static long timerStop = 0;
    public static long timerDiff = 0;
    public Label highscore;


    public void initialize() {
        // spinner ausblenden (spinner -> auswahl der spielernummern)
        // wird erst eingeblendet wenn multiplayer als spieltyp ausgewählt
        // durch setVisible wird das Element ausgeblendet, mit setManaged wird es aus dem übergeordneten Knoten "entfernt".
        spinner.setVisible(false);
        spinner.setManaged(false);
        multiPlayerLaunchButton.setVisible(false);
        multiPlayerLaunchButton.setManaged(false);
    }

    private void launchGame(ActionEvent event, int playerNumber) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game.fxml"));
        //Wir weisen den Controller hier zu, und nicht in der FXML-Datei, damit wir die Anzahl der Spieler an das Spiel übergeben können
        GameController controller = new GameController(playerNumber);
        loader.setController(controller);

        GridPane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert pane != null;
        Scene newgame_scene = new Scene(pane);
        newgame_scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(newgame_scene);
        app_stage.show();
    }

    private void launchStats(ActionEvent event, int playernumber) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Statistik.fxml"));
        //Wir weisen den Controller hier zu, und nicht in der FXML-Datei, damit wir die Anzahl der Spieler an das Spiel übergeben können
        StatController controller = new StatController(playernumber);
        loader.setController(controller);
        GridPane pane = null;
        try {
            pane = loader.load();
            assert pane != null;
            Scene newgame_scene = new Scene(pane);
            newgame_scene.getStylesheets().add(getClass().getResource("/style2.css").toExternalForm());
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.setScene(newgame_scene);
            app_stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void singlePlayer(ActionEvent event)
    {
        timerStart = System.currentTimeMillis();
        launchGame(event, 1);
    }

    public void multiPlayer(ActionEvent event) {
        spinner.setVisible(true);
        spinner.setManaged(true);
        multiPlayerLaunchButton.setVisible(true);
        multiPlayerLaunchButton.setManaged(true);
    }

    public void multiPlayerLaunch(ActionEvent event)
    {
        timerStart = System.currentTimeMillis();
        launchGame(event, (Integer) spinner.getValue());
    }

    public void stats(ActionEvent event)
    {
        timerStop = System.currentTimeMillis();
        timerDiff = (timerStop - timerStart) / 1000;
        launchStats(event, (2));

    }


    public void exit(ActionEvent actionEvent)
    {
        Platform.exit();
    }



}
