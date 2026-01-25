package main.views.components;

import javax.swing.*;
import java.awt.*;

public class BottomBar extends JPanel {
    public BottomBar(JButton actionButton) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 40));
        
        actionButton.setBackground(new Color(15, 23, 42));
        actionButton.setForeground(Color.WHITE);
        actionButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        actionButton.setFocusPainted(false);
        actionButton.setPreferredSize(new Dimension(140, 45));
        
        add(actionButton);
    }
}