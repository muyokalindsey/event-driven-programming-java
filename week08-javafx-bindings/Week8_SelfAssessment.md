# Week 8 — Final Self-Assessment

**Course:** Event-Driven Programming in Java

**Week:** 8 — Properties and Bindings in JavaFX

**Evidence basis:** Source and Maven configuration in this repository. Ratings describe implementation coverage; they do not claim that the JavaFX windows were launched here.

## Learning Outcomes

| # | Outcome | Evidence | Rating |
|---|---|---|---:|
| 1 | Create JavaFX model properties | `Person.java` exposes string and integer properties plus ordinary getters and setters. | 5 / 5 |
| 2 | Observe property changes | `ListenerDemo.java` compares invalidation and change listeners and logs old/new values. | 5 / 5 |
| 3 | Use one-way and bidirectional bindings | `UnidirectionalDemo.java` binds a greeting; `BidirectionalDemo.java` binds a slider and text field with a number converter. | 5 / 5 |
| 4 | Build a reactive form | `ReactiveFormApp.java` binds a character count and preview and disables submission while required fields are invalid. | 5 / 5 |

## Activity Reflections

- **Properties:** A property stores a value and also provides an observable interface for binding and listeners.
- **Listeners:** A `ChangeListener` receives old and new values. An `InvalidationListener` signals that a value may need recomputation; it does not provide the old/new pair.
- **One-way binding:** The target follows its source and cannot be set directly until it is unbound.
- **Bidirectional binding:** Either side can update the other. The converter maps between the slider's number and the text field's string.
- **Reactive form:** The submit button's disabled state is derived from the current field values; the bio count and preview update as text changes.

## Self-Check Answers

1. **Why expose property getters?** Bindings and listeners need access to the observable property, while ordinary getters/setters preserve a familiar model API.
2. **How do change and invalidation listeners differ?** A change listener receives old and new values. An invalidation listener reports invalidation and may defer recomputation until the value is read.
3. **Which direction does `bind()` update?** From the source property to the bound target.
4. **Why use a converter for the slider and text field?** Their properties have different types (`double` and `String`), so the converter maps values in each direction.
5. **Why bind the submit button's disabled state?** The UI rule stays synchronized with current input without manually checking every keystroke.

## Completion Evidence

- [x] Observable `Person` model
- [x] Invalidation and change listener example
- [x] One-way and bidirectional binding demos
- [x] Reactive registration form
- [x] Maven entry points and run instructions

**Implementation evidence score:** 20 / 20. Email validation in the example checks for an `@` character only; it is instructional validation, not full email verification. Interactive behavior still needs to be tried in a JavaFX desktop session to confirm it on the target machine.
