package main.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.net.URI;
import main.Application;

public class SupportPanel extends JPanel {
    private Color bgSlate = new Color(248, 250, 252);
    private Color indigoPrimary = new Color(79, 70, 229);
    private Color slate900 = new Color(15, 23, 42);
    private Color slate500 = new Color(100, 116, 139); // Defined missing color

    public SupportPanel(Application app) {
        setLayout(new BorderLayout());
        setBackground(bgSlate);

        // --- SCROLLABLE CONTAINER ---
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setOpaque(false);
        container.setBorder(new EmptyBorder(40, 40, 60, 40));

        // 1. BACK NAVIGATION (Fixed: Added to container instead of mainContent)
        JButton backBtn = new JButton("← BACK TO PORTAL");
        backBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        backBtn.setForeground(slate500);
        backBtn.setContentAreaFilled(false);
        backBtn.setBorderPainted(false);
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.addActionListener(e -> app.showPage("DASHBOARD"));
        
        container.add(backBtn);
        container.add(Box.createRigidArea(new Dimension(0, 20)));

        // 2. HEADER SECTION
        JLabel iconLabel = new JLabel("🛟", SwingConstants.CENTER);
        iconLabel.setFont(new Font("SansSerif", Font.PLAIN, 40));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel title = new JLabel("HELPDESK & ASSISTANCE", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.ITALIC | Font.BOLD, 48));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("<html><center>Access technical documentation and support protocols to ensure uninterrupted <b>Clinical Operations</b>.</center></html>");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 16));
        subtitle.setForeground(new Color(100, 116, 139));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        container.add(iconLabel);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(title);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(subtitle);
        container.add(Box.createRigidArea(new Dimension(0, 50)));

        // 3. MAIN SPLIT CONTENT (FAQ & FORM)
        JPanel mainContent = new JPanel(new GridLayout(1, 2, 40, 0));
        mainContent.setOpaque(false);

        // --- LEFT COLUMN: Administrative Protocols (FAQs) ---
        JPanel faqPanel = new JPanel();
        faqPanel.setLayout(new BoxLayout(faqPanel, BoxLayout.Y_AXIS));
        faqPanel.setOpaque(false);

        JLabel faqHeader = new JLabel("⚡ ADMINISTRATIVE PROTOCOLS");
        faqHeader.setFont(new Font("SansSerif", Font.BOLD, 18));
        faqHeader.setForeground(slate900);
        faqPanel.add(faqHeader);
        faqPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        String[][] faqs = {
            {"What is the Bed Notification System?", "BNS is a specialized clinical registry platform designed to standardize bed, ward, and department assignments."},
            {"Who is authorized to access?", "Access is strictly limited to verified medical personnel and institutional administrators."},
            {"Ward Assignment Expiry?", "Upon reaching a set expiry date, clinical data access is restricted to prevent outdated records."}
        };

        for (String[] faq : faqs) {
            faqPanel.add(createFAQItem(faq[0], faq[1]));
            faqPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        }
        mainContent.add(faqPanel);

        // --- RIGHT COLUMN: Technical Inquiry (Form) ---
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BorderLayout());
        formPanel.setOpaque(false);

        JLabel formHeader = new JLabel("🛡️ TECHNICAL INQUIRY");
        formHeader.setFont(new Font("SansSerif", Font.BOLD, 18));
        formPanel.add(formHeader, BorderLayout.NORTH);

        JPanel innerForm = new RoundedPanel(40, Color.WHITE);
        innerForm.setLayout(new BoxLayout(innerForm, BoxLayout.Y_AXIS));
        innerForm.setBorder(new EmptyBorder(30, 30, 30, 30));

        innerForm.add(createFieldLabel("STAFF NAME"));
        JTextField nameField = createStyledTextField("Enter full name");
        innerForm.add(nameField);
        
        innerForm.add(Box.createRigidArea(new Dimension(0, 15)));

        innerForm.add(createFieldLabel("REGISTRY EMAIL"));
        JTextField emailField = createStyledTextField("Institutional email");
        innerForm.add(emailField);

        innerForm.add(Box.createRigidArea(new Dimension(0, 15)));

        innerForm.add(createFieldLabel("INQUIRY DETAILS"));
        JTextArea messageArea = new JTextArea(4, 20);
        messageArea.setBackground(new Color(241, 245, 249));
        messageArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        messageArea.setLineWrap(true);
        innerForm.add(new JScrollPane(messageArea));

        innerForm.add(Box.createRigidArea(new Dimension(0, 25)));

        JButton submitBtn = new JButton("📋 SUBMIT INQUIRY");
        submitBtn.setBackground(indigoPrimary);
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFocusPainted(false);
        submitBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        submitBtn.setPreferredSize(new Dimension(0, 50));
        submitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Inquiry Dispatched to Helpdesk"));
        innerForm.add(submitBtn);

        formPanel.add(innerForm, BorderLayout.CENTER);
        
        // Quick Link Card (Telegram)
        JPanel telegramCard = new RoundedPanel(30, slate900);
        telegramCard.setLayout(new BorderLayout());
        telegramCard.setPreferredSize(new Dimension(0, 80));
        telegramCard.setMaximumSize(new Dimension(2000, 80));
        telegramCard.setBorder(new EmptyBorder(15, 25, 15, 25));
        
        JLabel tgLabel = new JLabel("<html><b style='color:white'>EMERGENCY DESK</b><br><small style='color:#94a3b8'>Real-time Support</small></html>");
        JButton tgBtn = new JButton("💬");
        tgBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tgBtn.addActionListener(e -> openWebpage("https://t.me/NYDev_Chat"));
        
        telegramCard.add(tgLabel, BorderLayout.WEST);
        telegramCard.add(tgBtn, BorderLayout.EAST);
        
        JPanel rightCol = new JPanel(new BorderLayout(0, 20));
        rightCol.setOpaque(false);
        rightCol.add(formPanel, BorderLayout.CENTER);
        rightCol.add(telegramCard, BorderLayout.SOUTH);

        mainContent.add(rightCol);

        container.add(mainContent);

        JScrollPane mainScroll = new JScrollPane(container);
        mainScroll.setBorder(null);
        mainScroll.getVerticalScrollBar().setUnitIncrement(16);
        add(mainScroll, BorderLayout.CENTER);
    }

    private JPanel createFAQItem(String q, String a) {
        JPanel p = new RoundedPanel(30, Color.WHITE);
        p.setLayout(new BorderLayout());
        p.setBorder(new EmptyBorder(20, 25, 20, 25));
        
        JLabel qLabel = new JLabel("<html><b>" + q.toUpperCase() + "</b></html>");
        qLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        JTextArea aLabel = new JTextArea(a);
        aLabel.setEditable(false);
        aLabel.setLineWrap(true);
        aLabel.setWrapStyleWord(true);
        aLabel.setFont(new Font("SansSerif", Font.ITALIC, 13));
        aLabel.setForeground(new Color(100, 116, 139));
        aLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        p.add(qLabel, BorderLayout.NORTH);
        p.add(aLabel, BorderLayout.CENTER);
        return p;
    }

    private JLabel createFieldLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("SansSerif", Font.BOLD, 10));
        l.setForeground(new Color(148, 163, 184));
        return l;
    }

    private JTextField createStyledTextField(String placeholder) {
        JTextField f = new JTextField();
        f.setBackground(new Color(241, 245, 249));
        f.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 15));
        return f;
    }

    private void openWebpage(String url) {
        try { Desktop.getDesktop().browse(new URI(url)); } catch (Exception e) {}
    }

    class RoundedPanel extends JPanel {
        private int cornerRadius;
        private Color bgColor;

        public RoundedPanel(int radius, Color color) {
            this.cornerRadius = radius;
            this.bgColor = color;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setColor(bgColor);
            graphics.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));
        }
    }
}