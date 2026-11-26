# Activity 6: Week 1 Final Self-Assessment & Summary

## Self-Assessment Answers

### Learning Outcome 1: Define EDP Components
1. **What is an event? Give two examples.**
   An event is an object or signal representing a state change or user/system action that occurred.
   - *Examples:* Clicking a button (`ActionEvent`), pressing a keyboard key (`KeyEvent`).
2. **What is an event source? Give two examples.**
   An event source is the component or device where an event originates and which dispatches the event to registered listeners.
   - *Examples:* A `JButton`, a `JSlider` (or hardware card reader / touchscreen).
3. **What is an event listener? Give two examples.**
   An event listener is an object (implementing a listener interface) registered with an event source to receive notifications when a specific event occurs.
   - *Examples:* `ActionListener`, `MouseListener` (or `DocumentListener`).
4. **What is an event handler? Give two examples.**
   An event handler is the method or callback block inside a listener that executes in response to an event.
   - *Examples:* `actionPerformed(ActionEvent e)` updating a label, or `mousePressed(MouseEvent e)` recording a drawing point.
5. **Diagram showing how these four components relate:**
   ```text
   [Event Source] --fires--> (Event Object) --notifies--> [Event Listener] --executes--> {Event Handler}
   ```

### Learning Outcome 2: Compare Paradigms
6. **How does procedural programming differ from event-driven programming?**
   Procedural programming executes instructions in a fixed, predetermined order controlled by the program, whereas event-driven programming waits in an event loop and lets external events (such as user clicks or sensor triggers) determine what code runs and when.
7. **What is the main advantage of event-driven programming for GUIs?**
   Users can interact with any button, menu, or input field in any order, recover easily from mistakes, and keep using the application continuously without restarting.
8. **In which scenario would you choose procedural programming over event-driven?**
   Batch scripts, command-line utilities, mathematical pipelines, or one-shot data conversion tools where steps run sequentially from input to output with no interactive GUI.

### Learning Outcome 3: Event Loop and Queue
9. **What is the event loop? What does it do?**
   The event loop is an ongoing loop that polls the event queue for incoming events and dispatches each event to its registered listener/handler.
10. **What is the event queue? How does it work?**
    The event queue is a FIFO buffer that holds incoming events in chronological order until the event loop is ready to process each one.
11. **Why should the event loop never be blocked?**
    Because the event loop processes both user input and GUI repaints on a single thread; blocking it causes the entire UI to freeze and stop responding.
12. **What is the EDT in Java Swing?**
    The **Event Dispatch Thread (EDT)** is Swing's dedicated single thread that runs the event loop and must handle all Swing component creation, updates, and event dispatching.

### Learning Outcome 4: Real-World Examples
13. **List five real-world event-driven systems:**
    1) ATM machine, 2) Microwave oven, 3) Traffic light controller, 4) Supermarket self-checkout, 5) Smartphone touchscreen UI.
14. **For one system, identify all four EDP components (ATM Machine):**
    - *Event Source:* Keypad button
    - *Event:* Button pressed
    - *Event Listener:* ATM input controller
    - *Event Handler:* Append digit to PIN buffer and verify PIN
15. **Why are most modern applications event-driven?**
    Modern apps are interactive, multi-control GUIs, web apps, or connected services that must respond immediately to unpredictable user actions, network responses, and hardware/timer events.

**Self-Assessment Score:** 15 / 15 (Excellent — Week 1 concepts mastered)

---

## Week 1 One-Page Summary

### Key Definitions
- **Event:** A notification object describing something that happened (e.g., `ActionEvent`).
- **Event Source:** The component generating the event (e.g., `JButton`).
- **Event Listener:** The interface/object listening for events from a source (e.g., `ActionListener`).
- **Event Handler:** The method/lambda body executed when the event fires (e.g., `actionPerformed`).
- **Event Queue & Event Loop (EDT):** Incoming events wait in a FIFO queue and are dispatched one-by-one on Swing's `AWT-EventQueue-0` thread.

### Essential Pattern & Code Example
```java
SwingUtilities.invokeLater(() -> {
    JButton btn = new JButton("Process");
    btn.addActionListener(e -> {
        new Thread(() -> {
            // Long background task off the EDT
            SwingUtilities.invokeLater(() -> statusLabel.setText("Done!"));
        }).start();
    });
});
```

### Key Takeaways & Questions
- Always start Swing GUIs inside `SwingUtilities.invokeLater(...)`.
- Never sleep or run blocking I/O on the EDT, and never mutate Swing widgets directly from a background thread.
- Clear collections holding UI components when removing them from containers to avoid memory leaks.
- *Next Question to Explore:* How can multiple unrelated UI views subscribe to the same data model cleanly without tight coupling? (Covered in Week 2: Observer Pattern.)
