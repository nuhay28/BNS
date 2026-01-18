package main.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import main.Application;

public class AboutPanel extends JPanel {
    // Colors from the Web Theme
    private Color bgSlate = new Color(248, 250, 252);
    private Color indigo600 = new Color(79, 70, 229);
    private Color slate900 = new Color(15, 23, 42);
    private Color slate500 = new Color(100, 116, 139);

    public AboutPanel(Application app) {
        setLayout(new BorderLayout());
        setBackground(bgSlate);

        // --- SCROLLABLE WRAPPER ---
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setOpaque(false);
        mainContent.setBorder(new EmptyBorder(50, 100, 50, 100)); // Large side margins for vertical elegance

        // 1. BACK NAVIGATION
        JButton backBtn = new JButton("← BACK TO PORTAL");
        backBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        backBtn.setForeground(slate500);
        backBtn.setContentAreaFilled(false);
        backBtn.setBorderPainted(false);
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        backBtn.addActionListener(e -> app.showPage("DASHBOARD"));
        mainContent.add(backBtn);
        mainContent.add(Box.createRigidArea(new Dimension(0, 40)));

        // 2. HERO SECTION (Stacked)
        JLabel iconLabel = new JLabel("🛡️");
        iconLabel.setFont(new Font("SansSerif", Font.PLAIN, 50));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContent.add(iconLabel);

        JLabel title = new JLabel("<html><center>ABOUT <font color='#4F46E5'>BNS</font></center></html>");
        title.setFont(new Font("SansSerif", Font.ITALIC | Font.BOLD, 64));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContent.add(title);
 JLabel subtitle = new JLabel("<html><center><p style='width: 500px'>"
                + "The Clinical Bed Registry (BNS) is a specialized administrative ecosystem engineered to "
                + "standardize ward management and synchronize patient throughput.</p></center></html>");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 18));
        subtitle.setForeground(slate500);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContent.add(Box.createRigidArea(new Dimension(0, 15)));
        mainContent.add(subtitle);
        
        mainContent.add(Box.createRigidArea(new Dimension(0, 60)));

        // 3. INFRASTRUCTURE CARD (White)
        mainContent.add(createVerticalCard(
            "🏢 THE INFRASTRUCTURE",
            "We have refined the logic of ward allocation and patient auditing into a singular, high-integrity interface. "
            + "It facilitates real-time occupancy monitoring and absolute transparency for medical directors.",
            Color.WHITE, slate900, true
        ));
