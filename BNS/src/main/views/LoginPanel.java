package main.views;

import javax.swing.*;
import java.awt.*;
import main.Application;
import main.services.AuthService;

public class LoginPanel extends JPanel {
    private JTextField emailField;
    private JPasswordField passField;
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

}

