# Week 9 — Asynchronous Programming in JavaFX

This module contrasts blocking work on the JavaFX Application Thread with background `Task` work, progress/message bindings, and reusable `Service` tasks.

## Requirements

- JDK 17 or later
- Maven 3.8 or later
- Internet access on the first Maven run so dependencies can be downloaded

Run commands from this folder:

```powershell
mvn clean javafx:run
mvn clean javafx:run "-Dmain.class=com.example.TaskDemo"
mvn clean javafx:run "-Dmain.class=com.example.ProgressDemo"
mvn clean javafx:run "-Dmain.class=com.example.FreezeDemo"
```

The default `ServiceDemo` shows restartable and cancellable work. `TaskDemo` runs a one-time background task. `ProgressDemo` binds progress and status to task properties and recovers controls after success or failure. `FreezeDemo` deliberately sleeps in the UI event handler so the freeze can be observed; it is an anti-pattern demonstration.
