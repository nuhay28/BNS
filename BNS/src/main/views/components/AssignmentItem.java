package main.views.components;

import javax.swing.*;
import java.awt.*;

public class AssignmentItem extends JPanel {
    public AssignmentItem(String bedId, String practitioner, String role, Runnable onRemove) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240), 1));

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(248, 250, 252));
        header.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        header.add(new JLabel("🛏 Bed " + bedId), BorderLayout.WEST);
        header.add(new JLabel("✔"), BorderLayout.EAST);

        // Body
        JPanel body = new JPanel(new GridLayout(2, 1, 5, 5));
        body.setOpaque(false);
        body.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel pLabel = new JLabel("<html><font color='gray' size='2'>PRACTITIONER</font><br><b>" + practitioner + "</b></html>");
        JLabel rLabel = new JLabel("<html><font color='gray' size='2'>DESIGNATION</font><br><font color='blue'>" + role + "</font></html>");
        
        body.add(pLabel);
        body.add(rLabel);

        // Remove Button
        JButton removeBtn = new JButton("Remove Bed");
        removeBtn.setForeground(Color.RED);
        removeBtn.addActionListener(e -> onRemove.run());

        add(header, BorderLayout.NORTH);
        add(body, BorderLayout.CENTER);
        add(removeBtn, BorderLayout.SOUTH);
    }
}