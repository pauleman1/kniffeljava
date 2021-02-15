package de.azubi.kniffel.gui.fx;

import javafx.application.Application;
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
        primaryStage.setTitle("Kniffel");
        primaryStage.show();
    }
}
