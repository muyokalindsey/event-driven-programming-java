# Activity 1: "Spot the Components" — Real-World EDP Scavenger Hunt

## Completed EDP Components Table

| System | Event Source | Event | Listener | Handler / Action |
|---|---|---|---|---|
| **ATM Machine Operation** | Card slot | Card inserted | Card reader sensor | Read card chip/magnetic stripe and prompt for PIN |
| **ATM Machine Operation** | Keypad button | Button pressed | ATM controller software | Append digit / verify entered PIN against bank server |
| **ATM Machine Operation** | Cash dispenser | Cash taken by user | Optical/IR dispenser sensor | Close shutter, print receipt, and end transaction |
| **Microwave Oven Use** | Door latch | Door opened or closed | Door interlock switch | Pause magnetron immediately if opened; enable Start if closed |
| **Microwave Oven Use** | Number pad / Start button | Button pressed | Microwave microcontroller | Set cooking timer/power level and start turntable & heating |
| **Microwave Oven Use** | Internal countdown timer | Timer reaches 00:00 | Timer interrupt listener | Stop magnetron and sound completion beep |
| **Traffic Light System** | Countdown cycle timer | Timer interval expires | Traffic controller unit | Transition light phase (Green → Yellow → Red) |
| **Traffic Light System** | Pedestrian crosswalk button | Push-button pressed | Crosswalk input controller | Schedule pedestrian "WALK" phase on next safe cycle |
| **Traffic Light System** | Roadway inductive loop | Vehicle detected over coil | Loop detector module | Trigger green-light request for waiting turning lane |
| **Video Game Gameplay** | Keyboard / Gamepad | Key or button pressed | Game input manager | Move player character, jump, or fire weapon |
| **Video Game Gameplay** | Physics / Collision box | Player hits enemy or coin | Collision listener | Deduct health or increment score and play sound effect |
| **Elevator Operation** | Hallway call button | Up/Down button pressed | Elevator dispatch controller | Queue floor stop and send nearest elevator car |
| **Elevator Operation** | Floor arrival sensor | Car reaches target floor | Floor level detector | Stop motor, open doors, and update floor display |
| **Smartphone Touchscreen** | Capacitive touch screen | Finger tap or swipe | OS touch event dispatcher | Launch tapped app icon or scroll feed smoothly |
| **Online Shopping Website** | "Add to Cart" button | Mouse click (`click` event) | Browser DOM event listener | Add item to cart state, update badge count, show toast |
| **Supermarket Self-Checkout** | Barcode laser scanner | Barcode scanned | POS item lookup listener | Query product price from database and add line to receipt |

---

## Step 4: Self-Check Questions

### 1. What is the difference between an event source and an event listener?
An **event source** is the component or hardware/UI element where an event originates (such as a `JButton`, keypad, or timer) and which generates/fires an event object. An **event listener** is the object or interface implementation registered with that source to wait for notifications and react when the event occurs.

### 2. Can one event source have multiple listeners? Give an example.
**Yes.** A single event source can maintain a list of registered listeners and notify all of them when an event occurs.
- **Example:** In a GUI form or IDE, clicking a **"Save"** button (one event source) can notify a file-writer listener (saves the document to disk), a status-bar listener (displays `"Saved"`), and a dirty-flag listener (clears the `*` unsaved indicator).

### 3. Can one listener handle events from multiple sources? Give an example.
**Yes.** One listener instance can be attached to multiple event sources and inspect `event.getSource()` or `event.getActionCommand()` to decide what action to take.
- **Example:** In a calculator UI, a single `ActionListener` can be registered on all digit buttons (`0`–`9`) and append the clicked button's digit text (`((JButton) e.getSource()).getText()`) to the display field.
