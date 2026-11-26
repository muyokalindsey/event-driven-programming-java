# Week 6 — Introduction to Modern JavaFX

This module contains the five Week 6 activities: a programmatic JavaFX window, layout panes, an FXML login screen and controller, and a dashboard capstone.

## Requirements

- JDK 17 or later
- Maven 3.8 or later
- Internet access on the first Maven run so dependencies can be downloaded

From this directory, run the activity you want with:

```powershell
mvn clean javafx:run
mvn clean javafx:run "-Dmain.class=com.example.LayoutDemo"
mvn clean javafx:run "-Dmain.class=com.example.FXMLApp"
mvn clean javafx:run "-Dmain.class=com.example.DashboardApp"
```

The default entry point (`com.example.App`) demonstrates `Stage`, `Scene`, `Node`, a button action, and the JavaFX Application Thread. `LayoutDemo` demonstrates `HBox`, `VBox`, `GridPane`, and `BorderPane`. `FXMLApp` loads `src/main/resources/com/example/login.fxml`, whose controls are connected to `LoginController`. The demo login is `admin` / `password123`; it is sample UI behavior and does not authenticate against a real account store.

The dashboard sidebar buttons update the page heading. Its sample revenue, customer, and order values are demonstration data.
