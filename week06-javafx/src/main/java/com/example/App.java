package com.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** First JavaFX window, built entirely in Java. */
public class App extends Application {
    @Override
    public void start(Stage stage) {
        Label message = new Label("My First JavaFX App");
        message.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button clickButton = new Button("Click Me!");
        clickButton.setOnAction(event -> {
            message.setText("Button was clicked!");
            System.out.println("Button clicked on: " + Thread.currentThread().getName());
        });

        VBox root = new VBox(15, message, clickButton);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 24px; -fx-background-color: #f3f6fb;");

        stage.setTitle("JavaFX Programmatic App");
        stage.setScene(new Scene(root, 420, 280));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
