package main;

import javax.swing.*;
import java.awt.*;
import main.views.*;

public class Application extends JFrame {
    public CardLayout cardLayout = new CardLayout();
    public JPanel container = new JPanel(cardLayout);

    public Application() {
        setTitle("BNS - Hospital Management");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Adding "Pages" to the container
        container.add(new HomePanel(this), "HOME");
        container.add(new AboutPanel(this), "ABOUT");
        container.add(new LoginPanel(this), "LOGIN");
        container.add(new RegisterPanel(this), "REGISTER");
        container.add(new DashboardPanel(this), "DASHBOARD");

        add(container);
        cardLayout.show(container, "HOME"); // Start at Home
        setVisible(true);
    }
