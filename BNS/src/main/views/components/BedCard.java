package main.views.components;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BedCard extends JPanel {
    public BedCard(String bedId, String status, String patientName, String diagnosis, String risk) {
        setPreferredSize(new Dimension(350, 500));
        setBackground(status.equals("occupied") ? new Color(254, 242, 242) : new Color(236, 253, 245));
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(status.equals("occupied") ? new Color(254, 202, 202) : new Color(167, 243, 208), 2, true),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Header: Bed ID and Status Icon
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JLabel title = new JLabel("🛏️ BED " + bedId);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        
        JLabel statusLabel = new JLabel(status.toUpperCase());
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 10));
        statusLabel.setForeground(status.equals("occupied") ? Color.RED : new Color(5, 150, 105));
        
        header.add(title, BorderLayout.WEST);
        header.add(statusLabel, BorderLayout.EAST);

        // Form Fields Area
        JPanel form = new JPanel(new GridLayout(0, 1, 5, 5));
        form.setOpaque(false);
        
        form.add(new JLabel("👤 Patient Name"));
        JTextField nameField = new JTextField(patientName);
        form.add(nameField);

        form.add(new JLabel("📋 Clinical Diagnosis"));
        JTextArea diagArea = new JTextArea(diagnosis, 3, 20);
        diagArea.setLineWrap(true);
        form.add(new JScrollPane(diagArea));

        form.add(new JLabel("⚠️ Risk Level"));
        String[] risks = {"Low", "Medium", "High"};
        JComboBox<String> riskBox = new JComboBox<>(risks);
        riskBox.setSelectedItem(risk);
        form.add(riskBox);

        // Action Buttons
        JPanel actions = new JPanel(new GridLayout(2, 1, 5, 5));
        actions.setOpaque(false);
        
        JButton saveBtn = new JButton("💾 Update Clinical Record");
        saveBtn.setBackground(new Color(15, 23, 42));
        saveBtn.setForeground(Color.BLACK);
        
        JButton statusBtn = new JButton(status.equals("occupied") ? "🚪 Discharge" : "✅ Confirm Admission");
        statusBtn.setBackground(status.equals("occupied") ? Color.WHITE : new Color(79, 70, 229));
        statusBtn.setForeground(status.equals("occupied") ? Color.RED : Color.WHITE);

        actions.add(saveBtn);
        actions.add(statusBtn);

        add(header, BorderLayout.NORTH);
        add(form, BorderLayout.CENTER);
        add(actions, BorderLayout.SOUTH);
    }
}