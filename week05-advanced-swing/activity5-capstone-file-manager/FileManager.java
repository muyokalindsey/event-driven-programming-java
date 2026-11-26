import javax.swing.*;
import javax.swing.table.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Week 5 – Activity 5 Capstone: File Manager
 * Left: JTree of home directory folder structure.
 * Right: JTable showing folder contents (Name, Type, Size, Modified).
 * Top: menu bar + toolbar (Home / Up / Refresh).
 * Bottom: status bar with item count and current path.
 */
public class FileManager extends JFrame {

    // ---- Constants ---------------------------------------------------------
    private static final String HOME = System.getProperty("user.home");
    private static final SimpleDateFormat DATE_FMT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    // ---- UI fields ---------------------------------------------------------
    private JTree              tree;
    private DefaultTableModel  tableModel;
    private JTable             table;
    private JLabel             statusBar;
    private File               currentFolder;

    // -----------------------------------------------------------------------
    public FileManager() {
        super("File Manager – Week 5 Activity 5");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 560);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ---- Build tree ----------------------------------------------------
        File homeFile = new File(HOME);
        DefaultMutableTreeNode rootNode = buildFolderTree(homeFile, 2);
        tree = new JTree(rootNode);
        tree.setRootVisible(true);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (node != null && node.getUserObject() instanceof File f && f.isDirectory()) {
                loadFolderContents(f);
            }
        });

        // ---- Table ---------------------------------------------------------
        String[] cols = {"Name", "Type", "Size", "Modified"};
        tableModel = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(tableModel);
        table.setRowHeight(24);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Double-click on folder row → navigate into it
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && currentFolder != null) {
                    int row = table.rowAtPoint(e.getPoint());
                    if (row < 0) return;
                    String name = (String) tableModel.getValueAt(row, 0);
                    String type = (String) tableModel.getValueAt(row, 1);
                    if ("Folder".equals(type)) {
                        File sub = new File(currentFolder, name);
                        loadFolderContents(sub);
                    }
                }
            }
        });

        // ---- Toolbar -------------------------------------------------------
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        JButton homeBtn    = new JButton("⌂ Home");
        JButton upBtn      = new JButton("↑ Up");
        JButton refreshBtn = new JButton("↻ Refresh");
        homeBtn.addActionListener(e -> loadFolderContents(homeFile));
        upBtn.addActionListener(e -> {
            if (currentFolder != null && currentFolder.getParentFile() != null)
                loadFolderContents(currentFolder.getParentFile());
        });
        refreshBtn.addActionListener(e -> { if (currentFolder != null) loadFolderContents(currentFolder); });
        toolBar.add(homeBtn); toolBar.add(upBtn); toolBar.add(refreshBtn);

        // ---- Menu bar ------------------------------------------------------
        JMenuBar menuBar = new JMenuBar();
        JMenu viewMenu = new JMenu("View");
        JMenuItem homeItem    = new JMenuItem("Home");
        JMenuItem refreshItem = new JMenuItem("Refresh");
        homeItem.addActionListener(e -> loadFolderContents(homeFile));
        refreshItem.addActionListener(e -> { if (currentFolder != null) loadFolderContents(currentFolder); });
        viewMenu.add(homeItem); viewMenu.add(refreshItem);
        menuBar.add(viewMenu);
        setJMenuBar(menuBar);

        // ---- Status bar ----------------------------------------------------
        statusBar = new JLabel(" Ready – Home: " + HOME);
        statusBar.setBorder(BorderFactory.createEtchedBorder());

        // ---- Split pane ----------------------------------------------------
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
            new JScrollPane(tree), new JScrollPane(table));
        split.setDividerLocation(280);

        // ---- Assemble -------------------------------------------------------
        add(toolBar, BorderLayout.NORTH);
        add(split, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);

        // Load home directory
        loadFolderContents(homeFile);

        setVisible(true);
    }

    // ---- Build a JTree node structure from a folder (limited depth) --------
    private DefaultMutableTreeNode buildFolderTree(File folder, int depth) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(folder);
        if (depth <= 0 || !folder.isDirectory()) return node;
        File[] children = folder.listFiles();
        if (children == null) return node;
        for (File child : children) {
            if (child.isDirectory() && !child.isHidden()) {
                node.add(buildFolderTree(child, depth - 1));
            }
        }
        return node;
    }

    // ---- Populate the table with contents of a folder ----------------------
    private void loadFolderContents(File folder) {
        if (!folder.isDirectory()) return;
        currentFolder = folder;
        tableModel.setRowCount(0);

        File[] entries = folder.listFiles();
        int count = 0;
        if (entries != null) {
            // Folders first, then files, alphabetically
            java.util.Arrays.sort(entries, (a, b) -> {
                if (a.isDirectory() && !b.isDirectory()) return -1;
                if (!a.isDirectory() && b.isDirectory()) return 1;
                return a.getName().compareToIgnoreCase(b.getName());
            });
            for (File f : entries) {
                if (!f.isHidden()) {
                    String type    = f.isDirectory() ? "Folder" : getExtension(f);
                    String size    = f.isDirectory() ? "--" : humanSize(f.length());
                    String modified = DATE_FMT.format(new Date(f.lastModified()));
                    tableModel.addRow(new Object[]{f.getName(), type, size, modified});
                    count++;
                }
            }
        }
        statusBar.setText(" " + count + " item(s)  |  " + folder.getAbsolutePath());
    }

    // ---- Helpers -----------------------------------------------------------
    private String getExtension(File f) {
        String name = f.getName();
        int dot = name.lastIndexOf('.');
        return dot >= 0 ? name.substring(dot + 1).toUpperCase() + " File" : "File";
    }

    private String humanSize(long bytes) {
        if (bytes < 1024)        return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.1f KB", bytes / 1024.0);
        return String.format("%.1f MB", bytes / (1024.0 * 1024));
    }

    // ---- Entry point -------------------------------------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FileManager::new);
    }
}
