# Activity 4: EDT Rule — Observations

## FreezeDemo Observations
- Clicking "Freeze the UI" immediately locks the entire window.
- The window **cannot be moved** during the 5-second sleep.
- The window **cannot be resized** — the OS draws it as "Not Responding".
- Clicking the button again during the freeze **has no effect**.
- After 5 seconds, "Done!" finally appears, but the "Working..." intermediate state was never visually shown because the EDT was blocked and couldn't repaint.

## FixedDemo Observations
- Clicking "Do Long Operation" shows "Working..." and greys out the button immediately.
- The window **can be moved freely** while the background operation runs.
- The window **can be resized** normally.
- After 5 seconds, the label updates to "Done!" and the button is re-enabled.
- The UI remained fully responsive throughout the operation.

## Two Golden Rules of Swing Threading
1. **All GUI creation and updates must happen on the EDT** — use `SwingUtilities.invokeLater(...)`.
2. **Never perform long operations on the EDT** — use `new Thread(...)` or `SwingWorker` and update the GUI via `invokeLater` from the background thread.
