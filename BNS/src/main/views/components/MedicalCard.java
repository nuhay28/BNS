package main.views.components;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MedicalCard extends JPanel {
      private static final int CARD_WIDTH = 250;
      private static final int CARD_HEIGHT = 180;
      private static final int BOARDER_GAP = 20;
      private static final Color HOVER_COLOR = new Color(245, 245, 250);

    public MedicalCard(String title, String desc, Color accentColor, Runnable onClick) {
        setLayout(new BorderLayout(10, 10));
        setBackground(HOVER_COLOR);
        setPreferredSize(new Dimension(CARD_WIDTH,CARD_HEIGHT));
        
        // Add the left border (the "accent")
        setBorder(BorderFactory.createCompoundBorder(
            new MatteBorder(0, 5, 0, 0, accentColor),
            BorderFactory.createEmptyBorder(BOARDER_GAP, BOARDER_GAP, BOARDER_GAP, BOARDER_GAP)
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
