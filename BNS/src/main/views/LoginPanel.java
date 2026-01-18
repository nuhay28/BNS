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
}
}