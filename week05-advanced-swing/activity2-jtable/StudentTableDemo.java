import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Week 5 – Activity 2: JTable with custom renderer, editable cells, and row operations.
 */
public class StudentTableDemo extends JFrame {

    // ---- Column definitions ------------------------------------------------
    private static final String[] COLUMNS = {"ID", "Name", "Age", "Grade", "GPA"};
    private static final Object[][] INITIAL_DATA = {
        {1001, "Alice Johnson",  20, "A", 3.9},
        {1002, "Bob Smith",      22, "B", 2.8},
        {1003, "Carol Williams", 21, "A", 3.7},
        {1004, "David Brown",    23, "C", 2.3},
        {1005, "Eve Davis",      20, "A", 3.5},
        {1006, "Frank Miller",   24, "B", 3.1},
    };

    private DefaultTableModel tableModel;
    private JTable table;
    private int nextId = 2000;

    // -----------------------------------------------------------------------
    public StudentTableDemo() {
        super("Student Records – Week 5 Activity 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(680, 420);
        setLocationRelativeTo(null);

        // --- model ----------------------------------------------------------
        tableModel = new DefaultTableModel(INITIAL_DATA, COLUMNS) {
            @Override
            public boolean isCellEditable(int row, int col) {
                // Only Name (1) and Grade (3) are editable
                return col == 1 || col == 3;
            }

            @Override
            public Class<?> getColumnClass(int col) {
                return switch (col) {
                    case 0, 2 -> Integer.class;
                    case 4    -> Double.class;
                    default   -> String.class;
                };
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(26);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);

        // GPA column custom renderer
        table.getColumnModel().getColumn(4).setCellRenderer(new GPACellRenderer());

        // ---- Button panel --------------------------------------------------
        JButton addBtn    = new JButton("Add Student");
        JButton deleteBtn = new JButton("Delete Selected");
        JButton clearBtn  = new JButton("Clear All");

        addBtn.addActionListener(e -> addStudent());
        deleteBtn.addActionListener(e -> deleteSelected());
        clearBtn.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(this,
                "Clear ALL student records?", "Confirm Clear",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) tableModel.setRowCount(0);
        });

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        btnPanel.add(addBtn); btnPanel.add(deleteBtn); btnPanel.add(clearBtn);

        // ---- Status label --------------------------------------------------
        JLabel statusLabel = new JLabel(" Double-click Name or Grade cells to edit.");
        statusLabel.setForeground(Color.GRAY);

        // ---- Layout --------------------------------------------------------
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        add(statusLabel, BorderLayout.NORTH);

        setVisible(true);
    }

    // ---- Add a new student row via input dialogs ---------------------------
    private void addStudent() {
        String name = JOptionPane.showInputDialog(this, "Student name:", "Add Student", JOptionPane.PLAIN_MESSAGE);
        if (name == null || name.isBlank()) return;

        String ageStr = JOptionPane.showInputDialog(this, "Age:", "Add Student", JOptionPane.PLAIN_MESSAGE);
        int age;
        try { age = Integer.parseInt(ageStr.trim()); } catch (Exception ex) { age = 18; }

        String[] grades = {"A", "B", "C", "D", "F"};
        String grade = (String) JOptionPane.showInputDialog(this, "Grade:", "Add Student",
            JOptionPane.PLAIN_MESSAGE, null, grades, grades[0]);
        if (grade == null) return;

        String gpaStr = JOptionPane.showInputDialog(this, "GPA (0.0 – 4.0):", "Add Student", JOptionPane.PLAIN_MESSAGE);
        double gpa;
        try { gpa = Double.parseDouble(gpaStr.trim()); } catch (Exception ex) { gpa = 2.0; }

        tableModel.addRow(new Object[]{nextId++, name, age, grade, gpa});
    }

    // ---- Delete selected row -----------------------------------------------
    private void deleteSelected() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.",
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String name = (String) tableModel.getValueAt(row, 1);
        int choice = JOptionPane.showConfirmDialog(this,
            "Delete \"" + name + "\"?", "Confirm Delete",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) tableModel.removeRow(row);
    }

    // ---- GPA cell renderer -------------------------------------------------
    private static class GPACellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int col) {

            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
            if (value instanceof Double gpa) {
                setText(String.format("%.2f", gpa));
                if (!isSelected) {
                    if (gpa < 3.0) {
                        c.setBackground(new Color(255, 200, 200));
                        c.setForeground(new Color(180, 0, 0));
                    } else {
                        c.setBackground(new Color(200, 240, 200));
                        c.setForeground(new Color(0, 120, 0));
                    }
                }
            }
            return c;
        }
    }

    // ---- Entry point -------------------------------------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentTableDemo::new);
    }
}
