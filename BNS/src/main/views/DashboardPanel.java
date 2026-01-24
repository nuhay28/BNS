package main.views;

import javax.swing.*;
import java.awt.*;
import main.Application;
import main.views.components.MedicalCard;

public class DashboardPanel extends JPanel {
    private boolean isSubscribed = true;
    private int daysLeft = 24;
    private String userRole = "Senior Surgeon"; 
    private String userName = "Peniel";

    public DashboardPanel(Application app) {
        setLayout(new BorderLayout());
        setBackground(new Color(248, 250, 252)); // slate-50

        // --- 1. DYNAMIC SUBSCRIPTION BANNER ---
        JPanel banner = createSubscriptionBanner();
        add(banner, BorderLayout.NORTH);

        // --- 2. MAIN CONTENT CONTAINER ---
        JPanel centerContainer = new JPanel(new BorderLayout(20, 20));
        centerContainer.setOpaque(false);
        centerContainer.setBorder(BorderFactory.createEmptyBorder(30, 40, 40, 40));

        // Medical Header
        JPanel headerGrp = new JPanel(new BorderLayout());
        headerGrp.setOpaque(false);

        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setOpaque(false);
        
        JLabel systemTag = new JLabel("HOSPITAL SYSTEM V1.0");
        systemTag.setFont(new Font("SansSerif", Font.BOLD, 10));
        systemTag.setForeground(new Color(37, 99, 235)); // blue-600
        
        JLabel welcome = new JLabel("Clinical Dashboard");
        welcome.setFont(new Font("SansSerif", Font.BOLD, 32));
        
        titlePanel.add(systemTag);
        titlePanel.add(welcome);

        // --- RIGHT SIDE: PROFILE & LOGOUT ---
        JPanel rightHeaderSide = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        rightHeaderSide.setOpaque(false);

        // Profile Info Card
        JPanel profileCard = new JPanel(new GridLayout(2, 1));
        profileCard.setOpaque(true);
        profileCard.setBackground(Color.WHITE);
        profileCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(241, 245, 249)),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        
        JLabel pLabel = new JLabel("Practitioner: Dr. " + userName);
        pLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        JLabel rLabel = new JLabel("Duty Role: " + userRole.toUpperCase());
        rLabel.setFont(new Font("SansSerif", Font.PLAIN, 10));
        rLabel.setForeground(new Color(13, 148, 136)); // teal-600
        
        profileCard.add(pLabel);
        profileCard.add(rLabel);

        // --- THE LOGOUT BUTTON ---
        JButton logoutBtn = new JButton("LOGOUT");
        logoutBtn.setFont(new Font("SansSerif", Font.BOLD, 11));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setBackground(new Color(239, 68, 68)); // Tailwind red-500
        logoutBtn.setFocusPainted(false);
        logoutBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        logoutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Action for logout
        logoutBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to terminate the session?", 
                "System Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                app.showPage("LOGIN"); // or wherever your starting page is
            }
        });

        rightHeaderSide.add(profileCard);
        rightHeaderSide.add(logoutBtn);

        headerGrp.add(titlePanel, BorderLayout.WEST);
        headerGrp.add(rightHeaderSide, BorderLayout.EAST);
        centerContainer.add(headerGrp, BorderLayout.NORTH);

        // --- 3. MODULE GRID ---
        JPanel grid = new JPanel(new GridLayout(0, 3, 25, 25)); 
        grid.setOpaque(false);

        grid.add(new MedicalCard("Ward Beds", "Assign and monitor ward beds", 
            new Color(13, 148, 136), () -> app.showPage("BEDS")));

        if (!userRole.equalsIgnoreCase("intern")) {
            grid.add(new MedicalCard("Notifications", "New admissions & alerts", 
                new Color(37, 99, 235), () -> app.showPage("NOTIFS")));
                
            grid.add(new MedicalCard("Assignments", "Your current ward duties", 
                new Color(124, 58, 237), () -> app.showPage("ASSIGNMENTS")));

            grid.add(new MedicalCard("Support", "Get help and FAQs", 
                new Color(234, 88, 12), () -> app.showPage("SUPPORT")));
            
            // Added About BNS card
            grid.add(new MedicalCard("About BNS", "Institutional protocols", 
                new Color(71, 85, 105), () -> app.showPage("ABOUT")));
        }

        centerContainer.add(grid, BorderLayout.CENTER);

        // --- 4. FOOTER ---
        JButton menuBtn = new JButton("MENU");
        menuBtn.setBackground(new Color(15, 23, 42)); // slate-900
        menuBtn.setForeground(Color.WHITE);
        menuBtn.setFocusPainted(false);
        menuBtn.setPreferredSize(new Dimension(100, 45));
        
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        footer.setOpaque(false);
        footer.add(menuBtn);
        centerContainer.add(footer, BorderLayout.SOUTH);

        add(centerContainer, BorderLayout.CENTER);
    }

    private JPanel createSubscriptionBanner() {
        JPanel banner = new JPanel();
        JLabel label = new JLabel();
        label.setFont(new Font("SansSerif", Font.BOLD, 11));

        if (isSubscribed) {
            banner.setBackground(new Color(209, 250, 229)); // emerald-100
            label.setText("SUBSCRIPTION ACTIVE \u2022 " + daysLeft + " DAYS REMAINING");
            label.setForeground(new Color(6, 95, 70)); // emerald-800
        } else {
            banner.setBackground(new Color(254, 243, 199)); // amber-100
            label.setText("PENDING SUBSCRIPTION: CLINICAL MODULES ARE IN VIEW-ONLY MODE");
            label.setForeground(new Color(146, 64, 14)); // amber-800
        }

        banner.add(label);
        return banner;
    }
}