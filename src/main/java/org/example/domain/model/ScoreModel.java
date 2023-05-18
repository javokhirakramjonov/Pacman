package org.example.domain.model;

import java.io.Serializable;

public class ScoreModel implements Serializable {
    private final String name;
    private final int score;
    private final int inSecond;

    public ScoreModel(String name, int score, int inSecond) {
        this.name = name;
        this.score = score;
        this.inSecond = inSecond;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getTime() {
        int hours = inSecond / 3600;
        int minutes = inSecond % 3600 / 60;
        int seconds = inSecond % 60;

        String time = "";

        time += (hours < 10 ? "0" : "");
        time += hours;
        time += ':';
        time += (minutes < 10 ? "0" : "");
        time += minutes;
        time += ':';
        time += (seconds < 10 ? "0" : "");
        time += seconds;

        return time;
    }

    public int getInSecond() {
        return inSecond;
    }

    @Override
    public String toString() {
        return "[Name: " + name + ", Score: " + score + ", Time: " + inSecond + "]";
    }
}
