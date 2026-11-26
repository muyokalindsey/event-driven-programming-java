# Week 6 — Self-Assessment and Evidence

**Course:** Event-Driven Programming in Java  
**Week:** 6 — Introduction to Modern JavaFX

## Learning outcomes

| Outcome | Evidence in this project | Status |
|---|---|---|
| Create a JavaFX application with a window and UI controls | `src/main/java/com/example/App.java` builds a Stage, Scene, Label, and Button. | Implemented |
| Build layouts programmatically and with FXML | `LayoutDemo.java` uses HBox, VBox, GridPane, and BorderPane. `src/main/resources/com/example/login.fxml` defines the login view. | Implemented |
| Describe JavaFX architecture and threading | App.java prints the thread name when the button is clicked. UI event handlers run on the JavaFX Application Thread; long-running work should be moved off that thread. | Demonstrated |
| Connect a controller and build a complete application | FXMLApp loads login.fxml; LoginController handles validation; DashboardApp is the dashboard capstone. | Implemented |

## Activity evidence

1. **First JavaFX window:** `App.java` updates a label after a button click.
2. **Layouts:** `LayoutDemo.java` combines a top bar, sidebar, and grid.
3. **FXML design:** `login.fxml` defines the login controls and controller connection.
4. **FXML controller:** `LoginController.java` validates blank fields and the demo credentials.
5. **Dashboard capstone:** `DashboardApp.java` provides a sidebar and sample KPI cards.

## Self-check answers

- **Stage vs. Scene:** The Stage is the top-level window. The Scene holds the visual content attached to that window.
- **JavaFX UI thread:** The JavaFX Application Thread. UI controls should be updated on this thread; long operations should not block it.
- **FXML connection:** The `fx:controller` attribute selects the controller, `fx:id` connects controls to fields annotated with `@FXML`, and `onAction` connects a button to a handler.

## Run status

The source and FXML files are present in the Week 6 Maven project. VS Code’s direct **Run Java** action reported that JavaFX runtime components were missing. The Maven extension also reported that it could not find a Maven executable, so the JavaFX windows have not yet been launched. VS Code currently shows two Java environment warnings because the project targets Java 17 while the installed JRE is Java 25.

**Personal self-rating (1–5):** left for the student to complete.
