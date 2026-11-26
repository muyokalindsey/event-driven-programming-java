# Week 7 — Event Handling in JavaFX

This module demonstrates event filters and handlers, mouse and keyboard input, drag and drop, and a custom event. Each activity is a separate JavaFX application.

## Requirements

- JDK 17 or later
- Maven 3.8 or later
- Internet access on the first Maven run so dependencies can be downloaded

Run commands from this folder:

```powershell
mvn clean javafx:run
mvn clean javafx:run "-Dmain.class=com.example.MouseKeyDemo"
mvn clean javafx:run "-Dmain.class=com.example.DragDropDemo"
mvn clean javafx:run "-Dmain.class=com.example.CustomEventDemo"
```

The default `EventPropagationDemo` prints the filter, target handler, and bubbling handler order when its button is clicked. Uncomment `e.consume()` in its filter to stop propagation. `MouseKeyDemo` reports pointer coordinates and moves a circle with the arrow keys; click the pane if it does not have keyboard focus. `DragDropDemo` accepts the sample label in its drop zone. `CustomEventDemo` fires a `LoginSuccessEvent` for a non-empty username; it demonstrates event wiring and is not real authentication.
