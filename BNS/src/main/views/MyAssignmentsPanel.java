package main.views;

import javax.swing.*;
import java.awt.*;
import main.Application;
import main.views.components.AssignmentItem;

public class MyAssignmentsPanel extends JPanel {
    private JPanel bedsGrid;
    private boolean isActive = true; // Mock subscription status

    public MyAssignmentsPanel(Application app) {
        if (!isActive) {
            renderAccessDenied(app);
            return;
        }

        setLayout(new BorderLayout());
        setBackground(new Color(248, 250, 252));

        // --- TOP HEADER ---
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel title = new JLabel("My Assignments");
        title.setFont(new Font("SansSerif", Font.ITALIC | Font.BOLD, 36));
        
        JButton backBtn = new JButton("<- Back to DASHBOARD");
        backBtn.addActionListener(e -> app.showPage("DASHBOARD"));

        header.add(title, BorderLayout.WEST);
        header.add(backBtn, BorderLayout.EAST);

        // --- ASSIGNMENT CONTROLS (Add Bed) ---
        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controls.setBackground(Color.WHITE);
        controls.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.LIGHT_GRAY));
        
        JComboBox<String> bedSelector = new JComboBox<>(new String[]{"Unit 105", "Unit 108", "Unit 202"});
        JButton addBtn = new JButton("Add to Protocol");
        addBtn.setBackground(new Color(79, 70, 229));
        addBtn.setForeground(Color.WHITE);
        
        controls.add(new JLabel("Expand Assignment: "));
        controls.add(bedSelector);
        controls.add(addBtn);

        // --- BEDS GRID ---
        bedsGrid = new JPanel(new GridLayout(0, 3, 20, 20));
        bedsGrid.setOpaque(false);
        bedsGrid.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Initial Data Mock
        refreshGrid();

        JScrollPane scroll = new JScrollPane(bedsGrid);
        scroll.setBorder(null);

        JPanel mainContainer = new JPanel(new BorderLayout());
        mainContainer.setOpaque(false);
        mainContainer.add(controls, BorderLayout.NORTH);
        mainContainer.add(scroll, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);
        add(mainContainer, BorderLayout.CENTER);
    }

    private void refreshGrid() {
        bedsGrid.removeAll();
        bedsGrid.add(new AssignmentItem("101", "Dr. Smith", "Medical Intern", () -> {
            JOptionPane.showMessageDialog(this, "Removing Bed...");
        }));
        bedsGrid.add(new AssignmentItem("102", "Dr. Smith", "Medical Intern", () -> {}));
        bedsGrid.revalidate();
        bedsGrid.repaint();
    }

    private void renderAccessDenied(Application app) {
        setLayout(new GridBagLayout());
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(400, 300));
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        
        JLabel lockIcon = new JLabel("🔒 Access Denied");
        lockIcon.setFont(new Font("SansSerif", Font.BOLD, 24));
        lockIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton upgradeBtn = new JButton("Upgrade Plan");
        upgradeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton backBtn = new JButton("Go Back");
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        backBtn.addActionListener(e -> app.showPage("DASHBOARD"));

        card.add(Box.createVerticalGlue());
        card.add(lockIcon);
        card.add(Box.createRigidArea(new Dimension(0, 20)));
        card.add(upgradeBtn);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(backBtn);
        card.add(Box.createVerticalGlue());

        add(card);
    }
}