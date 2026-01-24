package main.views;

import javax.swing.*;
import java.awt.*;
import main.Application;
import main.views.components.BedCard;
import main.views.components.WardBedContainer;

public class BedsPanel extends JPanel {
    public BedsPanel(Application app) {
        setLayout(new BorderLayout());
        setBackground(new Color(248, 250, 252));

        // --- TOP BAR ---
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setOpaque(false);
        topBar.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JButton backBtn = new JButton("⬅️ Back");
        backBtn.addActionListener(e -> app.showPage("DASHBOARD"));
        
        JLabel title = new JLabel("🏢 Cardiology Department");
        title.setFont(new Font("SansSerif", Font.ITALIC | Font.BOLD, 28));
        
        topBar.add(backBtn, BorderLayout.WEST);
        topBar.add(title, BorderLayout.CENTER);

        // --- MAIN SCROLLABLE CONTENT ---
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setOpaque(false);
        mainContent.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));

        // Ward 1
        WardBedContainer wardA = new WardBedContainer("General Ward A");
        wardA.addBed(new BedCard("101", "occupied", "Alice Smith", "Post-Op Recovery", "Low"));
        wardA.addBed(new BedCard("102", "available", "", "", "Low"));
        wardA.addBed(new BedCard("103", "occupied", "Bob Johnson", "Chest Pain", "High"));
        
        // Ward 2
        WardBedContainer wardB = new WardBedContainer("ICU Tier 1");
        wardB.addBed(new BedCard("201", "occupied", "Charlie Brown", "Acute MI", "High"));
        wardB.addBed(new BedCard("202", "available", "", "", "Low"));

        mainContent.add(wardA);
        mainContent.add(Box.createRigidArea(new Dimension(0, 40)));
        mainContent.add(wardB);

        JScrollPane mainScroll = new JScrollPane(mainContent);
        mainScroll.setBorder(null);
        mainScroll.getViewport().setOpaque(false);

        // --- FLOATING DEPARTMENT BUTTON ---
        JButton deptBtn = new JButton("📂 DEPARTMENTS");
        deptBtn.setBackground(new Color(15, 23, 42));
        deptBtn.setForeground(Color.WHITE);
        deptBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        footer.setOpaque(false);
        footer.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        footer.add(deptBtn);

        add(topBar, BorderLayout.NORTH);
        add(mainScroll, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
    }
}