# Event-Driven System Design

## System Name: Smart Home Controller

---

## Part 1: Event Sources
List all components that can generate events:

1. **Lights Toggle Button (`JToggleButton`)** - Generates `ActionEvent` when the homeowner turns living-room lights ON or OFF.
2. **Security Arm/Disarm Toggle (`JToggleButton`)** - Generates `ActionEvent` when the security alarm is armed or disarmed.
3. **Door Sensor Simulator Button (`JButton`)** - Generates `ActionEvent` simulating a magnetic door contact sensor opening.
4. **Thermostat Slider (`JSlider`)** - Generates `ChangeEvent` when the target temperature (16°C–30°C) is adjusted.
5. **Reset Alarm / All Off Button (`JButton`)** - Generates `ActionEvent` to silence an active alarm and restore normal status.

---

## Part 2: Events
List all possible events in the system:

| Event Name | Source | Description |
|---|---|---|
| `LightsToggledEvent` | `lightsToggle` | Triggered when the user clicks the Lights toggle button |
| `SecurityModeChangedEvent` | `securityToggle` | Triggered when the user arms or disarms the security system |
| `DoorOpenedEvent` | `doorButton` | Triggered when the front door sensor detects an opening |
| `TemperatureChangedEvent` | `thermostatSlider` | Triggered when the user drags the thermostat slider to a new setpoint |
| `AlarmResetEvent` | `resetButton` | Triggered when the user clicks "Reset Alarm" after an intrusion alert |

---

## Part 3: Event Handlers
For each event, describe what should happen:

| Event | Handler Action |
|---|---|
| `LightsToggledEvent` | Updates button text (`Lights: ON` / `OFF`), updates status bar, and appends a timestamped entry to the event log. |
| `SecurityModeChangedEvent` | Updates security toggle label (`ARMED` / `OFF`), clears any active alarm highlight when disarmed, updates status bar, and logs the security state change. |
| `DoorOpenedEvent` | Logs the door opening; if security is currently armed, triggers `"ALARM ACTIVE!"`, highlights the status banner in red, and logs an intruder alert. |
| `TemperatureChangedEvent` | Reads `thermostatSlider.getValue()`, updates the thermostat readout label (`Target Temp: XX°C`), and logs temperature adjustments once the slider stops moving. |
| `AlarmResetEvent` | Resets the alarm banner background back to normal, updates status text to `"Alarm cleared - System Ready"`, and logs the acknowledgement. |

---

## Part 4: Event Flow Diagram

```text
+-------------+     +--------------+     +--------------+     +-------------+
| User Clicks | --> | Event Source | --> | Event Object | --> | Event Queue |
| / Drags UI  |     | (JButton /   |     | (ActionEvent |     | (AWT Event  |
|             |     |  JSlider)    |     | /ChangeEvent)|     |  Queue)     |
+-------------+     +--------------+     +--------------+     +-------------+
                                                                     |
                                                                     v
+-------------+     +--------------+     +--------------+     +-------------+
| GUI Repaint | <-- | Event Handler| <-- | Registered   | <-- | Event Loop  |
| & Log Entry |     | (Lambda /    |     | Listener     |     | (EDT Thread)|
|             |     |  Method)     |     |              |     |             |
+-------------+     +--------------+     +--------------+     +-------------+
```

---

## Part 5: Potential Issues
1. **Blocking the EDT during external hardware/network I/O** - Prevent by sending real IoT device commands on a background thread and only updating Swing labels/logs via `SwingUtilities.invokeLater()`.
2. **Log TextArea growing without bound in long-running sessions** - Prevent by capping the number of retained log lines or clearing old entries automatically.
3. **Rapid slider drag flooding the log** - Prevent by checking `!thermostatSlider.getValueIsAdjusting()` before logging the final target temperature while still updating the live label smoothly.
