package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/** Controller for the login.fxml view. */
public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Label feedbackLabel;

    @FXML
    private void handleLoginButtonAction() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            feedbackLabel.setText("Enter both your username and password.");
            feedbackLabel.setStyle("-fx-text-fill: #b45309;");
            return;
        }

        if (username.equals("admin") && password.equals("password123")) {
            feedbackLabel.setText("Login successful. Welcome, admin!");
            feedbackLabel.setStyle("-fx-text-fill: #15803d;");
            usernameField.clear();
            passwordField.clear();
        } else {
            feedbackLabel.setText("Login failed. Check your credentials.");
            feedbackLabel.setStyle("-fx-text-fill: #b91c1c;");
        }
    }
}
