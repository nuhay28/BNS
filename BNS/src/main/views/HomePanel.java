package main.views;

import javax.swing.*;
import java.awt.*;
import main.Application;

public class HomePanel extends JPanel{
    public HomePanel(Application app) {
        setBackground(new Color(15, 23, 42)); // slate-950
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Title
        JLabel title = new JLabel("<html><center>Hospital Bed<br><font color='#818cf8'>Notification System</font></center></html>");
        title.setFont(new Font("SansSerif", Font.BOLD, 50));
        title.setForeground(Color.WHITE);
        
        // Subtitle
        JLabel sub = new JLabel("A modern, lightweight system for real-time bed assignment.");
        sub.setForeground(new Color(203, 213, 225)); // slate-300
        sub.setFont(new Font("SansSerif", Font.PLAIN, 18));

        // Buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnPanel.setOpaque(false);
        
        JButton loginBtn = new JButton("Log In");
        JButton aboutBtn = new JButton("About Us");

        loginBtn.addActionListener(e -> app.showPage("LOGIN"));
        aboutBtn.addActionListener(e -> app.showPage("ABOUT"));

        btnPanel.add(loginBtn);
        btnPanel.add(aboutBtn);

        // Layout Adding
        gbc.gridx = 0; gbc.gridy = 0; gbc.insets = new Insets(10,10,10,10);
        add(title, gbc);
        gbc.gridy = 1;
        add(sub, gbc);
        gbc.gridy = 2;
        add(btnPanel, gbc);
    } 
}
