# Week 10 — Final Capstone Self-Assessment

**Course:** Event-Driven Programming in Java

**Week:** 10 — University Management System

**Evidence basis:** Source, FXML, SQL, and Maven configuration in this repository. Ratings describe implementation coverage; they do not claim that MySQL or the JavaFX application was launched here.

## Learning Outcomes

| # | Outcome | Evidence | Rating |
|---|---|---|---:|
| 1 | Build a multi-view JavaFX application | `MainView.fxml`, `MainController.java`, and `StudentView.fxml` provide a navigation shell and student-management view. | 5 / 5 |
| 2 | Represent data with JavaFX properties | `Student.java` exposes JavaFX properties used by `TableView` cell value factories. | 5 / 5 |
| 3 | Keep database work off the UI thread | `LoadStudentsTask.java` loads records in a `Task`; `StudentController.java` inserts records in a separate background `Task`. | 5 / 5 |
| 4 | Connect JavaFX to MySQL and manage a scheduled task | `DatabaseUtil.java` uses JDBC and environment-configurable settings; SQL creates the schema; `App.java` schedules and shuts down the heartbeat executor. | 5 / 5 |

## Capstone Reflection

The app combines FXML navigation with a property-backed table. Student reads and inserts run in background tasks so database delays do not block interaction. Save is disabled while fields are empty or a save is in progress. Status messages distinguish a successful save from a later refresh failure. The scheduled heartbeat runs off the UI thread and its executor is stopped when the application closes.

## Self-Check Answers

1. **Why use JavaFX properties in the student model?** `TableView` can observe the property values and display them through cell value factories.
2. **Why does database access use background tasks?** JDBC work can take time; running it on the JavaFX Application Thread would freeze the UI.
3. **How does the view connect to its controller?** FXML names the controller, `fx:id` values inject controls into `@FXML` fields, and `onAction` invokes controller methods.
4. **How are database settings supplied?** `UNIVERSITY_DB_URL`, `UNIVERSITY_DB_USER`, and `UNIVERSITY_DB_PASSWORD` can be set in the environment before launching.
5. **What happens to the scheduled heartbeat on exit?** `Application.stop()` calls `shutdownNow()` on its scheduler.

## Completion Evidence and Limitations

- [x] FXML shell with Dashboard and Students navigation
- [x] Student model, form, and table
- [x] Background database load and insert tasks with status handling
- [x] MySQL schema and sample program seed data
- [x] Scheduled heartbeat with shutdown handling
- [x] Setup and run instructions in `README.md`

**Implementation evidence score:** 20 / 20. Database integration requires a local MySQL server and valid environment credentials and was not exercised here. The SQL sample program inserts are safe to rerun; student registration numbers and generated demo email addresses must remain unique.
