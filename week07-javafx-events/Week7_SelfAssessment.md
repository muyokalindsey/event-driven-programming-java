# Week 7 — Final Self-Assessment

**Course:** Event-Driven Programming in Java

**Week:** 7 — Event Handling in JavaFX

**Evidence basis:** Source and Maven configuration in this repository. Ratings describe implementation coverage; they do not claim that the JavaFX windows were launched here.

## Learning Outcomes

| # | Outcome | Evidence | Rating |
|---|---|---|---:|
| 1 | Distinguish event filters from event handlers | `EventPropagationDemo.java` logs the capturing filter, target handler, and bubbling handler; the filter includes an `e.consume()` demonstration. | 5 / 5 |
| 2 | Handle mouse and keyboard events | `MouseKeyDemo.java` displays pointer coordinates and moves a bounded circle with arrow keys after requesting focus. | 5 / 5 |
| 3 | Implement drag and drop | `DragDropDemo.java` transfers a string through a dragboard, accepts it at a drop target, and reports completion. | 5 / 5 |
| 4 | Define and fire a custom JavaFX event | `LoginSuccessEvent.java` defines a typed event; `CustomEventDemo.java` fires and handles it. | 5 / 5 |

## Activity Reflections

- **Propagation:** Filters run while an event travels toward its target; handlers can act at the target and while it bubbles back up. Consuming the event stops further propagation.
- **Mouse and keyboard:** Mouse coordinates come from the event, while key events require the scene graph node to have focus. The demo keeps the circle inside its pane's initial bounds.
- **Drag and drop:** The source places transferable text on a `Dragboard`; the target advertises acceptance and reports success after processing the drop.
- **Custom events:** A domain event separates the login button's action from the listener that responds to a successful demo login. This is an event-flow example, not a security system.

## Self-Check Answers

1. **Which phase uses an event filter?** The capturing phase, as the event travels from the scene/root toward its target.
2. **What does `consume()` do?** It marks the event handled so it does not continue through the remaining propagation path.
3. **Why might a key handler receive no event?** Its node may not have keyboard focus; it must be focus-traversable and focused.
4. **What does a dragboard do?** It carries data and transfer modes between the drag source and drop target.
5. **Why define a custom event?** It provides a typed way for one part of an application to announce an event that other parts can handle independently.

## Completion Evidence

- [x] Event filter and bubbling handler example
- [x] Mouse and keyboard interaction
- [x] Drag-and-drop source and target
- [x] Custom event type, firing, and handler
- [x] Maven entry points and run instructions

**Implementation evidence score:** 20 / 20. The repository contains the code and commands; interactive behavior still needs to be tried in a JavaFX desktop session to confirm it on the target machine.
