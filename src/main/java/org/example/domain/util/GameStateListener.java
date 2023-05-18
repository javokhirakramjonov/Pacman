package org.example.domain.util;

public interface GameStateListener {
    void setLifeCount(int count);

    void setScore(int score);

    void finishGame(int score, int time);
}
