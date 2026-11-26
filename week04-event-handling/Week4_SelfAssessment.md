# Week 4 Final Self-Assessment

## Learning Outcome 1: Implement ActionListener using multiple techniques
**Rating: 5 / 5**

Three ways to implement an `ActionListener`:
1. **Named class** — `class MyListener implements ActionListener { ... }` → reusable across multiple components
2. **Anonymous inner class** — `button.addActionListener(new ActionListener() { ... })` → common in legacy code
3. **Lambda expression** — `button.addActionListener(e -> { ... })` → modern, concise, preferred

## Learning Outcome 2: Handle state changes in checkboxes, radio buttons, and text fields
**Rating: 5 / 5**

- Listener for checkbox state changes: **`ItemListener`**
- Listener for text field changes: **`DocumentListener`** (registered via `textField.getDocument().addDocumentListener(...)`)

## Learning Outcome 3: Differentiate between listener interfaces
**Rating: 5 / 5**

| Component | Listener |
|---|---|
| `JButton` | `ActionListener` |
| `JCheckBox` | `ItemListener` (also `ActionListener`) |
| `JTextField` | `DocumentListener` (live changes) or `ActionListener` (Enter pressed) |
| Mouse hover | `MouseListener.mouseEntered()` / `MouseListener.mouseExited()` |
| Key press | `KeyListener` (or `KeyAdapter`) |

## Learning Outcome 4: Build an interactive Swing application
**Rating: 5 / 5**
`RegistrationForm.java` works fully: validation on Submit, live name preview via `DocumentListener`, Clear resets all fields, Exit shows confirm dialog.

---

## Completion Checklist
- [x] Activity 1: `FirstButton.java` — popup appears on click
- [x] Activity 2: `ListenerDemo.java` (4 styles) + `EventSourceDemo.java` (shared listener)
- [x] Activity 3: `ItemListenerDemo.java` (pizza calculator) + `DocumentListenerDemo.java` (char counter) + `LiveSearchDemo.java`
- [x] Activity 4: `CounterApp.java` with +/- buttons, colour change, keyboard shortcuts (↑ ↓ R)
- [x] Activity 5: `RegistrationForm.java` — full validated interactive form
- [x] Activity 6: `DrawingApp.java` + `KeyboardMover.java`
