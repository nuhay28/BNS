package main;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {

    public Application() {
        setTitle("BNS Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Application();
    }
}
