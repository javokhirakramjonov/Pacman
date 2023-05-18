package org.example.controller;

import org.example.domain.repository.Repository;
import org.example.domain.util.GameStateListener;
import org.example.domain.util.PositionListener;
import org.example.ui.GameScreen;
import org.example.ui.MyBoardRenderer;
import org.example.ui.MyBoardTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GameScreenController implements
        PositionListener,
        KeyEventDispatcher,
        GameStateListener {

    private final Repository repository;
    private final GameScreen gameScreen;
    private final MyBoardTable boardTable;

    public GameScreenController(GameScreen mainScreen, int columns, int rows) {
        gameScreen = mainScreen;
        repository = new Repository(columns, rows);

        repository.setPositionListener(this);
        repository.setGameStateListener(this);

        boardTable = new MyBoardTable(repository);
        JTable table = new JTable(boardTable);
        table.setDefaultRenderer(Object.class, new MyBoardRenderer(repository.getCellSize()));
        table.setRowHeight(repository.getCellSize());
        gameScreen.setBoard(table);

        gameScreen.setSize(repository.getDimension());
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
    }

    public void start() {
        repository.start();
    }

    private void keyPressed(KeyEvent e) {
        if (e.isControlDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_Q) {
            stopGame();
            return;
        }
        repository.move(e.getKeyCode());
    }

    @Override
    public void dataChanged() {
        boardTable.fireTableDataChanged();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_RELEASED) {
            keyPressed(e);
        }
        return true;
    }

    @Override
    public void setLifeCount(int count) {
        gameScreen.drawHearts(count);
    }

    @Override
    public void setScore(int score) {
        gameScreen.drawScore(score);
    }

    @Override
    public void finishGame(int score, int time) {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(this);
        gameScreen.finishGame(score, time);
    }

    public void stopGame() {
        repository.stop();
    }
}
