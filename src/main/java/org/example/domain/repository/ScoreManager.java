package org.example.domain.repository;

import org.example.domain.model.ScoreModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreManager {
    private final String SCORE_FILE_NAME = "PacmanGameScores.txt";

    public List<ScoreModel> getScores() {
        List<ScoreModel> scores = null;
        try (FileInputStream in = new FileInputStream(SCORE_FILE_NAME);
             ObjectInputStream s = new ObjectInputStream(in)) {
            scores = (List<ScoreModel>) s.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (scores == null) {
            scores = new ArrayList<>();
        }
        scores.sort((score1, score2) ->
                score2.getScore() == score1.getScore() ?
                        Integer.compare(score1.getInSecond(), score2.getInSecond()) :
                        Integer.compare(score2.getScore(), score1.getScore()));
        return scores;
    }

    public void addNewScore(ScoreModel scoreModel) {
        List<ScoreModel> scores = getScores();
        try (
                FileOutputStream f = new FileOutputStream(SCORE_FILE_NAME);
                ObjectOutputStream s = new ObjectOutputStream(f)
        ) {
            scores.forEach(System.out::println);
            scores.add(scoreModel);
            s.writeObject(scores);
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}