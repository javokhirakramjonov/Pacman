package org.example.ui;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {

    private Dimension buttonSize;

    public MainScreen() {
        super("Pacman");
    }

    public void start() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLUE);

        buttonSize = new Dimension(200, 60);
        Dimension screenSize = new Dimension(800, 450);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 60));
        buttonPanel.setOpaque(false);

        buttonPanel.add(getNewGameButton());
        buttonPanel.add(getHighScoresButton());
        buttonPanel.add(getExitButton());

        JPanel buttonLayout = new JPanel(new GridBagLayout());
        buttonLayout.setBackground(new Color(0, 0, 0, 0));

        buttonLayout.add(buttonPanel);

        add(buttonLayout);

        /*try {
            URL imageUrl = getClass().getClassLoader().getResource("pacmanWallpaper.jpg");
            if(imageUrl != null) {
                BufferedImage image = ImageIO.read(imageUrl);
                Image newImage = image.getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_DEFAULT);
                JLabel imageLabel = new JLabel(new ImageIcon(newImage));
                add(imageLabel);
            }
        } catch (Exception ignored) {
        }*/

        setSize(screenSize);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private JButton getNewGameButton() {
        JButton newGameButton = new JButton("New Game");
        newGameButton.setPreferredSize(buttonSize);

        newGameButton.setBackground(Color.GREEN);
        newGameButton.addActionListener(e -> {

        });
        return newGameButton;
    }

    private JButton getHighScoresButton() {
        JButton highScoresButton = new JButton("High Scores");
        highScoresButton.setPreferredSize(buttonSize);

        highScoresButton.setBackground(Color.GREEN);
        highScoresButton.addActionListener(e -> {

        });
        return highScoresButton;
    }

    private JButton getExitButton() {
        JButton exitButton = new JButton("Exit");
        exitButton.setPreferredSize(buttonSize);

        exitButton.setBackground(Color.GREEN);
        exitButton.addActionListener(e -> System.exit(0));
        return exitButton;
    }

}
