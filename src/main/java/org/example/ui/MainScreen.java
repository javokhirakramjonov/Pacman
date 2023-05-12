package org.example.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.function.Supplier;

public class MainScreen extends JFrame {

    Color buttonColor = new Color(0x3F3F3F);
    Color fontColor = new Color(0, 102, 255);
    Dimension buttonSize = new Dimension(300, 80);

    public MainScreen() {
        super("Pacman");
    }

    public void start() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Dimension screenSize = new Dimension(800, 450);

        JLayeredPane mainPane = new JLayeredPane();

        try {
            URL imageUrl = getClass().getClassLoader().getResource("pacmanWallpaper.jpg");
            if (imageUrl != null) {
                BufferedImage image = ImageIO.read(imageUrl);
                Image newImage = image.getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_DEFAULT);
                JLabel imageLabel = new JLabel(new ImageIcon(newImage));
                imageLabel.setBounds(new Rectangle(screenSize));
                mainPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER);
            }
        } catch (Exception ignored) {
        }

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 0, 40));
        buttonPanel.setOpaque(false);

        buttonPanel.add(createButton("New Game", () -> {
            setVisible(false);
            new GameScreen(20, 20).start();
            return null;
        }));
        buttonPanel.add(createButton("High Scores", () -> {
            //TODO
            return null;
        }));
        buttonPanel.add(createButton("Exit", () -> {
            System.exit(0);
            return null;
        }));

        JPanel buttonContainer = new JPanel(new GridBagLayout());
        buttonContainer.setOpaque(false);

        buttonContainer.add(buttonPanel);

        buttonContainer.setBounds(new Rectangle(screenSize));
        mainPane.add(buttonContainer, JLayeredPane.PALETTE_LAYER);

        add(mainPane);

        setSize(screenSize);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private JButton createButton(String name, Supplier<Void> action) {

        JButton button = new JButton(name);
        button.setFont(new Font("Halvetica", Font.BOLD, 28));
        button.setForeground(fontColor);
        button.setBackground(buttonColor);
        button.setPreferredSize(buttonSize);
        button.setBorder(BorderFactory.createLineBorder(fontColor, 4, true));
        button.setFocusPainted(false);
        button.addActionListener(event -> action.get());
        return button;
    }

}
