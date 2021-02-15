package de.azubi.kniffel.gui.fx;


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

public class MenuController {
    public Button singlePlayerButton;
    public Button multiPlayerButton;
    public Button exitButton;
    public Spinner spinner;
    public Button multiPlayerLaunchButton;
    public Label highscore;

    public void initialize() {
        spinner.setVisible(false);
        spinner.setManaged(false);
        multiPlayerLaunchButton.setVisible(false);
        multiPlayerLaunchButton.setManaged(false);
    }

    private void launchGame(ActionEvent event, int playerNumber) {
        // TODO: Hier Bitte noch die FXML Datei implementieren
        FXMLLoader loader = new FXMLLoader(getClass().getResource(""));
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

        // TODO: Hier die CSS Datei der Stylesheets noch implenetieren
        newgame_scene.getStylesheets().add(getClass().getResource("").toExternalForm());
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(newgame_scene);
        app_stage.show();
    }

    public void singlePlayer(ActionEvent event) {
        launchGame(event, 1);
    }

    public void multiPlayer(javafx.event.ActionEvent event) {
        spinner.setVisible(true);
        spinner.setManaged(true);
        multiPlayerLaunchButton.setVisible(true);
        multiPlayerLaunchButton.setManaged(true);
    }

    public void multiPlayerLaunch(ActionEvent event) {
        launchGame(event, (Integer) spinner.getValue());
    }

    public void exit(ActionEvent event) {
        Platform.exit();
    }
}
