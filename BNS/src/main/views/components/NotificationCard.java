package main.views.components;

import javax.swing.*;
import java.awt.*;

public class NotificationCard extends JPanel {
    public NotificationCard(String message, String type, String time, boolean isRead) {
        setLayout(new BorderLayout(15, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(226, 232, 240)),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        // Type Indicator (Dot)
        JLabel dot = new JLabel("●");
        dot.setForeground(isRead ? Color.LIGHT_GRAY : new Color(79, 70, 229));
        
        // Content
        JPanel textContent = new JPanel(new GridLayout(2, 1));
        textContent.setOpaque(false);
        
        JLabel msgLabel = new JLabel("<html><body style='width: 250px;'><b>" + type + ":</b> " + message + "</body></html>");
        msgLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        
        JLabel timeLabel = new JLabel(time);
        timeLabel.setFont(new Font("SansSerif", Font.PLAIN, 10));
        timeLabel.setForeground(Color.GRAY);
        
        textContent.add(msgLabel);
        textContent.add(timeLabel);

        add(dot, BorderLayout.WEST);
        add(textContent, BorderLayout.CENTER);
        
        if (!isRead) {
            JButton readBtn = new JButton("Mark Read");
            readBtn.setFont(new Font("SansSerif", Font.BOLD, 10));
            add(readBtn, BorderLayout.EAST);
        }
    }
}