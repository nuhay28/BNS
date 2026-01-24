package main.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.regex.Pattern;
import main.Application;
import main.services.AuthService;

public class LoginPanel extends JPanel {
    private JTextField emailField;
    private JPasswordField passField;
    private JButton loginBtn;
    private AuthService authService = new AuthService();
    
    private Color indigo600 = new Color(79, 70, 229);
    private Color slate900 = new Color(15, 23, 42);
    private Color slate500 = new Color(100, 116, 139);
    private Color slate100 = new Color(241, 245, 249);

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";

    public LoginPanel(Application app) {
        setLayout(new GridLayout(1, 2));
        setBackground(Color.WHITE);

        // --- LEFT SIDE: BRANDING PANEL ---
        JPanel brandingPanel = new GradientOverlayPanel();
        brandingPanel.setLayout(new BoxLayout(brandingPanel, BoxLayout.Y_AXIS));
        brandingPanel.setBorder(new EmptyBorder(60, 60, 60, 60));

        JLabel version = new JLabel("v2.5.0 • ENTERPRISE SECURE");
        version.setForeground(new Color(255, 255, 255, 150));
        version.setFont(new Font("SansSerif", Font.BOLD, 10));

        JLabel heroText = new JLabel("<html><body style='width: 300px'>"
                + "<h1 style='color:white; font-size:40pt; font-style:italic;'>Efficiency in <br>"
                + "<font color='#818CF8'>Every Ward.</font></h1><br>"
                + "<p style='color:#CBD5E1; font-size:14pt;'>Streamlining bed management and patient notifications with real-time intelligence.</p>"
                + "</body></html>");
        
        brandingPanel.add(version);
        brandingPanel.add(Box.createVerticalGlue());
        brandingPanel.add(heroText);
        brandingPanel.add(Box.createVerticalGlue());

        // --- RIGHT SIDE: LOGIN FORM ---
        JPanel formWrapper = new JPanel(new GridBagLayout());
        formWrapper.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 40, 5, 40);

        // --- BACK BUTTON (Row 0) ---
        JButton backBtn = new JButton("← Back to Portal");
        backBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        backBtn.setForeground(indigo600);
        backBtn.setContentAreaFilled(false);
        backBtn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(79, 70, 229, 100), 1),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(e -> app.showPage("HOME"));
        
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 40, 30, 40);
        formWrapper.add(backBtn, gbc);

        // Header Section (Row 1)
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel signinTitle = new JLabel("Sign In");
        signinTitle.setFont(new Font("SansSerif", Font.BOLD, 32));
        gbc.gridy = 1; gbc.insets = new Insets(5, 40, 5, 40);
        formWrapper.add(signinTitle, gbc);

        JLabel subtitle = new JLabel("<html>Access your hospital dashboard.</html>");
        subtitle.setForeground(slate500);
        gbc.gridy = 2; gbc.insets = new Insets(0, 40, 30, 40);
        formWrapper.add(subtitle, gbc);

        // Email Field (Row 3 & 4)
        gbc.insets = new Insets(5, 40, 5, 40);
        JLabel emailLabel = new JLabel("YOUR EMAIL");
        emailLabel.setFont(new Font("SansSerif", Font.BOLD, 10));
        emailLabel.setForeground(slate500);
        gbc.gridy = 3;
        formWrapper.add(emailLabel, gbc);

        emailField = new StyledTextField("yourname@gmail.com");
        gbc.gridy = 4;
        formWrapper.add(emailField, gbc);

        // Password Field (Row 5 & 6)
        JLabel passLabel = new JLabel("PASSWORD");
        passLabel.setFont(new Font("SansSerif", Font.BOLD, 10));
        passLabel.setForeground(slate500);
        gbc.gridy = 5; gbc.insets = new Insets(20, 40, 5, 40);
        formWrapper.add(passLabel, gbc);

        passField = new StyledPasswordField();
        gbc.gridy = 6; gbc.insets = new Insets(5, 40, 5, 40);
        formWrapper.add(passField, gbc);

        // Show Password (Row 7)
        JCheckBox showPass = new JCheckBox("Show Password");
        showPass.setFont(new Font("SansSerif", Font.PLAIN, 12));
        showPass.setBackground(Color.WHITE);
        showPass.addActionListener(e -> {
            if (showPass.isSelected()) passField.setEchoChar((char) 0);
            else passField.setEchoChar('\u2022');
        });
        gbc.gridy = 7;
        formWrapper.add(showPass, gbc);

        // Login Button (Row 8)
        loginBtn = new JButton("Enter Dashboard →");
        loginBtn.setPreferredSize(new Dimension(0, 50));
        loginBtn.setBackground(Color.BLUE);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        loginBtn.setFocusPainted(false);
        gbc.gridy = 8; gbc.insets = new Insets(30, 40, 10, 40);
        formWrapper.add(loginBtn, gbc);

        // Register Link (Row 9)
        JButton regBtn = new JButton("New here? Register Facility");
        regBtn.setForeground(indigo600);
        regBtn.setContentAreaFilled(false);
        regBtn.setBorderPainted(false);
        gbc.gridy = 9;
        formWrapper.add(regBtn, gbc);

        regBtn.addActionListener(e -> app.showPage("REGISTER"));
        loginBtn.addActionListener(e -> handleLogin(app));

        add(brandingPanel);
        add(formWrapper);
    }

    private void handleLogin(Application app) {
        String email = emailField.getText();
        String password = new String(passField.getPassword());
        if (!Pattern.matches(EMAIL_PATTERN, email)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            if(authService.login(email, password)) {
                app.showPage("DASHBOARD");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid Credentials", "Auth Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    class GradientOverlayPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint gp = new GradientPaint(0, 0, new Color(15, 23, 42), getWidth(), getHeight(), new Color(79, 70, 229));
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    class StyledTextField extends JTextField {
        public StyledTextField(String placeholder) {
            setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(slate100, 2),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
            ));
            setFont(new Font("SansSerif", Font.PLAIN, 14));
        }
    }

    class StyledPasswordField extends JPasswordField {
        public StyledPasswordField() {
            setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(slate100, 2),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
            ));
        }
    }
}