package yahtzee;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setMinHeight(1000);
        primaryStage.setMinWidth(1000);
        Parent root = FXMLLoader.load(getClass().getResource("/Menu.fxml"));


        Scene scene = new Scene(root, 1000, 1000);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Kniffel");
        primaryStage.getIcons().add(new Image("/Logo.png"));
        primaryStage.show();
    }
}
