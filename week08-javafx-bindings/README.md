# Week 8 — Properties and Bindings in JavaFX

This module covers JavaFX observable properties, invalidation and change listeners, one-way and bidirectional bindings, and a reactive registration form.

## Requirements

- JDK 17 or later
- Maven 3.8 or later
- Internet access on the first Maven run so dependencies can be downloaded

Run commands from this folder:

```powershell
mvn clean javafx:run
mvn clean javafx:run "-Dmain.class=com.example.ListenerDemo"
mvn clean javafx:run "-Dmain.class=com.example.UnidirectionalDemo"
mvn clean javafx:run "-Dmain.class=com.example.BidirectionalDemo"
```

The default `ReactiveFormApp` demonstrates live text bindings and button validation. `Person` exposes both standard JavaBean accessors and JavaFX property accessors. `ListenerDemo` prints listener notifications and exits; the other demos open interactive windows.
