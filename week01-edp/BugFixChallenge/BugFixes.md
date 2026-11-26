# Bug Fix Documentation

## Bug 1: Blocking the EDT
- **Symptom:** UI freezes for 5 seconds when the "Process" button is clicked; the window cannot be moved, resized, or clicked.
- **Cause:** `Thread.sleep(5000)` runs directly inside the `ActionListener` on the Event Dispatch Thread (EDT), stalling the event loop.
- **Fix:** Run the 5-second simulated task in a background thread (`new Thread(...)`) and update `statusLabel` back on the EDT via `SwingUtilities.invokeLater(...)`.
- **Code Change:** Wrapped `Thread.sleep(5000)` inside `new Thread(() -> { ... }).start()`, set `"Processing..."` before starting, disabled the button during work, and moved `statusLabel.setText("Done!")` inside `SwingUtilities.invokeLater(...)`.

## Bug 2: Missing Listener
- **Symptom:** Clicking the "Increment" button does nothing; `Count: 0` never changes.
- **Cause:** No `ActionListener` was registered on `incrementButton`, so `ActionEvent`s fired by the button had no handler.
- **Fix:** Registered an `ActionListener` on `incrementButton` that increments `count` and updates `countLabel`.
- **Code Change:** Added `incrementButton.addActionListener(e -> { count++; countLabel.setText("Count: " + count); });`.

## Bug 3: Wrong Thread
- **Symptom:** Erratic Swing behavior, potential race conditions, or visual glitches when updating the clock label.
- **Cause:** `timeLabel.setText(...)` was called directly from inside the background `while (running)` thread instead of the EDT.
- **Fix:** Wrapped the `timeLabel.setText(...)` call inside `SwingUtilities.invokeLater(...)` so all Swing UI mutations occur safely on the EDT.
- **Code Change:** Computed `String currentTime = "Time: " + new Date();` in the background loop and called `SwingUtilities.invokeLater(() -> timeLabel.setText(currentTime));`. Also marked `running` as `volatile` and guarded against spawning duplicate clock threads.

## Bug 4: Memory Leak
- **Symptom:** Memory usage grows over time even after clicking "Remove All".
- **Cause:** `buttonPanel.removeAll()` removed the buttons from the UI container, but `createdButtons` (`ArrayList<JButton>`) still retained strong references to every created `JButton`, preventing garbage collection.
- **Fix:** Clear the `createdButtons` list when removing all buttons from `buttonPanel`.
- **Code Change:** Added `createdButtons.clear();` right after `buttonPanel.removeAll();` in `removeButton`'s `ActionListener`.
