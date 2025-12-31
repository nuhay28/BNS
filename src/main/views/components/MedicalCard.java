package main.views.components;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MedicalCard extends JPanel {
    public MedicalCard(String title, String desc, Color accentColor, Runnable onClick) {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(250, 180));
        
        // Add the left border (the "accent")
        setBorder(BorderFactory.createCompoundBorder(
            new MatteBorder(0, 5, 0, 0, accentColor),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Icon Placeholder (In a real app, use an ImageIcon)
        JLabel iconLabel = new JLabel("🔘"); // Lucide icon placeholder
        iconLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
        iconLabel.setForeground(accentColor);
        
        // Text Panel
        JPanel textPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        textPanel.setOpaque(false);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        
        JLabel descLabel = new JLabel("<html>" + desc + "</html>");
        descLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        descLabel.setForeground(Color.GRAY);
        
        textPanel.add(titleLabel);
        textPanel.add(descLabel);

        add(iconLabel, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);

        // Hover Effect & Click Logic
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { setBackground(new Color(245, 245, 250)); }
            public void mouseExited(MouseEvent e) { setBackground(Color.WHITE); }
            public void mousePressed(MouseEvent e) { onClick.run(); }
        });
    }
}