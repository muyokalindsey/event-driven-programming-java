import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class RegistrationForm extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JRadioButton maleRadio, femaleRadio, otherRadio;
    private ButtonGroup genderGroup;
    private JCheckBox sportsCheck, musicCheck, readingCheck;
    private JComboBox<String> countryBox;
    private JTextArea commentsArea;
    private JLabel statusLabel;
    private JLabel nameHintLabel;

    public RegistrationForm() {
        setTitle("Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 580);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ---- Title ----
        JLabel titleLabel = new JLabel("Registration Form", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        add(titleLabel, BorderLayout.NORTH);

        // ---- Form Panel ----
        JPanel form = new JPanel(new GridLayout(10, 2, 8, 8));
        form.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        form.add(new JLabel("Full Name:"));
        nameField = new JTextField(30);
        form.add(nameField);

        form.add(new JLabel("Email:"));
        emailField = new JTextField(30);
        form.add(emailField);

        form.add(new JLabel("Password:"));
        passwordField = new JPasswordField(30);
        form.add(passwordField);

        form.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        otherRadio = new JRadioButton("Other");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderGroup.add(otherRadio);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        genderPanel.add(otherRadio);
        form.add(genderPanel);

        form.add(new JLabel("Interests:"));
        JPanel interestPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        sportsCheck = new JCheckBox("Sports");
        musicCheck = new JCheckBox("Music");
        readingCheck = new JCheckBox("Reading");
        interestPanel.add(sportsCheck);
        interestPanel.add(musicCheck);
        interestPanel.add(readingCheck);
        form.add(interestPanel);

        form.add(new JLabel("Country:"));
        countryBox = new JComboBox<>(new String[]{"Kenya", "Tanzania", "Uganda", "Rwanda"});
        form.add(countryBox);

        form.add(new JLabel("Comments:"));
        commentsArea = new JTextArea(3, 30);
        commentsArea.setLineWrap(true);
        form.add(new JScrollPane(commentsArea));

        // Live name hint (DocumentListener demo)
        form.add(new JLabel("Name preview:"));
        nameHintLabel = new JLabel("(type your name above)");
        nameHintLabel.setForeground(Color.GRAY);
        form.add(nameHintLabel);
        nameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { update(); }
            @Override public void removeUpdate(DocumentEvent e) { update(); }
            @Override public void changedUpdate(DocumentEvent e) {}
            private void update() { nameHintLabel.setText(nameField.getText().isEmpty() ? "(type your name above)" : "Hello, " + nameField.getText() + "!"); }
        });

        add(form, BorderLayout.CENTER);

        // ---- Buttons + Status ----
        JPanel south = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(0, 170, 0));
        submitButton.setForeground(Color.WHITE);

        JButton clearButton = new JButton("Clear");
        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(200, 50, 50));
        exitButton.setForeground(Color.WHITE);

        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);

        statusLabel = new JLabel("Status: Ready", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 8, 10));

        south.add(buttonPanel, BorderLayout.NORTH);
        south.add(statusLabel, BorderLayout.SOUTH);
        add(south, BorderLayout.SOUTH);

        // ---- Submit handler ----
        submitButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (name.isEmpty()) {
                statusLabel.setText("Error: Please enter your name");
                statusLabel.setForeground(Color.RED);
                return;
            }
            if (email.isEmpty() || !email.contains("@")) {
                statusLabel.setText("Error: Please enter a valid email");
                statusLabel.setForeground(Color.RED);
                return;
            }
            if (password.length() < 6) {
                statusLabel.setText("Error: Password must be at least 6 characters");
                statusLabel.setForeground(Color.RED);
                return;
            }

            String gender = "Not specified";
            if (maleRadio.isSelected())   gender = "Male";
            else if (femaleRadio.isSelected()) gender = "Female";
            else if (otherRadio.isSelected())  gender = "Other";

            StringBuilder interests = new StringBuilder();
            if (sportsCheck.isSelected())  interests.append("Sports ");
            if (musicCheck.isSelected())   interests.append("Music ");
            if (readingCheck.isSelected()) interests.append("Reading ");

            String country = (String) countryBox.getSelectedItem();
            String comments = commentsArea.getText().trim();

            String message = "Registration Successful!\n\n"
                    + "Name: " + name + "\n"
                    + "Email: " + email + "\n"
                    + "Gender: " + gender + "\n"
                    + "Interests: " + (interests.length() > 0 ? interests.toString() : "None") + "\n"
                    + "Country: " + country + "\n"
                    + "Comments: " + (comments.isEmpty() ? "None" : comments);

            JOptionPane.showMessageDialog(this, message, "Registration Complete", JOptionPane.INFORMATION_MESSAGE);
            statusLabel.setText("Registration submitted successfully!");
            statusLabel.setForeground(new Color(0, 128, 0));
        });

        // ---- Clear handler ----
        clearButton.addActionListener(e -> {
            nameField.setText("");
            emailField.setText("");
            passwordField.setText("");
            genderGroup.clearSelection();
            sportsCheck.setSelected(false);
            musicCheck.setSelected(false);
            readingCheck.setSelected(false);
            countryBox.setSelectedIndex(0);
            commentsArea.setText("");
            statusLabel.setText("Status: Form cleared");
            statusLabel.setForeground(Color.BLACK);
        });

        // ---- Exit handler ----
        exitButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to exit?", "Confirm Exit",
                    JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationForm().setVisible(true));
    }
}
