# Activity 2: "Build the Event Loop" — Simulation Notes & Reflection

## Round 3 Questions (Simulating a Blocked Event Loop)

1. **What happened to the other events while you were "stuck" on the slow handler?**
   All other events remained stuck waiting in the event queue. None of them could be picked up or processed until the slow handler finished its 30-second task.

2. **What would a user see if this happened in a real application?**
   The user would see a completely frozen ("Not Responding") application window: buttons would not respond to clicks, text fields would not show typed characters, and the window would not even repaint or move if dragged.

3. **How could this problem be fixed?**
   Offload long-running or blocking work (such as network requests, database queries, or heavy computations) to a **background worker thread** (e.g., `new Thread(...)` or `SwingWorker`), and then dispatch only the lightweight UI update back onto the event loop (`SwingUtilities.invokeLater(...)`).

---

## Step 5: Written Reflection

In an event-driven application, the **event queue** acts as a First-In, First-Out (FIFO) waiting line that stores incoming events—such as button clicks, keystrokes, mouse movements, and timer ticks—in the exact order they occur. The **event loop** is a continuous background cycle that takes one event at a time from the front of the event queue, identifies its registered listener, and executes the corresponding event handler before moving on to the next event. Because a single thread (such as Swing's Event Dispatch Thread) typically runs this loop and also handles screen repainting, every handler must finish quickly. If a handler performs a slow operation like sleeping, reading a huge file, or waiting on a network request directly on the event loop, the entire loop stalls and cannot dequeue subsequent events. As a result, the user interface stops responding to input and fails to redraw itself, creating a frozen experience. Keeping slow operations on separate background threads ensures the event loop remains free, responsive, and ready to process user interactions immediately.
