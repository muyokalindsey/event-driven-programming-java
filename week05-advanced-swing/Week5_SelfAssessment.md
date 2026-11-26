# Week 5 – Self-Assessment & Summary
## Advanced Swing Components

**Course:** Event-Driven Programming in Java  
**Week:** 5  
**Date Completed:** 2026-11-26

---

## Learning Outcomes Checklist

| # | Outcome | Self-Rating (1–5) | Evidence |
|---|---------|-------------------|---------|
| 1 | Use JList with custom cell renderers | ✅ 5 | `ContactListDemo` with `ColorDot` icon and two-line renderer |
| 2 | Build editable JTable with custom renderers | ✅ 5 | `StudentTableDemo` – GPA column coloured red/green, Name & Grade editable |
| 3 | Implement JTree and JTabbedPane in a split layout | ✅ 5 | `FileExplorerDemo` – tree + 3-tab panel in `JSplitPane` |
| 4 | Create a full application with menus, toolbars, and dialogs | ✅ 5 | `MiniEditor` – File/Edit/Help menus, toolbar, file open/save via JFileChooser |
| 5 | Build a capstone using all advanced components | ✅ 5 | `FileManager` – tree navigation, table, toolbar, real filesystem I/O |

---

## Activity Reflections

### Activity 1 – JList with Custom Renderer
Using `DefaultListCellRenderer` to return a custom `JPanel` per row was more flexible than I expected.
The key insight is that the renderer is a **stamp** — it paints one row at a time, so the same panel object
is reused. Making the `ColorDot` an `Icon` implementation kept painting decoupled from layout.

### Activity 2 – JTable
Overriding `isCellEditable()` in `DefaultTableModel` is elegant — it centralises the editing policy in one place.
The GPA renderer demonstrates how `DefaultTableCellRenderer` delegates to the super implementation
and then adjusts colours after the fact, which is cleaner than rebuilding a component from scratch.

### Activity 3 – JTree & JTabbedPane
`DefaultMutableTreeNode` is straightforward once you realise that `getUserObject()` returns the value you passed
to the constructor. Wrapping `File` objects as the user object makes the selection listener clean.
`JSplitPane` with `setDividerLocation(250)` immediately after `add()` requires the frame to be visible first;
calling it in the constructor works because Swing defers layout.

### Activity 4 – Mini Editor
`DocumentListener` fires on every keystroke via three callbacks (`insertUpdate`, `removeUpdate`, `changedUpdate`).
The pattern of `confirmSaveIfModified()` returning `boolean` made the new/open/exit flow clean.
`Files.readString` and `Files.writeString` (Java 11+) are much simpler than stream-based file I/O.

### Activity 5 – File Manager Capstone
Combining all five components showed how they complement each other: the tree drives navigation,
the table shows content, the toolbar provides quick actions, and the status bar gives constant context.
The `buildFolderTree` depth limit (2 levels) keeps startup fast while still showing a useful tree.

---

## Key Concepts Mastered

- **Custom renderers** (`DefaultListCellRenderer`, `DefaultTableCellRenderer`) vs. editors
- **JTable model** (`DefaultTableModel`) vs. read-only data (`AbstractTableModel`)
- **JTree** – `DefaultMutableTreeNode`, `TreeSelectionListener`, expand/collapse
- **JTabbedPane** – adding tabs with components, icons, tooltips
- **JMenuBar / JMenu / JMenuItem** with keyboard mnemonics and accelerators
- **JToolBar** – floatable vs. fixed, adding buttons and separators
- **JFileChooser** with `FileNameExtensionFilter`
- **DocumentListener** for real-time text change events
- **JSplitPane** – horizontal/vertical split, divider location
- **NIO file I/O** – `Files.readString()`, `Files.writeString()`

---

## Score: 25 / 25 (5 activities × 5 points each)
