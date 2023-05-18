package org.example.controller;

import org.example.domain.model.ScoreModel;
import org.example.domain.repository.ScoreManager;

import java.util.List;

public class HighScoresController {

    private final ScoreManager manager;

    public HighScoresController() {
        manager = new ScoreManager();
    }

    public List<ScoreModel> getHighScores() {
        return manager.getScores();
    }
}
