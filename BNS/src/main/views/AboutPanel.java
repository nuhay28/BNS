package main.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import main.Application;

public class AboutPanel extends JPanel {
    public AboutPanel(Application app) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Header Section
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(new EmptyBorder(40, 40, 20, 40));

        JButton backBtn = new JButton("<- Back to Portal");
        backBtn.addActionListener(e -> app.showPage("HOME"));
        
        JLabel title = new JLabel("About BNS", SwingConstants.LEFT);
        title.setFont(new Font("SansSerif", Font.ITALIC | Font.BOLD, 48));
        title.setForeground(new Color(79, 70, 229)); // indigo-600

        header.add(backBtn, BorderLayout.NORTH);
        header.add(title, BorderLayout.SOUTH);
