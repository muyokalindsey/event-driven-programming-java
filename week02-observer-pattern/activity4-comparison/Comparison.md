# Comparison of Event Notification Mechanisms in Java

| Feature | Custom Observer | `java.util.Observer` | `PropertyChangeListener` | JavaFX Bindings |
|---|---|---|---|---|
| **Push or Pull?** | Both possible | Pull (by default) | Push (carries old + new) | Push |
| **Is it deprecated?** | No | Yes (Java 9+) | No | No |
| **Requires inheritance?** | No (interface) | Yes (class `Observable`) | No | No |
| **Thread-safe?** | Depends on you | No | Yes (`PropertyChangeSupport` is thread-safe) | Yes (on JavaFX Application Thread) |
| **Auto-generates boilerplate?** | No | No | Yes (via `PropertyChangeSupport`) | Yes |
| **Best for...** | Custom systems | Legacy code | JavaBeans / Swing | JavaFX apps |
| **Can have multiple listeners?** | Yes | Yes | Yes | Yes |
| **Can be typed to send any data?** | Yes | No (`Object`) | Limited (`Object` old/new values) | Yes (typed Properties) |
| **Recommended today?** | Yes | No | Only for JavaBeans / Swing models | Yes |

## Which One Should You Use?

- For a **plain Java project with custom needs** → **Custom Observer**
- For a **JavaFX application** → **JavaFX Bindings**
- For **legacy code using JavaBeans / Swing** → **`PropertyChangeListener`**
- **Never** → `java.util.Observer`

---

## Step 4: Self-Check Answers

1. **Which mechanism carries both the old and new value of a property?**
   `PropertyChangeListener` (via `PropertyChangeEvent.getOldValue()` and `getNewValue()`).
2. **Which mechanism requires a helper class to manage the listener list?**
   `PropertyChangeListener` via `PropertyChangeSupport`.
3. **Which two mechanisms are recommended for modern Java?**
   Custom Observer interfaces and JavaFX Properties/Bindings.
4. **Which mechanism is deprecated and should be avoided?**
   `java.util.Observer` and `java.util.Observable`.
