package org.example.controller;

import org.example.domain.model.Repository;
import org.example.ui.GameScreen;

public class GameScreenController {

    private final Repository repository;
    private final GameScreen gameScreen;

    public GameScreenController(
            GameScreen mainScreen,
            int columns,
            int rows
    ) {
        this.gameScreen = mainScreen;
        this.repository = new Repository(columns, rows);
    }

    public void start() {
        gameScreen.setSize(repository.getDimension());
        gameScreen.drawBoard(repository.getCellSize(), repository.getBoard());
    }

}
