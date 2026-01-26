package main.views.components;

import javax.swing.*;
import java.awt.*;
import main.Application;

public class TopNav extends JPanel {
    public TopNav(Application app, String pageTitle, String userName, String userRole) {
        setLayout(new BorderLayout());
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));

        // Left Side: Title & Branding
        JPanel leftGrp = new JPanel(new GridLayout(2, 1));
        leftGrp.setOpaque(false);
        JLabel tag = new JLabel("HOSPITAL SYSTEM V1.0");
        tag.setFont(new Font("SansSerif", Font.BOLD, 10));
        tag.setForeground(new Color(37, 99, 235));
        JLabel title = new JLabel(pageTitle);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        leftGrp.add(tag);
        leftGrp.add(title);

        // Right Side: Profile & Logout
        JPanel rightGrp = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        rightGrp.setOpaque(false);

        JPanel profile = new JPanel(new GridLayout(2, 1));
        profile.setBackground(Color.WHITE);
        profile.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(226, 232, 240)),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        JLabel pName = new JLabel("Dr. " + userName);
        pName.setFont(new Font("SansSerif", Font.BOLD, 12));
        JLabel pRole = new JLabel(userRole.toUpperCase());
        pRole.setFont(new Font("SansSerif", Font.PLAIN, 10));
        pRole.setForeground(new Color(13, 148, 136));
        profile.add(pName); profile.add(pRole);

        JButton logout = new JButton("LOGOUT");
        logout.setBackground(new Color(239, 68, 68));
        logout.setForeground(Color.BLACK);
        logout.setFocusPainted(false);
        logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logout.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "End session?", "Logout", 0) == 0) app.showPage("LOGIN");
        });

        rightGrp.add(profile);
        rightGrp.add(logout);

        add(leftGrp, BorderLayout.WEST);
        add(rightGrp, BorderLayout.EAST);
    }
}
