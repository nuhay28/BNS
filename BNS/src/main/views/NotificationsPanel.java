package main.views;

import javax.swing.*;
import java.awt.*;
import main.Application;
import main.views.components.NotificationCard;

public class NotificationsPanel extends JPanel {
    public NotificationsPanel(Application app) {
        setLayout(new BorderLayout());
        setBackground(new Color(248, 250, 252));

        // --- HEADER ---
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(BorderFactory.createEmptyBorder(30, 40, 10, 40));

        JPanel titleGrp = new JPanel(new GridLayout(2, 1));
        titleGrp.setOpaque(false);
        JLabel sub = new JLabel("REAL-TIME ALERT FEED");
        sub.setFont(new Font("SansSerif", Font.BOLD, 10));
        sub.setForeground(new Color(79, 70, 229));
        JLabel title = new JLabel("Notifications");
        title.setFont(new Font("SansSerif", Font.ITALIC | Font.BOLD, 32));
        titleGrp.add(sub);
        titleGrp.add(title);

        JButton backBtn = new JButton("<- Back to DASHBOARD");
        backBtn.addActionListener(e -> app.showPage("DASHBOARD"));

        header.add(titleGrp, BorderLayout.WEST);
        header.add(backBtn, BorderLayout.EAST);

        // --- FEED LIST ---
        JPanel feedList = new JPanel();
        feedList.setLayout(new BoxLayout(feedList, BoxLayout.Y_AXIS));
        feedList.setOpaque(false);
        feedList.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Mock Notifications
        feedList.add(new NotificationCard("Patient in Ward A-1 discharged.", "ADMISSION", "2 mins ago", false));
        feedList.add(Box.createRigidArea(new Dimension(0, 10)));
        feedList.add(new NotificationCard("Emergency: Critical bed shortage in ICU.", "ALERT", "15 mins ago", false));
        feedList.add(Box.createRigidArea(new Dimension(0, 10)));
        feedList.add(new NotificationCard("New protocol update for Senior Surgeons.", "SYSTEM", "1 hour ago", true));

        JScrollPane scroll = new JScrollPane(feedList);
        scroll.setBorder(null);
        scroll.getViewport().setOpaque(false);

        add(header, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }
}