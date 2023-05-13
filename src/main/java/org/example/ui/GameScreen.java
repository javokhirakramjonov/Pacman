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
    private Dimension boardSize;

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
        boardSize = new Dimension(d.width, d.height);
        screenSize = new Dimension(d.width + 100, d.height + 100);
        super.setSize(screenSize);
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
        int borderSize = 4;
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                Rectangle bound = new Rectangle(
                        new Point(j * cellSize, i * cellSize),
                        new Dimension(cellSize, cellSize)
                );
                for (CellModel cell : board[i][j]) {
                    switch (cell) {
                        case FOOD -> {
                            JPanel food = new JPanel() {
                                @Override
                                public void paintComponent(Graphics g) {
                                    g.fillOval(cellSize / 4, cellSize / 4, cellSize / 2, cellSize / 2);
                                }
                            };
                            food.setOpaque(false);
                            food.setBounds(bound);
                            layeredPane.add(food, JLayeredPane.PALETTE_LAYER);
                        }
                        case BLOCK -> {
                            JPanel block = new JPanel() {
                                @Override
                                protected void paintComponent(Graphics g) {
                                    g.fillRect(0, 0, cellSize, cellSize);
                                }
                            };
                            block.setOpaque(false);
                            block.setBounds(bound);
                            layeredPane.add(block, JLayeredPane.DEFAULT_LAYER);
                        }
                        case EMPTY -> {
                        }
                        case HORIZONTAL_DOOR -> {
                            JPanel horizontalDoor = new JPanel() {
                                @Override
                                protected void paintComponent(Graphics g) {
                                    g.fillRect(0, 0, cellSize, borderSize);
                                    g.fillRect(0, cellSize - borderSize, cellSize, borderSize);
                                }
                            };
                            horizontalDoor.setOpaque(false);
                            horizontalDoor.setBounds(bound);
                            layeredPane.add(horizontalDoor, JLayeredPane.DEFAULT_LAYER);
                        }
                        case VERTICAL_DOOR -> {
                            JPanel verticalDoor = new JPanel() {
                                @Override
                                protected void paintComponent(Graphics g) {
                                    g.fillRect(0, 0, borderSize, cellSize);
                                    g.fillRect(cellSize - borderSize, 0, borderSize, cellSize);
                                }
                            };
                            verticalDoor.setOpaque(false);
                            verticalDoor.setBounds(bound);
                            layeredPane.add(verticalDoor, JLayeredPane.DEFAULT_LAYER);
                        }
                        case LEFT_TOP_BORDER -> {
                            JPanel leftBorder = new JPanel() {
                                @Override
                                protected void paintComponent(Graphics g) {
                                    g.fillRect(0, 0, borderSize, cellSize);
                                    g.fillRect(0, 0, cellSize, borderSize);
                                }
                            };
                            leftBorder.setOpaque(false);
                            leftBorder.setBounds(bound);
                            layeredPane.add(leftBorder, JLayeredPane.DEFAULT_LAYER);
                        }
                        case TOP_BORDER -> {
                            JPanel topBorder = new JPanel() {
                                @Override
                                protected void paintComponent(Graphics g) {
                                    g.fillRect(0, 0, cellSize, borderSize);
                                }
                            };
                            topBorder.setOpaque(false);
                            topBorder.setBounds(bound);
                            layeredPane.add(topBorder, JLayeredPane.DEFAULT_LAYER);
                        }
                        case RIGHT_TOP_BORDER -> {
                            JPanel rightTopBorder = new JPanel() {
                                @Override
                                protected void paintComponent(Graphics g) {
                                    g.fillRect(0, 0, cellSize, borderSize);
                                    g.fillRect(cellSize - borderSize, 0, borderSize, cellSize);
                                }
                            };
                            rightTopBorder.setOpaque(false);
                            rightTopBorder.setBounds(bound);
                            layeredPane.add(rightTopBorder, JLayeredPane.DEFAULT_LAYER);
                        }
                        case RIGHT_BORDER -> {
                            JPanel rightBorder = new JPanel() {
                                @Override
                                protected void paintComponent(Graphics g) {
                                    g.fillRect(cellSize - borderSize, 0, borderSize, cellSize);
                                }
                            };
                            rightBorder.setOpaque(false);
                            rightBorder.setBounds(bound);
                            layeredPane.add(rightBorder, JLayeredPane.DEFAULT_LAYER);
                        }
                        case RIGHT_BOTTOM_BORDER -> {
                            JPanel rightBottomBorder = new JPanel() {
                                @Override
                                protected void paintComponent(Graphics g) {
                                    g.fillRect(0, cellSize - borderSize, cellSize, borderSize);
                                    g.fillRect(cellSize - borderSize, 0, borderSize, cellSize);
                                }
                            };
                            rightBottomBorder.setOpaque(false);
                            rightBottomBorder.setBounds(bound);
                            layeredPane.add(rightBottomBorder, JLayeredPane.DEFAULT_LAYER);
                        }
                        case BOTTOM_BORDER -> {
                            JPanel bottomBorder = new JPanel() {
                                @Override
                                protected void paintComponent(Graphics g) {
                                    g.fillRect(0, cellSize - borderSize, cellSize, borderSize);
                                }
                            };
                            bottomBorder.setOpaque(false);
                            bottomBorder.setBounds(bound);
                            layeredPane.add(bottomBorder, JLayeredPane.DEFAULT_LAYER);
                        }
                        case LEFT_BOTTOM_BORDER -> {
                            JPanel leftBottomBorder = new JPanel() {
                                @Override
                                protected void paintComponent(Graphics g) {
                                    g.fillRect(0, 0, borderSize, cellSize);
                                    g.fillRect(0, cellSize - borderSize, cellSize, borderSize);
                                }
                            };
                            leftBottomBorder.setOpaque(false);
                            leftBottomBorder.setBounds(bound);
                            layeredPane.add(leftBottomBorder, JLayeredPane.DEFAULT_LAYER);
                        }
                        case LEFT_BORDER -> {
                            JPanel leftBorder = new JPanel() {
                                @Override
                                protected void paintComponent(Graphics g) {
                                    g.fillRect(0, 0, borderSize, cellSize);
                                }
                            };
                            leftBorder.setOpaque(false);
                            leftBorder.setBounds(bound);
                            layeredPane.add(leftBorder, JLayeredPane.DEFAULT_LAYER);
                        }
                    }
                }
            }
        }
    }

}
