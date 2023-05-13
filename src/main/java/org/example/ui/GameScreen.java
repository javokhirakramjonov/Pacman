package org.example.ui;

import org.example.controller.GameScreenController;
import org.example.domain.model.GhostModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameScreen extends JFrame {

    private final GameScreenController controller;
    private Dimension screenSize;
    private Dimension boardSize;
    private JTable board;

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
        screenSize = new Dimension(d.width + 100, d.height + 100);
        super.setSize(screenSize);
    }

    public void start() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        controller.start();

        board.setBounds(new Rectangle(boardSize));

        add(board);

        setFocusable(true);
        requestFocus();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void drawGhosts(List<GhostModel> data) {

    }
}
