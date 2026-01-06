package main.views;

import javax.swing.*;
import java.awt.*;
import main.Application;
import main.services.AuthService;

public class LoginPanel extends JPanel {
    private JTextField emailField;
    private JPasswordField passField;
    private AuthService authService = new AuthService();
}

