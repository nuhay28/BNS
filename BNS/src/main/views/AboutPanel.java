package main.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import main.Application;

public class AboutPanel extends JPanel {
    public AboutPanel(Application app) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Header Section
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(new EmptyBorder(40, 40, 20, 40));
