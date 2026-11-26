import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class SmartHomePrototype extends JFrame {
    private JLabel statusLabel;
    private JLabel tempLabel;
    private JTextArea eventLog;
    private JToggleButton lightsToggle;
    private JToggleButton securityToggle;
    private JButton doorButton;
    private JButton resetAlarmButton;
    private JSlider thermostatSlider;
    private JPanel statusPanel;

    public SmartHomePrototype() {
        setTitle("Smart Home Controller");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Status Panel (NORTH)
        statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        statusLabel = new JLabel("System Ready");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        tempLabel = new JLabel("Target Temp: 22°C");
        tempLabel.setFont(new Font("Arial", Font.BOLD, 14));
        statusPanel.add(statusLabel, BorderLayout.WEST);
        statusPanel.add(tempLabel, BorderLayout.EAST);
        add(statusPanel, BorderLayout.NORTH);

        // Event Log (CENTER)
        eventLog = new JTextArea(10, 35);
        eventLog.setEditable(false);
        add(new JScrollPane(eventLog), BorderLayout.CENTER);

        // Control Panel (SOUTH)
        JPanel controlPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 1. Lights Toggle
        lightsToggle = new JToggleButton("Lights: OFF");
        lightsToggle.addActionListener(e -> {
            if (lightsToggle.isSelected()) {
                lightsToggle.setText("Lights: ON");
                logEvent("Lights turned ON");
                statusLabel.setText("Lights are on");
            } else {
                lightsToggle.setText("Lights: OFF");
                logEvent("Lights turned OFF");
                statusLabel.setText("Lights are off");
            }
        });

        // 2. Security Toggle
        securityToggle = new JToggleButton("Security: OFF");
        securityToggle.addActionListener(e -> {
            if (securityToggle.isSelected()) {
                securityToggle.setText("Security: ARMED");
                logEvent("Security system ARMED");
                statusLabel.setText("Security armed");
            } else {
                securityToggle.setText("Security: OFF");
                statusPanel.setBackground(null);
                statusLabel.setForeground(Color.BLACK);
                logEvent("Security system DISARMED");
                statusLabel.setText("Security disarmed");
            }
        });

        // 3. Door Sensor Button
        doorButton = new JButton("Simulate Door Open");
        doorButton.addActionListener(e -> {
            logEvent("Door sensor triggered");
            if (securityToggle.isSelected()) {
                logEvent("ALARM! Intruder detected!");
                statusLabel.setText("ALARM ACTIVE!");
                statusPanel.setBackground(Color.RED);
                statusLabel.setForeground(Color.WHITE);
            } else {
                logEvent("Door opened (no alarm)");
                statusLabel.setText("Door opened");
            }
        });

        // 4. Thermostat Slider
        thermostatSlider = new JSlider(16, 30, 22);
        thermostatSlider.setMajorTickSpacing(2);
        thermostatSlider.setPaintTicks(true);
        thermostatSlider.setPaintLabels(true);
        thermostatSlider.addChangeListener(e -> {
            int temp = thermostatSlider.getValue();
            tempLabel.setText("Target Temp: " + temp + "°C");
            if (!thermostatSlider.getValueIsAdjusting()) {
                logEvent("Thermostat set to " + temp + "°C");
            }
        });

        // 5. Reset Alarm Button
        resetAlarmButton = new JButton("Reset Alarm");
        resetAlarmButton.addActionListener(e -> {
            statusPanel.setBackground(null);
            statusLabel.setForeground(Color.BLACK);
            statusLabel.setText("Alarm cleared - System Ready");
            logEvent("Alarm reset by user");
        });

        controlPanel.add(lightsToggle);
        controlPanel.add(securityToggle);
        controlPanel.add(doorButton);
        controlPanel.add(thermostatSlider);
        controlPanel.add(resetAlarmButton);
        add(controlPanel, BorderLayout.SOUTH);

        setSize(480, 500);
        setLocationRelativeTo(null);
    }

    private void logEvent(String event) {
        String timestamp = new Date().toString();
        eventLog.append("[" + timestamp + "] " + event + "\n");
        eventLog.setCaretPosition(eventLog.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SmartHomePrototype().setVisible(true);
        });
    }
}
