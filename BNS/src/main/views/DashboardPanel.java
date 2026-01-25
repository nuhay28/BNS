package main.views;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import main.Application;
import main.views.components.MedicalCard;

public class DashboardPanel extends JPanel {
    private boolean isSubscribed = true;
    private int daysLeft = 24;
    private String userRole = "Senior Surgeon"; 
    private String userName = "Peniel";

    // Real Data Mapping
    private final Map<String, String[]> deptData = new HashMap<>() {{
        put("Internal Medicine", new String[]{"Ward C", "Ward D", "Inpatient", "Stroke", "Kalazar"});
        put("GynObs", new String[]{"Labor", "M1 up", "M1 down", "M2 (post natal)", "M3 (high risk)", "Gyn ward", "Michu", "Fistula"});
        put("Pediatrics", new String[]{"ETAT", "Emergency", "Main ward", "Nutrition and infant", "Hemato Oncology", "NICU"});
        put("Surgery", new String[]{"Trauma EOPD", "Non trauma EOPD", "Recovery", "Surgical ward", "Ortho ward"});
        put("Emergency", new String[]{"Red", "Orange", "Yellow"});
        put("Dermatology", new String[]{"Dermatology"});
        put("Psychiatry", new String[]{"Psychiatry"});
        put("ENT", new String[]{"ENT"});
        put("Ophthalmology", new String[]{"Ophthalmology"});
    }};

    public DashboardPanel(Application app) {
        setLayout(new BorderLayout());
        setBackground(new Color(248, 250, 252));

        add(createSubscriptionBanner(), BorderLayout.NORTH);

        JPanel centerContainer = new JPanel(new BorderLayout(20, 20));
        centerContainer.setOpaque(false);
        centerContainer.setBorder(BorderFactory.createEmptyBorder(30, 40, 40, 40));

        // Header Section
        JPanel headerGrp = new JPanel(new BorderLayout());
        headerGrp.setOpaque(false);
        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setOpaque(false);
        JLabel systemTag = new JLabel("HOSPITAL SYSTEM V1.0");
        systemTag.setFont(new Font("SansSerif", Font.BOLD, 10));
        systemTag.setForeground(new Color(37, 99, 235));
        JLabel welcome = new JLabel("Clinical Dashboard");
        welcome.setFont(new Font("SansSerif", Font.BOLD, 32));
        titlePanel.add(systemTag);
        titlePanel.add(welcome);

        // Profile Section
        JPanel rightHeaderSide = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        rightHeaderSide.setOpaque(false);
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
        rLabel.setForeground(new Color(13, 148, 136));
        profileCard.add(pLabel);
        profileCard.add(rLabel);

        JButton logoutBtn = new JButton("LOGOUT");
        logoutBtn.setFont(new Font("SansSerif", Font.BOLD, 11));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setBackground(new Color(239, 68, 68));
        logoutBtn.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Terminate session?", "Logout", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                app.showPage("LOGIN");
            }
        });

        rightHeaderSide.add(profileCard);
        rightHeaderSide.add(logoutBtn);
        headerGrp.add(titlePanel, BorderLayout.WEST);
        headerGrp.add(rightHeaderSide, BorderLayout.EAST);
        centerContainer.add(headerGrp, BorderLayout.NORTH);

        // Module Grid
        JPanel grid = new JPanel(new GridLayout(0, 3, 25, 25)); 
        grid.setOpaque(false);
        grid.add(new MedicalCard("Ward Beds", "Assign and monitor ward beds", new Color(13, 148, 136), () -> app.showPage("BEDS")));
        if (!userRole.equalsIgnoreCase("intern")) {
            grid.add(new MedicalCard("Notifications", "New admissions & alerts", new Color(37, 99, 235), () -> app.showPage("NOTIFS")));
            grid.add(new MedicalCard("Assignments", "Your current ward duties", new Color(124, 58, 237), () -> app.showPage("ASSIGNMENTS")));
            grid.add(new MedicalCard("Support", "Get help and FAQs", new Color(234, 88, 12), () -> app.showPage("SUPPORT")));
            grid.add(new MedicalCard("About BNS", "Institutional protocols", new Color(71, 85, 105), () -> app.showPage("ABOUT")));
        }
        centerContainer.add(grid, BorderLayout.CENTER);

        // Footer & Menu
        JButton menuBtn = new JButton("MENU");
        menuBtn.setBackground(new Color(15, 23, 42));
        menuBtn.setForeground(Color.WHITE);
        menuBtn.setPreferredSize(new Dimension(100, 45));
        
        menuBtn.addActionListener(e -> {
            JPanel menuPanel = new JPanel(new GridLayout(0, 2, 10, 10));
            
            String[] depts = deptData.keySet().toArray(new String[0]);
            JComboBox<String> deptCombo = new JComboBox<>(depts);
            JComboBox<String> wardCombo = new JComboBox<>(deptData.get(depts[0]));
            
            // Link Wards to Departments
            deptCombo.addActionListener(event -> {
                String selected = (String) deptCombo.getSelectedItem();
                wardCombo.setModel(new DefaultComboBoxModel<>(deptData.get(selected)));
            });

            JTextField deptExpiry = new JTextField("2026-12-31");
            JTextField wardExpiry = new JTextField("2026-06-01");
            JTextField bedsField = new JTextField("20");

            menuPanel.add(new JLabel("Department:")); menuPanel.add(deptCombo);
            menuPanel.add(new JLabel("Deptexpiry:")); menuPanel.add(deptExpiry);
            menuPanel.add(new JLabel("Ward:")); menuPanel.add(wardCombo);
            menuPanel.add(new JLabel("Wardexpiry:")); menuPanel.add(wardExpiry);
            menuPanel.add(new JLabel("Beds:")); menuPanel.add(bedsField);

            int result = JOptionPane.showConfirmDialog(this, menuPanel, "System Configuration", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(this, "Configured: " + deptCombo.getSelectedItem() + " - " + wardCombo.getSelectedItem());
            } else {
                JOptionPane.showMessageDialog(this, "The user has cancelled the process.", "Cancelled", JOptionPane.WARNING_MESSAGE);
            }
        });

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
        banner.setBackground(isSubscribed ? new Color(209, 250, 229) : new Color(254, 243, 199));
        label.setText(isSubscribed ? "SUBSCRIPTION ACTIVE • " + daysLeft + " DAYS REMAINING" : "PENDING SUBSCRIPTION: VIEW-ONLY MODE");
        label.setForeground(isSubscribed ? new Color(6, 95, 70) : new Color(146, 64, 14));
        banner.add(label);
        return banner;
    }
}