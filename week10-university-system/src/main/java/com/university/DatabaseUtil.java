package com.university;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Handles the JDBC connection bridge between JavaFX and MySQL
public class DatabaseUtil {
    private static final String URL = System.getenv().getOrDefault(
            "UNIVERSITY_DB_URL",
            "jdbc:mysql://localhost:3306/university_db"
    );
    private static final String USER = System.getenv().getOrDefault(
            "UNIVERSITY_DB_USER",
            "root"
    );
    private static final String PASSWORD = System.getenv().getOrDefault(
            "UNIVERSITY_DB_PASSWORD",
            "your_password"
    );

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
