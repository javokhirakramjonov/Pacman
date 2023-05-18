package org.example.domain.model;

import org.example.domain.util.State;

import java.awt.*;

public class PacmanModel extends ActorModel {

    private final int eyeDiameter;
    private State state;

    public PacmanModel(int cellSize) {
        super(cellSize);
        eyeDiameter = cellSize / 4;
        setState(State.STAY_RIGHT);
        setSpeed(150);
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
                g.fillArc(0, 0, cellSize, cellSize, 210, 300);
                g.setColor(Color.BLACK);
                g.fillOval(cellSize / 2 - eyeDiameter / 2, cellSize / 4 - eyeDiameter / 2, eyeDiameter, eyeDiameter);
            }
            case EATING_TOP -> {
                g.setColor(Color.YELLOW);
                g.fillArc(0, 0, cellSize, cellSize, 120, 300);
                g.setColor(Color.BLACK);
                g.fillOval(cellSize * 3 / 4 - eyeDiameter / 2, cellSize / 2 - eyeDiameter / 2, eyeDiameter, eyeDiameter);
            }
            case EATING_RIGHT -> {
                g.setColor(Color.YELLOW);
                g.fillArc(0, 0, cellSize, cellSize, 30, 300);
                g.setColor(Color.BLACK);
                g.fillOval(cellSize / 2 - eyeDiameter / 2, cellSize / 4 - eyeDiameter / 2, eyeDiameter, eyeDiameter);
            }
            case EATING_BOTTOM -> {
                g.setColor(Color.YELLOW);
                g.fillArc(0, 0, cellSize, cellSize, 300, 300);
                g.setColor(Color.BLACK);
                g.fillOval(cellSize / 4 - eyeDiameter / 2, cellSize / 2 - eyeDiameter / 2, eyeDiameter, eyeDiameter);
            }
        }
    }

    public void setEating(boolean contains) {
        if (contains) {
            switch (getDirection()) {
                case UP -> {
                    setState(State.EATING_TOP);
                }
                case RIGHT -> {
                    setState(State.EATING_RIGHT);
                }
                case DOWN -> {
                    setState(State.EATING_BOTTOM);
                }
                case LEFT -> {
                    setState(State.EATING_LEFT);
                }
            }
        } else {
            switch (getDirection()) {
                case UP -> {
                    setState(State.STAY_TOP);
                }
                case RIGHT -> {
                    setState(State.STAY_RIGHT);
                }
                case DOWN -> {
                    setState(State.STAY_BOTTOM);
                }
                case LEFT -> {
                    setState(State.STAY_LEFT);
                }
            }
        }
    }
}
