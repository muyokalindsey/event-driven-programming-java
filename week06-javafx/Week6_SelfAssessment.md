# Week 6 Final Self-Assessment & Summary

**Course:** Event-Driven Programming in Java  
**Week:** 6 — Introduction to Modern JavaFX  
**Date Completed:** 2026-11-26  
**Submission:** [Week 6 on GitHub](https://github.com/muyokalindsey/event-driven-programming-java/tree/main/week06-javafx)

This assessment records implementation evidence from the submitted source and project files. Ratings describe how fully each activity is represented in the repository; they do not claim that the JavaFX windows were launched on this computer.

---

## Learning Outcomes Checklist

| # | Outcome | Evidence | Implementation rating |
|---|---------|----------|-----------------------|
| 1 | Create a JavaFX window with controls | `App.java` creates a `Stage`, `Scene`, `VBox`, `Label`, and `Button`; the button updates the label. | 5 / 5 |
| 2 | Arrange controls with JavaFX layouts and FXML | `LayoutDemo.java` demonstrates `HBox`, `VBox`, `GridPane`, and `BorderPane`; `login.fxml` defines the login view. | 5 / 5 |
| 3 | Explain JavaFX architecture and threading | `App.java` reports the handler thread; the self-check below explains the JavaFX Application Thread and why long work should stay off it. | 5 / 5 |
| 4 | Connect an FXML view to a controller | `login.fxml` names `LoginController`, injects controls with `fx:id`, and injects the button with `fx:id="loginButton"`, and connects it with `onAction`. | 5 / 5 |
| 5 | Build a complete JavaFX dashboard | `DashboardApp.java` combines a top bar, navigation sidebar, content area, KPI cards, and working heading changes. | 5 / 5 |

---

## Activity Reflections

### Activity 1 — First JavaFX Window
The first window shows how JavaFX builds an interface from nodes, places them in a layout, and attaches the layout to a scene and stage. The button handler changes the label and prints the current thread name, making the event and thread relationship visible in the code.

### Activity 2 — JavaFX Layouts
The layouts demo gives each pane a clear role: the `HBox` forms the header, the `VBox` stacks navigation buttons, the `GridPane` arranges tiles, and the `BorderPane` assembles the whole window. This keeps positioning responsive to the container instead of relying on fixed coordinates.

### Activity 3 — FXML View
The login screen separates view markup from Java logic. Its `VBox`, prompt fields, feedback label, and button are defined in `login.fxml`; the file is ready to open and adjust in Scene Builder.

### Activity 4 — FXML Controller
`LoginController` handles empty input, checks the demonstration credentials, shows feedback, and clears the fields after a successful demo login. The example demonstrates controller wiring and validation; it is not connected to a real account store.

### Activity 5 — Dashboard Capstone
`DashboardApp` applies the layout concepts to a dashboard with navigation and three sample KPI cards. Each sidebar button updates the page heading, so the navigation has visible event-driven behavior. The displayed figures are sample data.

---

## Key Concepts

- `Stage` is the top-level window; a `Scene` contains the visual content attached to it.
- Controls and layout panes are `Node` objects arranged in a scene graph.
- JavaFX event handlers run on the JavaFX Application Thread; long-running work should use a background task and return UI updates to that thread.
- `HBox`, `VBox`, `GridPane`, and `BorderPane` arrange controls for different layout needs.
- FXML separates view structure from controller behavior. `fx:controller`, `fx:id`, `@FXML`, and `onAction` connect them.
- A demonstration login and dashboard figures are not authentication or production data.

---

## Self-Assessment Answers

1. **What is the difference between a Stage and a Scene?**  
   A `Stage` is the application window. A `Scene` holds the content displayed in that window. A stage displays one scene at a time, and the scene can be changed.

2. **What is the JavaFX main UI thread called?**  
   The JavaFX Application Thread. UI controls should be updated on it; long-running work should not block it.

3. **How does FXML connect the view to its controller?**  
   The `fx:controller` attribute identifies the controller class. An `fx:id` matches a view control to an `@FXML` field, and `onAction` connects a control event to a controller method.

4. **What does `@FXML` do?**  
   It allows the FXML loader to inject a view element into a controller field or expose a handler method to the FXML document.

5. **Why use FXML instead of writing the entire interface in Java?**  
   FXML keeps view structure separate from application logic and can be edited visually in Scene Builder without changing controller code.

---

## Completion Checklist

- [x] Activity 1: `App.java` — programmatic window, button action, and thread output
- [x] Activity 2: `LayoutDemo.java` — `HBox`, `VBox`, `GridPane`, and `BorderPane`
- [x] Activity 3: `login.fxml` — login view with controller and control identifiers
- [x] Activity 4: `LoginController.java` and `FXMLApp.java` — FXML loading, validation, and feedback
- [x] Activity 5: `DashboardApp.java` — dashboard capstone with interactive navigation
- [x] Maven project and run instructions: `pom.xml` and `README.md`

**Evidence score:** 25 / 25 implementation points (five activities × five points). The score is based on the files and behaviors represented in the repository.

---

## GitHub Submission and Run Status

The Week 6 project, documentation, and self-assessment are published in the private repository at [muyokalindsey/event-driven-programming-java](https://github.com/muyokalindsey/event-driven-programming-java/tree/main/week06-javafx). The repository includes the Week 1–5 work as well.

The JavaFX windows were not launched locally for this submission. The source, FXML, Maven configuration, and run instructions are present; this document does not claim runtime verification.
