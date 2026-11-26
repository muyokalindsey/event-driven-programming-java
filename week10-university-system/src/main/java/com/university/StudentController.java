package com.university;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class StudentController {
    @FXML private TextField regField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, String> colReg;
    @FXML private TableColumn<Student, String> colFirst;
    @FXML private TableColumn<Student, String> colLast;
    @FXML private Label statusLabel;
    @FXML private Button saveButton;

    private final ObservableList<Student> studentList = FXCollections.observableArrayList();
    private final SimpleBooleanProperty saving = new SimpleBooleanProperty(false);

    @FXML
    public void initialize() {
        colReg.setCellValueFactory(cellData -> cellData.getValue().regNumberProperty());
        colFirst.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        colLast.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        studentTable.setItems(studentList);

        saveButton.disableProperty().bind(
                regField.textProperty().isEmpty()
                        .or(firstNameField.textProperty().isEmpty())
                        .or(lastNameField.textProperty().isEmpty())
                        .or(saving)
        );

        loadStudents(false);
    }

    @FXML
    private void handleSaveStudent() {
        String reg = regField.getText().trim();
        String fName = firstNameField.getText().trim();
        String lName = lastNameField.getText().trim();
        if (reg.isEmpty() || fName.isEmpty() || lName.isEmpty()) {
            statusLabel.setText("Enter a registration number, first name, and last name.");
            return;
        }

        saving.set(true);
        statusLabel.setText("Saving student...");

        Task<Void> saveTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                String sql = "INSERT INTO students (registration_number, first_name, last_name, email) "
                        + "VALUES (?, ?, ?, ?)";
                try (Connection conn = DatabaseUtil.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, reg);
                    stmt.setString(2, fName);
                    stmt.setString(3, lName);
                    stmt.setString(4, reg.toLowerCase() + "@university.edu");
                    stmt.executeUpdate();
                }
                return null;
            }
        };

        saveTask.setOnSucceeded(e -> {
            saving.set(false);
            regField.clear();
            firstNameField.clear();
            lastNameField.clear();
            loadStudents(true);
        });
        saveTask.setOnFailed(e -> {
            saving.set(false);
            Throwable ex = saveTask.getException();
            statusLabel.setText("Save failed: " + (ex != null ? ex.getMessage() : "Unknown error"));
        });

        Thread thread = new Thread(saveTask, "save-student-task");
        thread.setDaemon(true);
        thread.start();
    }

    private void loadStudents(boolean followsSuccessfulSave) {
        LoadStudentsTask task = new LoadStudentsTask();
        task.setOnSucceeded(e -> {
            studentList.setAll(task.getValue());
            String count = "Loaded " + studentList.size() + " students from database.";
            statusLabel.setText(followsSuccessfulSave
                    ? "Student saved successfully. " + count
                    : count);
        });
        task.setOnFailed(e -> {
            Throwable ex = task.getException();
            String detail = ex != null ? ex.getMessage() : "Unknown error";
            statusLabel.setText(followsSuccessfulSave
                    ? "Student saved, but the list could not be refreshed: " + detail
                    : "DB connection error: " + detail);
        });

        Thread thread = new Thread(task, "load-students-task");
        thread.setDaemon(true);
        thread.start();
    }
}
