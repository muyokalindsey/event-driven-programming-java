# Event-Driven Programming in Java (Weeks 1–10)

Practical activities, Java source files, designs, reflections, and self-assessments for Weeks 1 through 10.

## Directory Structure

- `week01-edp/` — Week 1: Event-Driven Programming fundamentals, Event Loop reflection, Procedural vs. Event-Driven calculators, Bug Fix Challenge, Smart Home prototype & design, Self-Assessment
- `week02-observer-pattern/` — Week 2: Custom Weather Station Observer pattern, Legacy `java.util.Observer`, Refactoring tightly coupled code, `PropertyChangeListener` comparison, Self-Assessment
- `week03-swing-gui/` — Week 3: First `JFrame`, Component Showcase, Layout Managers (`FlowLayout`, `BorderLayout`, `GridLayout`, combined shell), EDT rule (`FreezeDemo` vs. `FixedDemo`), Student Portal static mockup
- `week04-event-handling/` — Week 4: `ActionListener` basics, 4 listener styles & `getSource()`, `ItemListener` & `DocumentListener`, Counter App with keyboard shortcuts, Interactive Registration Form, Mouse & Keyboard listeners
- `week05-advanced-swing/` — Week 5: `JList`, `JTable`, `JTree`, tabs, menus, dialogs, toolbar, and a File Manager capstone
- `week06-javafx/` — Week 6: programmatic JavaFX, layouts, FXML login with controller, and a dashboard capstone (see its README for run commands)
- `week07-javafx-events/` — Week 7: JavaFX event filters and handlers, mouse and keyboard input, drag and drop, and custom events
- `week08-javafx-bindings/` — Week 8: observable properties, listeners, one-way and two-way bindings, and a reactive registration form
- `week09-async-javafx/` — Week 9: blocking versus background work, JavaFX `Task` progress, and reusable `Service` tasks
- `week10-university-system/` — Week 10: JavaFX university student management capstone with MySQL, background database tasks, and a scheduled heartbeat (see its README for setup)

## Prerequisites

- Java Development Kit (JDK 17+ recommended; verified with JDK 25)
- Compile any activity folder with `javac *.java` and run its main class with `java <MainClass>`.
- Week 6 uses Maven and JavaFX; see `week06-javafx/README.md` for its application run commands.
- Weeks 7–9 use Maven and JavaFX; each module README lists its activity entry points and commands.
- Week 10 also requires a configured MySQL database; see `week10-university-system/README.md` before running it.
