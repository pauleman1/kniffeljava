//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package de.azubi.kniffel.gui.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public Main() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setMinHeight(1000.0D);
        primaryStage.setMinWidth(1000.0D);
        Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("Menu.fxml"));
        Scene scene = new Scene(root, 1000.0D, 1000.0D);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Kniffel");
        primaryStage.getIcons().add(new Image("resources/Logo.png"));
        primaryStage.show();
    }
}
