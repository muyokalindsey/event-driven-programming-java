package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** Demonstrates HBox, VBox, GridPane, and BorderPane. */
public class LayoutDemo extends Application {
    @Override
    public void start(Stage stage) {
        Label title = new Label("JavaFX Layouts");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");
        HBox topBar = new HBox(title);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(14, 18, 14, 18));
        topBar.setStyle("-fx-background-color: #172554;");

        VBox sidebar = new VBox(10,
                new Button("Home"), new Button("Profile"), new Button("Settings"));
        sidebar.setPadding(new Insets(16));
        sidebar.setPrefWidth(150);
        sidebar.setStyle("-fx-background-color: #e8eef8;");
        sidebar.getChildren().forEach(node -> ((Button) node).setMaxWidth(Double.MAX_VALUE));

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(12);
        grid.setVgap(12);
        grid.setPadding(new Insets(24));
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = new Button("Tile " + (row * 3 + col + 1));
                button.setPrefSize(110, 58);
                button.setMaxWidth(Double.MAX_VALUE);
                GridPane.setHgrow(button, Priority.ALWAYS);
                grid.add(button, col, row);
            }
        }

        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setLeft(sidebar);
        root.setCenter(grid);
        stage.setTitle("JavaFX Layouts Demo");
        stage.setScene(new Scene(root, 700, 480));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
