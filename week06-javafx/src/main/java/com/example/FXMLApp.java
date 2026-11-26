package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

/** Loads the login screen designed in FXML. */
public class FXMLApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/login.fxml"));
        stage.setTitle("JavaFX Login");
        stage.setScene(new Scene(root, 420, 390));
        stage.setMinWidth(380);
        stage.setMinHeight(350);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
