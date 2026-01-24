package main.views;

import javax.swing.*;
import java.awt.*;
import main.Application;

public class RegisterPanel extends JPanel {
    public RegisterPanel(Application app) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Split Layout (Left: Branding, Right: Form)
        JPanel mainContainer = new JPanel(new GridLayout(1, 2));
        JButton backBtn = new JButton("<- Back to Portal");
        backBtn.addActionListener(e -> app.showPage("HOME"));        
        // Left Side Branding (Dark Background)
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(new Color(15, 23, 42));
        JLabel welcomeTxt = new JLabel("<html>Join the<br><font color='#818cf8'>Future of Care.</font></html>");
        welcomeTxt.setForeground(Color.WHITE);
        welcomeTxt.setFont(new Font("SansSerif", Font.BOLD, 36));
        leftPanel.add(welcomeTxt);

        // Right Side Form
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 20, 5, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // Form Fields
        rightPanel.add(new JLabel("FULL NAME"), gbc);
        JTextField nameField = new JTextField(20);
        rightPanel.add(nameField, gbc);

        rightPanel.add(new JLabel("WORK EMAIL"), gbc);
        JTextField emailField = new JTextField(20);
        rightPanel.add(emailField, gbc);

        rightPanel.add(new JLabel("PROFESSIONAL ROLE"), gbc);
        String[] roles = {"Clinical Year 1 (C1)", "Clinical Year 2 (C2)", "Medical Staff"};
        JComboBox<String> roleCombo = new JComboBox<>(roles);
        rightPanel.add(roleCombo, gbc);

        JCheckBox terms = new JCheckBox("I acknowledge the Medical Privacy Policy");
        rightPanel.add(terms, gbc);

        JButton submitBtn = new JButton("CREATE ACCOUNT");
        submitBtn.setBackground(new Color(79, 70, 229));
        submitBtn.setForeground(Color.WHITE);
        rightPanel.add(submitBtn, gbc);

        mainContainer.add(leftPanel);
        mainContainer.add(rightPanel);
        add(mainContainer);
    }
}