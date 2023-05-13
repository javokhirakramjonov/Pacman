package org.example.controller;

import org.example.domain.model.Repository;
import org.example.ui.GameScreen;
import org.example.ui.MainScreen;
import org.example.ui.MyBoardRenderer;
import org.example.ui.MyBoardTable;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScreenController extends KeyAdapter implements KeyListener {

    private final Repository repository;
    private final GameScreen gameScreen;
    private final MyBoardTable boardTable;

    public GameScreenController(
            GameScreen mainScreen,
            int columns,
            int rows
    ) {
        this.gameScreen = mainScreen;
        this.repository = new Repository(columns, rows);

        boardTable = new MyBoardTable(repository);
        JTable table = new JTable(boardTable);
        table.setDefaultRenderer(Object.class, new MyBoardRenderer(repository.getCellSize()));
        table.setRowHeight(repository.getCellSize());
        gameScreen.setBoard(table);

        gameScreen.setSize(repository.getDimension());
        gameScreen.addKeyListener(GameScreenController.this);
    }

    public void start() {
        repository.start();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isControlDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_Q) {
            gameScreen.setVisible(false);
            new MainScreen().start();
        }
    }
}
