package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/** Week 6 capstone dashboard combining JavaFX layouts and controls. */
public class DashboardApp extends Application {
    private Label welcome;

    @Override
    public void start(Stage stage) {
        Label brand = new Label("NORTHSTAR  /  WORKSPACE");
        brand.setTextFill(Color.WHITE);
        brand.setFont(Font.font("System", 17));
        Label account = new Label("●  Alex Morgan");
        account.setTextFill(Color.web("#cbd5e1"));
        HBox topBar = new HBox(brand, account);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setSpacing(24);
        HBox.setHgrow(brand, Priority.ALWAYS);
        topBar.setPadding(new Insets(18, 26, 18, 26));
        topBar.setStyle("-fx-background-color: #172554;");

        VBox sidebar = new VBox(8);
        sidebar.setPadding(new Insets(22, 14, 22, 14));
        sidebar.setPrefWidth(190);
        sidebar.setStyle("-fx-background-color: #f1f5f9;");
        for (String name : new String[] {"Overview", "Reports", "Customers", "Settings"}) {
            Button item = new Button(name);
            item.setMaxWidth(Double.MAX_VALUE);
            item.setAlignment(Pos.CENTER_LEFT);
            item.setStyle("-fx-background-color: transparent; -fx-padding: 11 12; -fx-font-size: 14px;");
            item.setOnAction(event -> welcome.setText(name + " at a glance"));
            sidebar.getChildren().add(item);
        }

        welcome = new Label("Good morning, Alex");
        welcome.setFont(Font.font("System", 26));
        Label subtitle = new Label("Here is what is happening with your workspace today.");
        subtitle.setTextFill(Color.web("#64748b"));
        VBox heading = new VBox(7, welcome, subtitle);

        HBox cards = new HBox(16,
                createCard("Monthly revenue", "$12,450", "+8.2% this month", "#2563eb"),
                createCard("Active customers", "1,204", "+4.6% this month", "#0f766e"),
                createCard("Open orders", "342", "28 need attention", "#c2410c"));
        for (var node : cards.getChildren()) HBox.setHgrow(node, Priority.ALWAYS);

        VBox content = new VBox(24, heading, cards);
        content.setPadding(new Insets(34));
        content.setStyle("-fx-background-color: #f8fafc;");

        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setLeft(sidebar);
        root.setCenter(content);
        stage.setTitle("Northstar Workspace — Dashboard");
        stage.setMinWidth(760);
        stage.setMinHeight(480);
        stage.setScene(new Scene(root, 980, 620));
        stage.show();
    }

    private VBox createCard(String title, String value, String detail, String accent) {
        Label titleLabel = new Label(title);
        titleLabel.setTextFill(Color.web("#64748b"));
        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("System", 30));
        Label detailLabel = new Label(detail);
        detailLabel.setTextFill(Color.web(accent));
        VBox card = new VBox(12, titleLabel, valueLabel, detailLabel);
        card.setMinWidth(170);
        card.setPadding(new Insets(20));
        card.setStyle("-fx-background-color: white; -fx-background-radius: 12;"
                + "-fx-border-color: #e2e8f0; -fx-border-radius: 12;");
        return card;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
