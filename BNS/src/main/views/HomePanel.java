package main.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import main.Application;

public class HomePanel extends JPanel {
    // Colors from React Slate-950 theme
    private Color indigo600 = new Color(79, 70, 229);
    private Color slate300 = new Color(203, 213, 225);
    private Color slate950Overlay = new Color(15, 23, 42, 200); // 75% Alpha

    public HomePanel(Application app) {
        // Use a custom panel to handle the background image/overlay
        setLayout(new BorderLayout());

        BackgroundContainer mainContainer = new BackgroundContainer();
        mainContainer.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // --- 1. ICON (Bed Icon replacement) ---
        JLabel iconLabel = new JLabel("🛏️"); // You can replace with an ImageIcon
        iconLabel.setFont(new Font("SansSerif", Font.PLAIN, 80));
        iconLabel.setForeground(indigo600);
        
        // --- 2. TITLE ---
        JLabel title = new JLabel("<html><center>Hospital Bed<br><font color='#818cf8'>Notification System</font></center></html>");
        title.setFont(new Font("SansSerif", Font.BOLD, 64));
        title.setForeground(Color.WHITE);

        // --- 3. SUBTITLE ---
        JLabel sub = new JLabel("<html><center><p style='width: 500px'>"
                + "A modern, lightweight system for real-time bed assignment and patient admission notifications."
                + "</p></center></html>");
        sub.setForeground(slate300);
        // Change Font.LIGHT to Font.PLAIN
        sub.setFont(new Font("SansSerif", Font.PLAIN, 20));

        // --- 4. BUTTON PANEL ---
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnPanel.setOpaque(false);

        JButton loginBtn = createStyledButton("LOG IN", indigo600, Color.WHITE, true);
        JButton registerBtn = createStyledButton("SIGN UP", new Color(0,0,0,0), Color.WHITE, false);
        
        // Navigation Logic
        loginBtn.addActionListener(e -> app.showPage("LOGIN"));
        registerBtn.addActionListener(e -> app.showPage("REGISTER"));

        btnPanel.add(loginBtn);
        btnPanel.add(registerBtn);

        // Layout Assembly
        gbc.gridx = 0; gbc.insets = new Insets(10, 10, 10, 10);
        
        gbc.gridy = 0; mainContainer.add(iconLabel, gbc);
        gbc.gridy = 1; mainContainer.add(title, gbc);
        gbc.gridy = 2; mainContainer.add(sub, gbc);
        gbc.gridy = 3; gbc.insets = new Insets(40, 10, 10, 10);
        mainContainer.add(btnPanel, gbc);

        add(mainContainer, BorderLayout.CENTER);
    }

    // --- BUTTON STYLING FACTORY ---
    private JButton createStyledButton(String text, Color bg, Color fg, boolean isSolid) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (isSolid) {
                    g2.setColor(bg);
                    g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
                } else {
                    g2.setColor(new Color(129, 140, 248)); // indigo-400
                    g2.setStroke(new BasicStroke(2f));
                    g2.draw(new RoundRectangle2D.Double(1, 1, getWidth()-2, getHeight()-2, 15, 15));
                }
                g2.dispose();
                super.paintComponent(g);
            }
        };
        
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setForeground(fg);
        btn.setPreferredSize(new Dimension(180, 55));
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        return btn;
    }

    // --- BACKGROUND PANEL WITH OVERLAY ---
    class BackgroundContainer extends JPanel {
        private Image backgroundImage;

        public BackgroundContainer() {
            // Path to your hospitalHallway.jpg
            try {
                backgroundImage = new ImageIcon(getClass().getResource("../assets/hospitalHallway.jpg")).getImage();
            } catch (Exception e) {
                System.out.println("Background image not found, falling back to solid color.");
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            if (backgroundImage != null) {
                // Draw image to fill the screen (Center-crop style)
                double imgWidth = backgroundImage.getWidth(null);
                double imgHeight = backgroundImage.getHeight(null);
                double screenWidth = getWidth();
                double screenHeight = getHeight();
                double scale = Math.max(screenWidth / imgWidth, screenHeight / imgHeight);
                int scaledWidth = (int) (imgWidth * scale);
                int scaledHeight = (int) (imgHeight * scale);
                g2d.drawImage(backgroundImage, (int)(screenWidth - scaledWidth)/2, (int)(screenHeight - scaledHeight)/2, scaledWidth, scaledHeight, null);
            }

            // Draw the Slate-900/75 Backdrop Blur effect
            g2d.setColor(slate950Overlay);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}