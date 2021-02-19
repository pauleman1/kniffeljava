package yahtzee;


import de.azubi.kniffel.core.utils.ScoreUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class StatController {

    public Label lThreeOfAKindCounter;
    public Label lFourOfAKindCounter;
    public Label lSmallStraightCounter;
    public Label lLargeStraightCounter;
    public Label lYahtzeeCounter;
    public Label lGewurfeltCounter;
    public Label title;
    public Label lTimerCounter;

    private int threeOfAKind = 0;
    private int fourOfAKind = 0;
    private int smallStraight = 0;
    private int largeStraight = 0;
    private int yahtzee = 0;
    private int gewurfelt = 0;
    private double timediff = 0;


    public StatController(int playerNumber) {
    }

    public StatController() {

    }

    public void initialize() throws InterruptedException {
        threeOfAKind = ScoreUtils.threeOfAKindCounter;
        fourOfAKind = ScoreUtils.fourOfAKindCounter;
        smallStraight = ScoreUtils.smallStraightCounter;
        largeStraight = ScoreUtils.largeStraightCounter;
        yahtzee = ScoreUtils.yahtzeeCounter;
        gewurfelt = GameController.gewurfeltCounter;
        timediff =MenuController.timerDiff;
        lThreeOfAKindCounter.setText(String.valueOf(threeOfAKind)+"x");
        lFourOfAKindCounter.setText(String.valueOf(fourOfAKind)+"x");
        lSmallStraightCounter.setText(String.valueOf(smallStraight)+"x");
        lLargeStraightCounter.setText(String.valueOf(largeStraight)+"x");
        lYahtzeeCounter.setText(String.valueOf(yahtzee)+"x");
        lGewurfeltCounter.setText(String.valueOf(gewurfelt)+"x");
        lTimerCounter.setText(String.valueOf(MenuController.timerDiff)+"s");
    }

    public void exitToMenu(ActionEvent actionEvent) {
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

    public void resetStats(ActionEvent actionEvent) {
        threeOfAKind = 0;
        fourOfAKind = 0;
        smallStraight = 0;
        largeStraight = 0;
        yahtzee = 0;
        gewurfelt = 0;
        timediff = 0;
        lThreeOfAKindCounter.setText(String.valueOf(threeOfAKind)+"x");
        lFourOfAKindCounter.setText(String.valueOf(fourOfAKind)+"x");
        lSmallStraightCounter.setText(String.valueOf(smallStraight)+"x");
        lLargeStraightCounter.setText(String.valueOf(largeStraight)+"x");
        lYahtzeeCounter.setText(String.valueOf(yahtzee)+"x");
        lGewurfeltCounter.setText(String.valueOf(gewurfelt)+"x");
        lTimerCounter.setText(String.valueOf(timediff)+"s");
}
}