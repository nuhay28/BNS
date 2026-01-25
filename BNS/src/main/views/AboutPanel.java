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

        mainContent.add(Box.createRigidArea(new Dimension(0, 30)));

        // 4. MANDATE CARD (Dark)
        mainContent.add(createVerticalCard(
            "📋 OUR MANDATE",
            "Our objective is to empower medical institutions with data-driven tools that minimize clinical friction. "
            + "We aim to enhance institutional accountability and improve patient safety.",
            slate900, Color.WHITE, false
        ));

        mainContent.add(Box.createRigidArea(new Dimension(0, 60)));

        // 5. REGISTRY STANDARDS (Vertical List)
        JLabel standardHeader = new JLabel("REGISTRY STANDARDS");
        standardHeader.setFont(new Font("SansSerif", Font.BOLD, 20));
        standardHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContent.add(standardHeader);
        mainContent.add(Box.createRigidArea(new Dimension(0, 25)));

        mainContent.add(createStandardRow("🩺", "Clinical Precision", "Utilizing MERN stack logic for high-availability management."));
        mainContent.add(Box.createRigidArea(new Dimension(0, 15)));
        mainContent.add(createStandardRow("📱", "Responsive Design", "Optimized for high-stress emergency departments and ward rounds."));
        mainContent.add(Box.createRigidArea(new Dimension(0, 15)));
        mainContent.add(createStandardRow("👥", "Staff Coordination", "Fostering collaboration between medical staff and hospital admins."));

        mainContent.add(Box.createRigidArea(new Dimension(0, 80)));

        // 6. FINAL CONTACT CALL-TO-ACTION
        JButton contactBtn = new JButton("SECURE INQUIRY: negussupeniel@gmail.com");
        contactBtn.setPreferredSize(new Dimension(500, 60));
        contactBtn.setMaximumSize(new Dimension(500, 60));
        contactBtn.setBackground(indigo600);
        contactBtn.setForeground(Color.WHITE);
        contactBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        contactBtn.setFocusPainted(false);
        contactBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContent.add(contactBtn);

        // --- WRAP IN SCROLLPANE ---
        JScrollPane scrollPane = new JScrollPane(mainContent);
        scrollPane.setBorder(null);
        scrollPane.setBackground(bgSlate);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        add(scrollPane, BorderLayout.CENTER);
    }

    // --- CARD FACTORY ---
    private JPanel createVerticalCard(String title, String body, Color bg, Color fg, boolean hasBorder) {
        JPanel card = new RoundedPanel(40, bg);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(40, 40, 40, 40));
        card.setMaximumSize(new Dimension(700, 250)); // Constrain width for vertical look
        card.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel t = new JLabel(title);
        t.setFont(new Font("SansSerif", Font.BOLD, 22));
        t.setForeground(fg);
        t.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel b = new JLabel("<html><body style='width: 500px'>" + body + "</body></html>");
        b.setFont(new Font("SansSerif", Font.PLAIN, 16));
        b.setForeground(fg == Color.WHITE ? new Color(203, 213, 225) : slate500);
        b.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(t);
        card.add(Box.createRigidArea(new Dimension(0, 15)));
        card.add(b);
        return card;
    }

    private JPanel createStandardRow(String emoji, String title, String desc) {
        JPanel row = new RoundedPanel(25, Color.WHITE);
        row.setLayout(new BorderLayout(20, 0));
        row.setBorder(new EmptyBorder(20, 25, 20, 25));
        row.setMaximumSize(new Dimension(700, 100));
        row.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel icon = new JLabel(emoji);
        icon.setFont(new Font("SansSerif", Font.PLAIN, 30));
        
        JPanel textGroup = new JPanel(new GridLayout(2, 1));
        textGroup.setOpaque(false);
        JLabel t = new JLabel(title.toUpperCase());
        t.setFont(new Font("SansSerif", Font.BOLD, 14));
        JLabel d = new JLabel(desc);
        d.setFont(new Font("SansSerif", Font.PLAIN, 13));
        d.setForeground(slate500);
        
        textGroup.add(t);
        textGroup.add(d);

        row.add(icon, BorderLayout.WEST);
        row.add(textGroup, BorderLayout.CENTER);
        return row;
    }

    // --- CUSTOM PAINTING FOR ROUNDED CORNERS ---
    class RoundedPanel extends JPanel {
        private int radius;
        private Color color;
        public RoundedPanel(int radius, Color color) {
            this.radius = radius;
            this.color = color;
            setOpaque(false);
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), radius, radius));
        }
    }
}