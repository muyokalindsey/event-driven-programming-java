package com.university;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Background Task that loads all students from MySQL without blocking the UI thread
public class LoadStudentsTask extends Task<ObservableList<Student>> {
    @Override
    protected ObservableList<Student> call() throws Exception {
        ObservableList<Student> students = FXCollections.observableArrayList();
        String sql = "SELECT student_id, registration_number, first_name, last_name FROM students";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("student_id"),
                        rs.getString("registration_number"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                );
                students.add(student);
            }
        }
        return students;
    }
}
