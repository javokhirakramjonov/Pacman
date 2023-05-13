package org.example.domain.model;

import org.example.domain.util.State;

import java.awt.*;

public class PacmanModel extends ActorModel {

    private final int eyeDiameter;
    private State state;

    public PacmanModel(int cellSize) {
        super(cellSize);
        eyeDiameter = cellSize / 4;
        state = State.STAY_RIGHT;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void drawState(Graphics g) {
        int cellSize = getCellSize();

        switch (state) {
            case STAY_LEFT -> {
                g.setColor(Color.YELLOW);
                g.fillOval(0, 0, cellSize, cellSize);
                g.setColor(Color.BLACK);
                g.fillOval(cellSize / 2 - eyeDiameter / 2, cellSize / 4 - eyeDiameter / 2, eyeDiameter, eyeDiameter);
                g.drawLine(0, cellSize / 2, cellSize / 2, cellSize / 2);
            }
            case STAY_TOP -> {
                g.setColor(Color.YELLOW);
                g.fillOval(0, 0, cellSize, cellSize);
                g.setColor(Color.BLACK);
                g.fillOval(cellSize * 3 / 4 - eyeDiameter / 2, cellSize / 2 - eyeDiameter / 2, eyeDiameter, eyeDiameter);
                g.drawLine(cellSize / 2, 0, cellSize / 2, cellSize / 2);
            }
            case STAY_RIGHT -> {
                g.setColor(Color.YELLOW);
                g.fillOval(0, 0, cellSize, cellSize);
                g.setColor(Color.BLACK);
                g.fillOval(cellSize / 2 - eyeDiameter / 2, cellSize / 4 - eyeDiameter / 2, eyeDiameter, eyeDiameter);
                g.drawLine(cellSize / 2, cellSize / 2, cellSize, cellSize / 2);
            }
            case STAY_BOTTOM -> {
                g.setColor(Color.YELLOW);
                g.fillOval(0, 0, cellSize, cellSize);
                g.setColor(Color.BLACK);
                g.fillOval(cellSize / 4 - eyeDiameter / 2, cellSize / 2 - eyeDiameter / 2, eyeDiameter, eyeDiameter);
                g.drawLine(cellSize / 2, cellSize / 2, cellSize / 2, cellSize);
            }
            case EATING_LEFT -> {
                g.setColor(Color.YELLOW);
                g.fillArc(0, 0, cellSize, cellSize, -150, 150);
                g.setColor(Color.BLACK);
                g.fillOval(cellSize / 2 - eyeDiameter / 2, cellSize / 4 - eyeDiameter / 2, eyeDiameter, eyeDiameter);
            }
            case EATING_TOP -> {
                g.setColor(Color.YELLOW);
                g.fillArc(0, 0, cellSize, cellSize, -240, 60);
                g.setColor(Color.BLACK);
                g.fillOval(cellSize * 3 / 4 - eyeDiameter / 2, cellSize / 2 - eyeDiameter / 2, eyeDiameter, eyeDiameter);
            }
            case EATING_RIGHT -> {
                g.setColor(Color.YELLOW);
                g.fillArc(0, 0, cellSize, cellSize, 30, 330);
                g.setColor(Color.BLACK);
                g.fillOval(cellSize / 2 - eyeDiameter / 2, cellSize / 4 - eyeDiameter / 2, eyeDiameter, eyeDiameter);
            }
            case EATING_BOTTOM -> {
                g.setColor(Color.YELLOW);
                g.fillArc(0, 0, cellSize, cellSize, -60, 240);
                g.setColor(Color.BLACK);
                g.fillOval(cellSize / 4 - eyeDiameter / 2, cellSize / 2 - eyeDiameter / 2, eyeDiameter, eyeDiameter);
            }
        }
    }
}
