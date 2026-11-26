# Activity 5: Week 2 Final Self-Assessment & Summary

## Self-Assessment Answers

### Learning Outcome 1: Design and Implement the Observer Pattern
1. **UML Diagram of the Observer Pattern:**
   ```text
   +------------------------+          notifies          +------------------------+
   |     <<interface>>      | -------------------------> |     <<interface>>      |
   |        Subject         |                            |        Observer        |
   +------------------------+                            +------------------------+
   | + registerObserver(o)  |                            | + update(...)          |
   | + removeObserver(o)    |                            +------------------------+
   | + notifyObservers()    |                                        ^
   +------------------------+                                        | implements
              ^                                                      |
              | implements                               +------------------------+
   +------------------------+                            |   ConcreteObserver     |
   |    ConcreteSubject     | <------------------------- +------------------------+
   |      (WeatherData)     |        observes            | + update(...)          |
   +------------------------+                            +------------------------+
   | - observers: List      |
   | - state                |
   +------------------------+
   ```
2. **Java code for a `Subject` interface:**
   ```java
   public interface Subject {
       void registerObserver(Observer o);
       void removeObserver(Observer o);
       void notifyObservers();
   }
   ```
3. **Why does the Subject need a list of observers?**
   So it can dynamically track an arbitrary number of subscribers at runtime and loop through all of them to broadcast state updates whenever its data changes.

### Learning Outcome 2: Evaluate Legacy `java.util.Observer`
4. **Three problems with `java.util.Observable`:**
   1) `Observable` is a class, forcing single inheritance; 2) `setChanged()` is `protected` and easy to forget; 3) Notification order is unspecified and `update(Observable, Object)` relies on an untyped pull model.
5. **Why was this API deprecated?**
   Deprecated in Java 9 because it is inflexible, lacks type safety/generics, is not generally thread-safe, and does not provide rich event semantics compared to `PropertyChangeListener`, custom Observer interfaces, or `java.util.concurrent.Flow`.
6. **What is the "pull model" and why is it harder to use?**
   In the pull model, the Subject notifies observers without sending the updated fields, forcing each observer to inspect/cast the Subject reference and invoke getters. This couples observers to the concrete Subject class and requires extra boilerplate.

### Learning Outcome 3: Create a Custom Event Notification System
7. **What methods must a custom `Subject` interface provide?**
   `registerObserver(Observer o)`, `removeObserver(Observer o)`, and `notifyObservers()`.
8. **How does the Subject notify all observers?**
   By iterating over its registered `observers` collection inside `notifyObservers()` and invoking each observer's callback method (e.g., `o.update(...)`).
9. **How do you prevent a memory leak when observers are no longer needed?**
   Explicitly call `removeObserver(o)` when an observer is closed or destroyed (or use weak references where appropriate) so the Subject's list does not hold dangling references that block garbage collection.

### Learning Outcome 4: Compare Different Mechanisms
10. **Four event notification mechanisms in Java:**
    Custom Observer interfaces, `java.util.Observer`/`Observable` (deprecated), `java.beans.PropertyChangeListener`, and JavaFX Properties/Bindings.
11. **Which one is best for JavaFX?**
    JavaFX Properties and Bindings.
12. **Which one is deprecated?**
    `java.util.Observer` and `java.util.Observable`.

**Score:** 12 / 12 (Excellent — Observer pattern mastered)

---

## One-Page Summary: The Observer Pattern

The **Observer pattern** defines a one-to-many dependency between a **Subject** (publisher) and any number of **Observers** (subscribers) so that when the Subject's state changes, all registered Observers are notified automatically. Instead of a data model hardcoding direct calls to specific UI views—which creates tight coupling and makes testing or extending the system painful—the Subject only communicates through a shared `Observer` interface. Observers can be registered or removed dynamically at runtime without modifying a single line of the Subject. In Java, building your own custom `Subject` and `Observer` interfaces is far cleaner and more flexible than the deprecated `java.util.Observable` class, which suffered from single-inheritance limits and the awkward `setChanged()` flag. For JavaBeans and Swing components, `PropertyChangeSupport` and `PropertyChangeListener` provide built-in support with old and new property values, while JavaFX offers reactive bindings.

## Final Reflection

The Observer pattern is a foundational behavioral design pattern that decouples event producers from event consumers through a one-to-many subscription model. It relates directly to event-driven programming because every Swing listener (`ActionListener`, `ItemListener`, `DocumentListener`) is an implementation of the Observer pattern where UI widgets act as subjects and registered listeners act as observers. By relying on interfaces rather than concrete classes, systems become modular, extensible, and easy to unit-test in isolation. For my own plain Java or Swing projects, I would choose a **custom typed Observer interface** (or `PropertyChangeListener` for bean-style properties) because it avoids `java.util.Observable`'s single-inheritance restriction, provides compile-time type safety, and keeps notification payloads clean and explicit.
