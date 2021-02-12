package de.azubi.kniffel.gui.fx;

import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author tz
 */
public class Starter extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Label label = new Label("Platzhalter");


        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10,20,20,20));
        vBox.setSpacing(10);
        vBox.getChildren().add(label);

        Scene scene = new Scene(vBox, 300, 250);

        primaryStage.setTitle("Platzhalter");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
