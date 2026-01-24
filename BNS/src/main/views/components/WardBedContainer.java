package main.views.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class WardBedContainer extends JPanel {
    private JScrollPane scrollPane;
    private JPanel cardPanel;

    public WardBedContainer(String wardName) {
        setLayout(new BorderLayout());
        setOpaque(false);

        // Ward Label
        JLabel label = new JLabel("🏥 " + wardName.toUpperCase());
        label.setFont(new Font("SansSerif", Font.BOLD, 18));
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        add(label, BorderLayout.NORTH);

        // Card Panel (Horizontal Flow)
        cardPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        cardPanel.setOpaque(false);

        scrollPane = new JScrollPane(cardPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        add(scrollPane, BorderLayout.CENTER);

        // Initialize Auto-Scroll Timer (like your React useEffect)
        Timer timer = new Timer(50, (ActionEvent e) -> {
            JScrollBar horizontal = scrollPane.getHorizontalScrollBar();
            int max = horizontal.getMaximum() - horizontal.getModel().getExtent();
            if (horizontal.getValue() >= max) {
                horizontal.setValue(0);
            } else {
                horizontal.setValue(horizontal.getValue() + 1);
            }
        });
        timer.start();
    }

    public void addBed(BedCard card) {
        cardPanel.add(card);
    }
}