import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.awt.*;

/**
 * Week 5 – Activity 3: JTree + JTabbedPane in a JSplitPane layout.
 * Left pane: file-system-like tree. Right pane: tabbed settings panel.
 */
public class FileExplorerDemo extends JFrame {

    private JTextArea infoArea;

    // -----------------------------------------------------------------------
    public FileExplorerDemo() {
        super("File Explorer – Week 5 Activity 3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ---- Build tree ----------------------------------------------------
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("My Computer");

        DefaultMutableTreeNode docs = new DefaultMutableTreeNode("Documents");
        docs.add(new DefaultMutableTreeNode("Resume.docx"));
        docs.add(new DefaultMutableTreeNode("CoverLetter.pdf"));
        docs.add(new DefaultMutableTreeNode("Notes.txt"));
        root.add(docs);

        DefaultMutableTreeNode pics = new DefaultMutableTreeNode("Pictures");
        pics.add(new DefaultMutableTreeNode("vacation.jpg"));
        pics.add(new DefaultMutableTreeNode("profile.png"));
        root.add(pics);

        DefaultMutableTreeNode projects = new DefaultMutableTreeNode("Projects");
        DefaultMutableTreeNode edpProject = new DefaultMutableTreeNode("event-driven-programming-java");
        edpProject.add(new DefaultMutableTreeNode("week01-edp"));
        edpProject.add(new DefaultMutableTreeNode("week02-observer-pattern"));
        edpProject.add(new DefaultMutableTreeNode("week05-advanced-swing"));
        projects.add(edpProject);
        projects.add(new DefaultMutableTreeNode("website"));
        root.add(projects);

        JTree tree = new JTree(root);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (node != null) {
                boolean isLeaf = node.isLeaf();
                infoArea.setText("Selected: " + node.getUserObject() +
                    "\nType: " + (isLeaf ? "File" : "Folder") +
                    "\nPath: " + buildPath(node));
            }
        });
        // Expand top level by default
        tree.expandRow(0);

        // ---- Info area (south strip) ----------------------------------------
        infoArea = new JTextArea(3, 40);
        infoArea.setEditable(false);
        infoArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        infoArea.setText("Click a tree node to see details here.");

        // ---- Tabbed pane ----------------------------------------------------
        JTabbedPane tabs = new JTabbedPane();

        // Tab 1 – General
        JPanel generalPanel = new JPanel(new GridLayout(0, 1, 4, 4));
        generalPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        generalPanel.add(new JCheckBox("Show hidden files", false));
        generalPanel.add(new JCheckBox("Show file extensions", true));
        generalPanel.add(new JCheckBox("Confirm before delete", true));
        generalPanel.add(new JCheckBox("Open with single click", false));
        tabs.addTab("General", generalPanel);

        // Tab 2 – Appearance
        JPanel appearPanel = new JPanel(new GridLayout(0, 1, 4, 4));
        appearPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        ButtonGroup bg = new ButtonGroup();
        for (String theme : new String[]{"System Default", "Light", "Dark", "High Contrast"}) {
            JRadioButton rb = new JRadioButton(theme, theme.equals("System Default"));
            bg.add(rb);
            appearPanel.add(rb);
        }
        tabs.addTab("Appearance", appearPanel);

        // Tab 3 – About
        JTextArea aboutText = new JTextArea(
            "File Explorer Demo\n" +
            "Week 5 – Activity 3\n\n" +
            "Demonstrates:\n" +
            " • JTree with custom nodes\n" +
            " • TreeSelectionListener\n" +
            " • JTabbedPane (3 tabs)\n" +
            " • JSplitPane layout\n\n" +
            "Event-Driven Programming – Java Course");
        aboutText.setEditable(false);
        aboutText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tabs.addTab("About", new JScrollPane(aboutText));

        // ---- Split pane ----------------------------------------------------
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
            new JScrollPane(tree), tabs);
        split.setDividerLocation(250);

        // ---- Assemble -------------------------------------------------------
        add(split, BorderLayout.CENTER);
        add(new JScrollPane(infoArea), BorderLayout.SOUTH);

        setVisible(true);
    }

    // Build a human-readable path string for a tree node
    private String buildPath(DefaultMutableTreeNode node) {
        StringBuilder sb = new StringBuilder();
        TreeNode[] pathNodes = node.getPath();
        for (int i = 0; i < pathNodes.length; i++) {
            if (i > 0) sb.append(" / ");
            sb.append(pathNodes[i]);
        }
        return sb.toString();
    }

    // ---- Entry point -------------------------------------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FileExplorerDemo::new);
    }
}
