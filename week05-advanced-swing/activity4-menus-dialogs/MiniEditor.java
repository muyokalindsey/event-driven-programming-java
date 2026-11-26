import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Week 5 – Activity 4: Mini Text Editor with menus, toolbar, file I/O,
 *   real-time word count via DocumentListener.
 */
public class MiniEditor extends JFrame {

    private JTextArea textArea;
    private JLabel    wordCountLabel;
    private JLabel    statusBar;
    private File      currentFile = null;
    private boolean   modified    = false;

    // -----------------------------------------------------------------------
    public MiniEditor() {
        super("Mini Editor – Week 5 Activity 4");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(800, 580);
        setLocationRelativeTo(null);

        // ---- Text area -----------------------------------------------------
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e)  { updateWordCount(); modified = true; }
            @Override public void removeUpdate(DocumentEvent e)  { updateWordCount(); modified = true; }
            @Override public void changedUpdate(DocumentEvent e) { updateWordCount(); }
        });

        // ---- Menu bar ------------------------------------------------------
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        JMenuItem newItem    = new JMenuItem("New",         KeyEvent.VK_N);
        JMenuItem openItem   = new JMenuItem("Open…",       KeyEvent.VK_O);
        JMenuItem saveItem   = new JMenuItem("Save",        KeyEvent.VK_S);
        JMenuItem saveAsItem = new JMenuItem("Save As…");
        JMenuItem exitItem   = new JMenuItem("Exit");
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        newItem.addActionListener(e -> newDocument());
        openItem.addActionListener(e -> openFile());
        saveItem.addActionListener(e -> saveFile());
        saveAsItem.addActionListener(e -> saveFileAs());
        exitItem.addActionListener(e -> exitApp());
        fileMenu.add(newItem); fileMenu.add(openItem);
        fileMenu.addSeparator();
        fileMenu.add(saveItem); fileMenu.add(saveAsItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Edit menu
        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic('E');
        JMenuItem cutItem   = new JMenuItem("Cut");
        JMenuItem copyItem  = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");
        cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        cutItem.addActionListener(e -> textArea.cut());
        copyItem.addActionListener(e -> textArea.copy());
        pasteItem.addActionListener(e -> textArea.paste());
        editMenu.add(cutItem); editMenu.add(copyItem); editMenu.add(pasteItem);

        // Help menu
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('H');
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(this,
            "Mini Editor\nWeek 5 – Activity 4\nEvent-Driven Programming in Java",
            "About", JOptionPane.INFORMATION_MESSAGE));
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu); menuBar.add(editMenu); menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        // ---- Toolbar -------------------------------------------------------
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        JButton tbNew   = new JButton("New");
        JButton tbOpen  = new JButton("Open");
        JButton tbSave  = new JButton("Save");
        wordCountLabel = new JLabel("  Words: 0");
        wordCountLabel.setFont(wordCountLabel.getFont().deriveFont(Font.ITALIC, 12f));
        tbNew.addActionListener(e -> newDocument());
        tbOpen.addActionListener(e -> openFile());
        tbSave.addActionListener(e -> saveFile());
        toolBar.add(tbNew); toolBar.add(tbOpen); toolBar.add(tbSave);
        toolBar.addSeparator();
        toolBar.add(wordCountLabel);

        // ---- Status bar ----------------------------------------------------
        statusBar = new JLabel(" Ready");
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        statusBar.setFont(statusBar.getFont().deriveFont(12f));

        // ---- Layout --------------------------------------------------------
        setLayout(new BorderLayout());
        add(toolBar, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);

        // ---- Window close --------------------------------------------------
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) { exitApp(); }
        });

        setVisible(true);
    }

    // ---- File operations ---------------------------------------------------
    private void newDocument() {
        if (confirmSaveIfModified()) {
            textArea.setText("");
            currentFile = null;
            modified = false;
            setTitle("Mini Editor – Untitled");
            status("New document created.");
        }
    }

    private void openFile() {
        if (!confirmSaveIfModified()) return;
        JFileChooser fc = fileChooser();
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            currentFile = fc.getSelectedFile();
            try {
                textArea.setText(Files.readString(currentFile.toPath()));
                modified = false;
                setTitle("Mini Editor – " + currentFile.getName());
                status("Opened: " + currentFile.getAbsolutePath());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error reading file:\n" + ex.getMessage(),
                    "Open Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveFile() {
        if (currentFile == null) { saveFileAs(); return; }
        writeCurrentFile(currentFile);
    }

    private void saveFileAs() {
        JFileChooser fc = fileChooser();
        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            if (!f.getName().endsWith(".txt")) f = new File(f.getAbsolutePath() + ".txt");
            writeCurrentFile(f);
        }
    }

    private void writeCurrentFile(File f) {
        try {
            Files.writeString(f.toPath(), textArea.getText());
            currentFile = f;
            modified = false;
            setTitle("Mini Editor – " + f.getName());
            status("Saved: " + f.getAbsolutePath());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving file:\n" + ex.getMessage(),
                "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean confirmSaveIfModified() {
        if (!modified) return true;
        int choice = JOptionPane.showConfirmDialog(this,
            "Document has unsaved changes. Save now?", "Unsaved Changes",
            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if (choice == JOptionPane.YES_OPTION)    { saveFile(); return !modified; }
        if (choice == JOptionPane.NO_OPTION)     { return true; }
        return false; // CANCEL
    }

    private void exitApp() {
        if (confirmSaveIfModified()) dispose();
    }

    private JFileChooser fileChooser() {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("Text Files (*.txt)", "txt"));
        if (currentFile != null) fc.setCurrentDirectory(currentFile.getParentFile());
        return fc;
    }

    // ---- Live word count ---------------------------------------------------
    private void updateWordCount() {
        String text = textArea.getText().trim();
        int words = text.isEmpty() ? 0 : text.split("\\s+").length;
        wordCountLabel.setText("  Words: " + words);
    }

    private void status(String msg) { statusBar.setText(" " + msg); }

    // ---- Entry point -------------------------------------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MiniEditor::new);
    }
}
