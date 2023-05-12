package org.example.ui;

import org.example.controller.GameScreenController;
import org.example.domain.model.CellModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameScreen extends JFrame {

    private final GameScreenController controller;
    private final JLayeredPane layeredPane;
    private Dimension screenSize;

    public GameScreen(
            int columns,
            int rows
    ) {
        super("Pacman game");
        controller = new GameScreenController(this, columns, rows);
        layeredPane = new JLayeredPane();
    }

    @Override
    public void setSize(Dimension d) {
        super.setSize(d);
        screenSize = d;
    }

    public void start() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        controller.start();

        layeredPane.setBounds(new Rectangle(screenSize));
        add(layeredPane);

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void drawBoard(int cellSize, List<CellModel>[][] board) {
        int foods = 0;
        int blocks = 0;
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                Rectangle bound = new Rectangle(
                        new Point(j * cellSize, i * cellSize),
                        new Dimension(cellSize, cellSize)
                );
                for (CellModel cell : board[i][j]) {
                    switch (cell) {
                        case FOOD -> {
                            foods++;
                            JPanel food = new JPanel() {
                                @Override
                                public void paintComponent(Graphics g) {
                                    g.fillOval(cellSize / 4, cellSize / 4, cellSize / 2, cellSize / 2);
                                }
                            };
                            food.setBounds(bound);
                            layeredPane.add(food, JLayeredPane.PALETTE_LAYER);
                        }
                        case BLOCK -> {
                            blocks++;
                            JPanel block = new JPanel() {
                                @Override
                                protected void paintComponent(Graphics g) {
                                    g.fillRect(0, 0, cellSize, cellSize);
                                }
                            };
                            block.setBounds(bound);
                            layeredPane.add(block, JLayeredPane.DEFAULT_LAYER);
                        }
                        case EMPTY -> {
                        }
                        case HORIZONTAL_DOOR -> {
                        }
                        case VERTICAL_DOOR -> {
                        }
                        case LEFT_TOP_BORDER -> {
                        }
                        case TOP_BORDER -> {
                        }
                        case RIGHT_TOP_BORDER -> {
                        }
                        case RIGHT_BORDER -> {
                        }
                        case RIGHT_BOTTOM_BORDER -> {
                        }
                        case BOTTOM_BORDER -> {
                        }
                        case LEFT_BOTTOM_BORDER -> {
                        }
                        case LEFT_BORDER -> {
                        }
                    }
                }
            }
        }
        System.out.println(foods);
        System.out.println(blocks);
    }

}
