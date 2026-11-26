import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * Week 5 – Activity 1: Custom JList with DefaultListCellRenderer
 * Displays a contact list with status colour dots and a detail panel.
 */
public class ContactListDemo extends JFrame {

    // ---- Data model --------------------------------------------------------
    private static final String[] NAMES   = {"Alice Johnson", "Bob Smith", "Carol Williams", "David Brown", "Eve Davis"};
    private static final String[] STATUS  = {"Online", "Away", "Offline", "Online", "Away"};
    private static final String[] PHONES  = {"555-0101", "555-0102", "555-0103", "555-0104", "555-0105"};
    private static final String[] EMAILS  = {"alice@mail.com", "bob@mail.com", "carol@mail.com", "david@mail.com", "eve@mail.com"};

    private static final Color COLOR_ONLINE  = new Color(39, 174, 96);
    private static final Color COLOR_AWAY    = new Color(230, 126, 34);
    private static final Color COLOR_OFFLINE = new Color(149, 165, 166);

    // ---- UI fields ---------------------------------------------------------
    private JList<String> contactList;
    private JTextArea     detailArea;

    // -----------------------------------------------------------------------
    public ContactListDemo() {
        super("Contact List – Week 5 Activity 1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 420);
        setLocationRelativeTo(null);

        // --- build the list model ---
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String name : NAMES) model.addElement(name);

        contactList = new JList<>(model);
        contactList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        contactList.setCellRenderer(new ContactCellRenderer());
        contactList.setFixedCellHeight(50);

        // --- selection listener ---
        contactList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int idx = contactList.getSelectedIndex();
                if (idx >= 0) showContact(idx);
            }
        });

        // --- detail panel ---
        detailArea = new JTextArea(5, 30);
        detailArea.setEditable(false);
        detailArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        detailArea.setBorder(new EmptyBorder(8, 8, 8, 8));
        detailArea.setText("Select a contact to see details…");

        // --- layout ---
        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                new JScrollPane(contactList), new JScrollPane(detailArea));
        split.setDividerLocation(260);
        add(split);

        setVisible(true);
    }

    // ---- Show details of selected contact ----------------------------------
    private void showContact(int idx) {
        detailArea.setText(
            "Name   : " + NAMES[idx]  + "\n" +
            "Status : " + STATUS[idx] + "\n" +
            "Phone  : " + PHONES[idx] + "\n" +
            "Email  : " + EMAILS[idx]
        );
    }

    // ---- Custom renderer ---------------------------------------------------
    private class ContactCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {

            JPanel panel = new JPanel(new BorderLayout(10, 0));
            panel.setBorder(new EmptyBorder(6, 10, 6, 10));

            // Colour dot icon
            String status = STATUS[index];
            Color dotColor = switch (status) {
                case "Online"  -> COLOR_ONLINE;
                case "Away"    -> COLOR_AWAY;
                default        -> COLOR_OFFLINE;
            };
            JLabel dotLabel = new JLabel(new ColorDot(dotColor));
            panel.add(dotLabel, BorderLayout.WEST);

            // Name + status text
            JPanel textPanel = new JPanel(new GridLayout(2, 1));
            textPanel.setOpaque(false);
            JLabel nameLabel = new JLabel((String) value);
            nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD, 14f));
            JLabel statusLabel = new JLabel(status);
            statusLabel.setFont(statusLabel.getFont().deriveFont(11f));
            statusLabel.setForeground(dotColor);
            textPanel.add(nameLabel);
            textPanel.add(statusLabel);
            panel.add(textPanel, BorderLayout.CENTER);

            // Selection colours
            if (isSelected) {
                panel.setBackground(list.getSelectionBackground());
                nameLabel.setForeground(list.getSelectionForeground());
            } else {
                panel.setBackground(index % 2 == 0 ? Color.WHITE : new Color(245, 247, 249));
                nameLabel.setForeground(list.getForeground());
            }

            return panel;
        }
    }

    // ---- Coloured circle icon ----------------------------------------------
    private static class ColorDot implements Icon {
        private final Color color;
        ColorDot(Color color) { this.color = color; }

        @Override public int getIconWidth()  { return 16; }
        @Override public int getIconHeight() { return 16; }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fillOval(x, y + 2, 12, 12);
            g2.dispose();
        }
    }

    // ---- Entry point -------------------------------------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ContactListDemo::new);
    }
}
