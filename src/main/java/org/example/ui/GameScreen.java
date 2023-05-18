package org.example.ui;

import org.example.controller.GameScreenController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class GameScreen extends JFrame {

    private final GameScreenController controller;
    private final int heartSize = 50;
    private final GridBagConstraints constraints = new GridBagConstraints();
    private final Color textBackgroundColor = new Color(0x3F3F3F);
    private final Color fontColor = Color.WHITE;
    private final JPanel heartContainer = new JPanel();
    private final JPanel scoreContainer = new JPanel();
    private final JPanel gameDetailsPanel = new JPanel();
    private Dimension boardSize;
    private JTable board;
    private Image heartImage;

    public GameScreen(
            int columns,
            int rows
    ) {
        super("Pacman game");
        controller = new GameScreenController(this, columns, rows);
    }

    public void setBoard(JTable jTable) {
        board = jTable;
    }

    @Override
    public void setSize(Dimension d) {
        boardSize = new Dimension(d.width, d.height);
        Dimension screenSize = new Dimension(d.width + 4 * heartSize, d.height + 100);
        super.setSize(screenSize);
    }

    public void start() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        URL imageUrl = getClass().getClassLoader().getResource("heart.png");
        try {
            if (imageUrl != null) {
                heartImage = ImageIO.read(imageUrl);
                heartImage = heartImage.getScaledInstance(heartSize, heartSize, Image.SCALE_DEFAULT);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        board.setMinimumSize(boardSize);
        board.setMaximumSize(boardSize);

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(board, constraints);

        drawDetailsPanel();

        controller.start();

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void drawDetailsPanel() {
        constraints.gridx = 1;
        constraints.gridy = 0;

        gameDetailsPanel.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel lifeText = new JLabel("Life count");
        JLabel scoreText = new JLabel("Score");

        makeLabel(lifeText);
        makeLabel(scoreText);

        gameDetailsPanel.add(lifeText);
        heartContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        heartContainer.setBackground(Color.YELLOW);
        heartContainer.setMinimumSize(new Dimension(4 * heartSize, heartSize));
        gameDetailsPanel.add(heartContainer);
        gameDetailsPanel.add(scoreText);
        scoreContainer.setBackground(textBackgroundColor);
        scoreContainer.setLayout(new BorderLayout());
        scoreContainer.setMinimumSize(new Dimension(4 * heartSize, heartSize));
        gameDetailsPanel.add(scoreContainer);

        add(gameDetailsPanel, constraints);
    }

    private void makeLabel(JLabel text) {
        text.setFont(new Font("Halvetica", Font.BOLD, 28));
        text.setBackground(textBackgroundColor);
        text.setForeground(fontColor);
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setOpaque(true);
    }

    public void drawHearts(int count) {
        heartContainer.removeAll();

        for (int i = 0; i < count; ++i) {
            JLabel heart = new JLabel(new ImageIcon(heartImage));
            heartContainer.add(heart);
        }

        heartContainer.revalidate();
        heartContainer.repaint();
    }

    public void drawScore(int score) {
        scoreContainer.removeAll();

        JLabel scoreLabel = new JLabel(String.valueOf(score));

        makeLabel(scoreLabel);

        scoreContainer.add(scoreLabel, BorderLayout.SOUTH);

        scoreContainer.revalidate();
        scoreContainer.repaint();
    }

    public String showDialogAndGetName() {
        return JOptionPane.showInputDialog("Enter your name");
    }
}
