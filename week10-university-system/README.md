# Week 10 — University Management System

This capstone combines JavaFX navigation and FXML, a property-backed student table, background JDBC tasks, a MySQL schema, and a scheduled heartbeat.

## Requirements

- JDK 17 or later
- Maven 3.8 or later
- MySQL Server and a MySQL client such as MySQL Workbench

## Database Setup

1. Start MySQL and run `sql/university_db.sql`. It creates `university_db`, the `programs` and `students` tables, and two sample programs. The sample program inserts can be run again safely.
2. In the same PowerShell session used to launch Maven, set the connection settings. Replace the example password with the password configured for your MySQL account:

   ```powershell
   $env:UNIVERSITY_DB_URL = "jdbc:mysql://localhost:3306/university_db"
   $env:UNIVERSITY_DB_USER = "root"
   $env:UNIVERSITY_DB_PASSWORD = "your_mysql_password"
   ```

   The URL defaults to the value above if `UNIVERSITY_DB_URL` is omitted. Set all three values for a non-default MySQL setup.

## Build and Run

From this directory:

```powershell
mvn clean package
mvn clean javafx:run
```

The Dashboard and Students buttons switch the center view. In Students, provide a unique registration number plus first and last name. The app generates a demo email address from the registration number. Saving and loading happen in background JavaFX tasks; a heartbeat message is printed in the console every five seconds.

## Notes

- `DatabaseUtil` reads connection settings from environment variables; do not commit real database passwords.
- Registration numbers and generated email addresses must be unique because the schema enforces unique constraints.
- The heartbeat is a background console demonstration and is shut down when the JavaFX application stops.
- MySQL integration requires a running database and valid credentials; a successful Java compilation does not verify the database connection.
