package main.views;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import main.Application;
import main.views.components.BedCard;
import main.views.components.WardBedContainer;

public class BedsPanel extends JPanel {
    private JLabel title;
    private final Map<String, String[]> deptData = new HashMap<>() {{
        put("Internal Medicine", new String[]{"Ward C", "Ward D", "Stroke", "Kalazar"});
        put("GynObs", new String[]{"Labor", "M1 up", "M1 down", "Michu", "Fistula"});
        put("Pediatrics", new String[]{"ETAT", "NICU", "Hemato Oncology"});
        put("Surgery", new String[]{"Recovery", "Surgical ward", "Ortho ward"});
        put("Emergency", new String[]{"Red", "Orange", "Yellow"});
    }};

    public BedsPanel(Application app) {
        setLayout(new BorderLayout());
        setBackground(new Color(248, 250, 252));

        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setOpaque(false);
        topBar.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JButton backBtn = new JButton("⬅️ Back");
        backBtn.addActionListener(e -> app.showPage("DASHBOARD"));
        
        title = new JLabel("🏢 Internal Medicine");
        title.setFont(new Font("SansSerif", Font.ITALIC | Font.BOLD, 28));
        
        topBar.add(backBtn, BorderLayout.WEST);
        topBar.add(title, BorderLayout.CENTER);

        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setOpaque(false);
        mainContent.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));

        // Default Wards for Internal Medicine
        WardBedContainer ward1 = new WardBedContainer("Ward C");
        ward1.addBed(new BedCard("101", "occupied", "Alice Smith", "Recovery", "Low"));
        mainContent.add(ward1);

        JScrollPane mainScroll = new JScrollPane(mainContent);
        mainScroll.setBorder(null);
        mainScroll.getViewport().setOpaque(false);

        JButton deptBtn = new JButton("📂 DEPARTMENTS");
        deptBtn.setBackground(new Color(15, 23, 42));
        deptBtn.setForeground(Color.WHITE);
        deptBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        
        deptBtn.addActionListener(e -> {
            JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
            String[] depts = deptData.keySet().toArray(new String[0]);
            JComboBox<String> deptCombo = new JComboBox<>(depts);
            JComboBox<String> wardCombo = new JComboBox<>(deptData.get(depts[0]));

            deptCombo.addActionListener(event -> {
                wardCombo.setModel(new DefaultComboBoxModel<>(deptData.get((String)deptCombo.getSelectedItem())));
            });

            panel.add(new JLabel("Select Department:")); panel.add(deptCombo);
            panel.add(new JLabel("Select Ward:")); panel.add(wardCombo);
            
            int result = JOptionPane.showConfirmDialog(this, panel, "Change Ward", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                String selectedDept = (String) deptCombo.getSelectedItem();
                String selectedWard = (String) wardCombo.getSelectedItem();
                title.setText("🏢 " + selectedDept + " (" + selectedWard + ")");
                JOptionPane.showMessageDialog(this, "Switched to " + selectedWard);
            } else {
                JOptionPane.showMessageDialog(this, "The user has cancelled the process.", "Cancelled", JOptionPane.WARNING_MESSAGE);
            }
        });

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        footer.setOpaque(false);
        footer.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        footer.add(deptBtn);

        add(topBar, BorderLayout.NORTH);
        add(mainScroll, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
    }
}