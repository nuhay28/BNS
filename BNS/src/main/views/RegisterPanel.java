package main.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.regex.Pattern;
import main.Application;
import main.services.AuthService;

public class RegisterPanel extends JPanel {
    // Colors from React Theme
    private Color indigo600 = new Color(79, 70, 229);
    private Color indigo50 = new Color(238, 242, 255);
    private Color slate900 = new Color(15, 23, 42);
    private Color slate500 = new Color(100, 116, 139);
    private Color slate100 = new Color(241, 245, 249);

    // Class-level variables to fix the symbol errors
    private JTextField nameField = new JTextField();
    private JTextField emailField = new JTextField();
    private JTextField phoneField = new JTextField();
    private JPasswordField passField = new JPasswordField();
    private JComboBox<String> roleCombo;
    private AuthService authService = new AuthService();
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";

    public RegisterPanel(Application app) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel splitPane = new JPanel(new GridLayout(1, 2));

        // 1. LEFT SIDE: BRANDING
        JPanel leftPanel = new GradientBrandingPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(new EmptyBorder(40, 60, 60, 60));

        // --- BACK BUTTON ---
        JButton backBtn = createGhostButton("← Back to Portal", Color.WHITE);
        backBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        backBtn.addActionListener(e -> app.showPage("HOME"));
        leftPanel.add(backBtn);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JLabel badge = new JLabel("🛡️ HIPAA COMPLIANT ENCRYPTION");
        badge.setForeground(new Color(255, 255, 255, 180));
        badge.setFont(new Font("SansSerif", Font.BOLD, 10));
        badge.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel welcomeTxt = new JLabel("<html><body style='width: 350px'>"
                + "<h1 style='color:white; font-size:42pt; font-style:italic;'>Join the <br>"
                + "<font color='#818cf8'>Future of Care.</font></h1><br>"
                + "<p style='color:#CBD5E1; font-size:14pt;'>Empowering medical professionals with real-time bed intelligence.</p>"
                + "</body></html>");
        welcomeTxt.setAlignmentX(Component.LEFT_ALIGNMENT);

        leftPanel.add(badge);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(welcomeTxt);
        leftPanel.add(Box.createVerticalGlue());

        // 2. RIGHT SIDE: FORM
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(new EmptyBorder(40, 60, 40, 60));

        JLabel title = new JLabel("Create Account");
        title.setFont(new Font("SansSerif", Font.BOLD, 32));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JButton loginLink = new JButton("Already have an account? Sign in here");
        loginLink.setForeground(indigo600);
        loginLink.setFont(new Font("SansSerif", Font.BOLD, 13));
        loginLink.setBorderPainted(false);
        loginLink.setContentAreaFilled(false);
        loginLink.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginLink.addActionListener(e -> app.showPage("LOGIN"));

        rightPanel.add(title);
        rightPanel.add(loginLink);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel fieldGrid = new JPanel(new GridLayout(0, 1, 0, 15));
        fieldGrid.setOpaque(false);
        fieldGrid.setMaximumSize(new Dimension(500, 600));

        fieldGrid.add(createLabeledField("FULL NAME", nameField, "Dr. John Doe"));
        fieldGrid.add(createLabeledField("WORK EMAIL", emailField, "yourname@gmail.com"));
        fieldGrid.add(createLabeledField("PHONE NUMBER", phoneField, "0911223344"));
        
        fieldGrid.add(createLabeledField("PASSWORD", passField, "••••••••"));

        JLabel roleLabel = new JLabel("PROFESSIONAL ROLE");
        roleLabel.setFont(new Font("SansSerif", Font.BOLD, 10));
        roleLabel.setForeground(slate500);
        String[] roles = {"Clinical Year 1 (C1)", "Clinical Year 2 (C2)", "Medical Staff (Intern)"};
        roleCombo = new JComboBox<>(roles);
        roleCombo.setPreferredSize(new Dimension(0, 45));
        fieldGrid.add(roleLabel);
        fieldGrid.add(roleCombo);

        rightPanel.add(fieldGrid);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel statusBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusBox.setBackground(indigo50);
        statusBox.setBorder(BorderFactory.createLineBorder(new Color(199, 210, 254), 1));
        JLabel statusLbl = new JLabel("Verified Medical Access Enabled");
        statusLbl.setFont(new Font("SansSerif", Font.ITALIC | Font.BOLD, 12));
        statusLbl.setForeground(new Color(49, 46, 129));
        statusBox.add(statusLbl);
        rightPanel.add(statusBox);

        rightPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        JButton submitBtn = new JButton("CREATE ACCOUNT →");
        submitBtn.setMaximumSize(new Dimension(500, 55));
        submitBtn.setBackground(indigo600);
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        submitBtn.setFocusPainted(false);
        submitBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        submitBtn.addActionListener(e -> handleRegister(app));
        rightPanel.add(submitBtn);

        splitPane.add(leftPanel);
        splitPane.add(rightPanel);
        add(splitPane, BorderLayout.CENTER);
    }

    private JButton createGhostButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setForeground(color);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(color.getRed(), color.getGreen(), color.getBlue(), 100), 1),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void handleRegister(Application app) {
        String email = emailField.getText();
        String password = new String(passField.getPassword());
        
        if (!Pattern.matches(EMAIL_PATTERN, email)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            // Assuming your AuthService has a register method, otherwise update to match your API
            if(authService.login(email, password)) { 
                app.showPage("DASHBOARD");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Registration Failed: " + ex.getMessage(), "Auth Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel createLabeledField(String label, JTextField field, String placeholder) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setOpaque(false);
        JLabel l = new JLabel(label);
        l.setFont(new Font("SansSerif", Font.BOLD, 10));
        l.setForeground(slate500);
        l.setBorder(new EmptyBorder(0, 5, 5, 0));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(slate100, 2),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        p.add(l);
        p.add(field);
        return p;
    }

    class GradientBrandingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint gp = new GradientPaint(0, 0, slate900, getWidth(), getHeight(), indigo600);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}