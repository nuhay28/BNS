package main.views;

import javax.swing.*;
import java.awt.*;
import main.Application;
import main.views.components.MedicalCard;

public class DashboardPanel extends JPanel {
    public DashboardPanel(Application app) {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(248, 250, 252)); // slate-50
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // 1. Header Section
        JPanel header = new JPanel(new GridLayout(2, 1));
        header.setOpaque(false);
        
        JLabel welcome = new JLabel("Welcome, Dr. John Doe");
        welcome.setFont(new Font("SansSerif", Font.BOLD, 36));
        
        JLabel role = new JLabel("Medical Role: Senior Surgeon");
        role.setForeground(Color.GRAY);
        
        header.add(welcome);
        header.add(role);
        add(header, BorderLayout.NORTH);

        // 2. Grid Section (The Cards)
        JPanel grid = new JPanel(new GridLayout(0, 3, 20, 20)); // 3 columns
        grid.setOpaque(false);

        grid.add(new MedicalCard("Bed Management", "Assign and monitor ward beds", 
            new Color(20, 184, 166), () -> app.showPage("BEDS")));
            
        grid.add(new MedicalCard("Notifications", "New admissions & alerts", 
            new Color(245, 158, 11), () -> app.showPage("NOTIFS")));
            
        grid.add(new MedicalCard("My Assignments", "Your current ward duties", 
            new Color(59, 130, 246), () -> app.showPage("TASKS")));

        add(grid, BorderLayout.CENTER);

        // 3. Floating Button (Menu)
        JButton menuBtn = new JButton("☰");
        menuBtn.setFont(new Font("SansSerif", Font.BOLD, 20));
        menuBtn.setBackground(new Color(30, 27, 75)); // indigo-950
        menuBtn.setForeground(Color.WHITE);
        menuBtn.setPreferredSize(new Dimension(60, 60));
        
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        footer.setOpaque(false);
        footer.add(menuBtn);
        add(footer, BorderLayout.SOUTH);
    }
}