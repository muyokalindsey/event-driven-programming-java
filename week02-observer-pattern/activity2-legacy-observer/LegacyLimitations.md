# Limitations of `java.util.Observer` and `java.util.Observable`

## Limitation 1: Single Inheritance
**Explain:** Why is it a problem that `Observable` is a CLASS, not an INTERFACE?

**Your answer:** Java supports only single class inheritance (`extends`), so any class that extends `Observable` cannot extend any other domain superclass. Furthermore, `Observable` is a concrete class rather than an interface, which prevents swapping in alternative implementations or adding observable behavior to classes already in a hierarchy (such as `JPanel` or a domain model base class).

## Limitation 2: Cannot Change the Notification Order
**Explain:** In what order are observers notified? Can you control this?

**Your answer:** The `Observable` Javadoc explicitly states that the notification order is unspecified (internally it iterates over its `Vector` in reverse order, and subclasses cannot safely customize the dispatch order without overriding internal behavior). Developers cannot rely on or configure priority or deterministic ordering.

## Limitation 3: `setChanged()` is Confusing
**Explain:** Why must you call `setChanged()` before `notifyObservers()`?

**Your answer:** `Observable` maintains a `protected` boolean `changed` flag and ignores `notifyObservers()` unless `setChanged()` was called first. Because `setChanged()` is `protected`, external callers cannot invoke it, and developers writing subclasses frequently forget to call it—causing `notifyObservers()` to fail silently without notifying any observers.

## Limitation 4: Pull Model by Default
**Explain:** How does the observer get the data? Is this efficient?

**Your answer:** By default (`notifyObservers()` with no arguments), `update(Observable o, Object arg)` passes `null` as `arg`, forcing the observer to cast `o` (`if (o instanceof LegacyWeatherData)`) and call getter methods (`getTemperature()`, `getHumidity()`) to pull the data. This couples the observer to the concrete subject type, lacks compile-time type safety (`Object arg`), and adds extra casting and method calls.

## Limitation 5: Deprecated Since Java 9
**Explain:** Why did Java deprecate these classes?

**Your answer:** Oracle deprecated `java.util.Observer` and `java.util.Observable` in Java 9 because the event model is inflexible, not thread-safe in general, lacks generics/type safety, forces class inheritance, and provides a limited notification payload compared to `java.beans.PropertyChangeListener`, custom typed Observer interfaces, or reactive streams (`java.util.concurrent.Flow`).

---

## Step 4: Self-Check Answers

1. **Why did Java deprecate these classes?**
   Because `Observable` is a class (blocking inheritance), `setChanged()` is error-prone, notification order is unspecified, the API lacks type safety and rich event metadata, and modern alternatives like `PropertyChangeListener` and `Flow` are superior.
2. **Why can't you extend another class if you already extend `Observable`?**
   Because Java does not allow multiple inheritance of classes—each class may have at most one direct `extends` superclass.
3. **What is the difference between the push model and the pull model?**
   In the **push model**, the Subject sends the updated data directly as parameters in `update(...)` (e.g., `update(temp, humidity, pressure)`). In the **pull model**, the Subject only notifies the Observer that a change occurred, and the Observer queries/pulls the needed fields from the Subject using getter methods.
