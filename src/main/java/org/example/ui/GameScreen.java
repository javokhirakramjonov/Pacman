package org.example.ui;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JFrame {

    public GameScreen() {
        super("Pacman game");
    }

    public void start() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = new Dimension(500, 500);

        setSize(screenSize);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
