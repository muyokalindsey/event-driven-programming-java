# Week 3 Final Self-Assessment

## Learning Outcome 1: Create a functional Swing application with a main window
**Rating: 5 / 5**

Minimal code for a JFrame from memory:
```java
SwingUtilities.invokeLater(() -> {
    JFrame frame = new JFrame("My App");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 400);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
});
```

## Learning Outcome 2: Add, customize, and arrange components using layouts
**Rating: 5 / 5**

| Layout | Use Case |
|---|---|
| `FlowLayout` | Toolbars, tag rows — arranges components left-to-right, wrapping as needed |
| `BorderLayout` | Main app window with header, sidebar, content area, and status bar |
| `GridLayout` | Calculator keypads, photo galleries, dashboard cards — equal-sized cells |
| `BoxLayout (Y_AXIS)` | Vertical stacking of mixed-width components (e.g., dashboard content panel) |

## Learning Outcome 3: Ensure GUI code runs on the EDT
**Rating: 5 / 5**

**Two golden rules:**
1. All GUI creation and updates must happen on the EDT — use `SwingUtilities.invokeLater(...)`.
2. Never perform long operations on the EDT — offload to a background `Thread` and post UI updates back via `SwingUtilities.invokeLater(...)`.

## Learning Outcome 4: Build a static, non-interactive UI layout
**Rating: 5 / 5**
`StudentPortal.java` runs successfully — dark-blue header, 6-button sidebar, welcome + stat cards + notifications in center, status bar at bottom.

---

## Completion Checklist
- [x] Activity 1: `FirstWindow.java` — JFrame displayed with EDT thread name in console
- [x] Activity 2: `ComponentShowcase.java` — all 8 component types visible
- [x] Activity 3: `FlowLayoutDemo.java`, `BorderLayoutDemo.java`, `GridLayoutDemo.java`, `CombinedLayoutDemo.java`
- [x] Activity 4: `FreezeDemo.java` and `FixedDemo.java` — contrasting EDT behaviour documented
- [x] Activity 5: `StudentPortal.java` — polished university portal mockup
