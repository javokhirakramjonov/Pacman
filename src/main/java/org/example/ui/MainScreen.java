package org.example.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 0, 40));
        buttonPanel.setOpaque(false);

        buttonPanel.add(createButton("New Game", () -> {
            JFrame dialog = new JFrame();
            TextField textField1 = new TextField("20");
            TextField textField2 = new TextField("20");
            JButton button = createButton("START", () -> {
                try {
                    int num1 = Integer.parseInt(textField1.getText());
                    int num2 = Integer.parseInt(textField2.getText());
                    if (Math.min(num1, num2) < 10) {
                        textField1.setText("Invalid data");
                        textField2.setText("Invalid data");
                    } else {
                        dialog.dispose();
                        dispose();
                        new GameScreen(num1, num2).start();
                    }
                } catch (Exception e) {
                    textField1.setText("Invalid data");
                    textField2.setText("Invalid data");
                }
                return null;
            });

            dialog.setLayout(new GridLayout(3, 2, 0, 10));
            dialog.add(new JLabel("Number of columns"));
            dialog.add(textField1);
            dialog.add(new JLabel("Number of rows"));
            dialog.add(textField2);
            dialog.add(button);
            dialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            dialog.setSize(new Dimension(500, 200));
            dialog.setLocationRelativeTo(null);
            dialog.setResizable(false);
            dialog.setVisible(true);
            return null;
        }));
        buttonPanel.add(createButton("High Scores", () -> {
            dispose();
            new ScoresScreen().start();
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
