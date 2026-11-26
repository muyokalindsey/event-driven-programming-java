# Week 9 — Final Self-Assessment

**Course:** Event-Driven Programming in Java

**Week:** 9 — Asynchronous Programming in JavaFX

**Evidence basis:** Source and Maven configuration in this repository. Ratings describe implementation coverage; they do not claim that the JavaFX windows were launched here.

## Learning Outcomes

| # | Outcome | Evidence | Rating |
|---|---|---|---:|
| 1 | Explain why long work must not block the UI thread | `FreezeDemo.java` demonstrates the blocking behavior; `TaskDemo.java` moves the delay to a background task. | 5 / 5 |
| 2 | Run background work with `Task` | `TaskDemo.java` attaches success/failure handlers; `ProgressDemo.java` publishes progress and messages. | 5 / 5 |
| 3 | Bind task state to controls | `ProgressDemo.java` binds the progress bar and status label to the task properties and unbinds them for terminal messages. | 5 / 5 |
| 4 | Reuse and cancel work with `Service` | `ServiceDemo.java` provides a restartable service, a cancel action, and success/failure handlers. | 5 / 5 |

## Activity Reflections

- **UI responsiveness:** The JavaFX Application Thread processes input and renders the interface. Blocking it delays both; `FreezeDemo` illustrates this with a deliberate five-second sleep.
- **Tasks:** The `Task.call()` method performs background work. Its lifecycle handlers run on the JavaFX Application Thread, where the demo updates controls.
- **Progress:** `updateProgress()` and `updateMessage()` publish task state that can be bound to UI properties. Success and failure paths restore the controls.
- **Services:** A `Service` creates tasks that can be restarted. The example checks for cancellation during its simulated work and exposes a Cancel button.

## Self-Check Answers

1. **What happens if a long operation runs in an event handler?** The JavaFX Application Thread is blocked, so the interface cannot respond or repaint normally.
2. **Where should blocking work go?** In a background task, such as a JavaFX `Task` run by an executor or thread.
3. **Which thread runs task lifecycle handlers?** JavaFX schedules task state notifications and event handlers on the JavaFX Application Thread.
4. **How can a task report progress?** Call `updateProgress()` and bind a progress control to `progressProperty()`.
5. **Why use `Service`?** It manages reusable task creation and lifecycle, including restart and cancellation.

## Completion Evidence

- [x] Intentional UI freeze comparison
- [x] Background task and completion/failure handling
- [x] Progress and message bindings with failure recovery
- [x] Restartable, cancellable service example
- [x] Maven entry points and run instructions

**Implementation evidence score:** 20 / 20. The five-second delays simulate work and do not contact a real service. Interactive behavior still needs to be tried in a JavaFX desktop session to confirm it on the target machine.
