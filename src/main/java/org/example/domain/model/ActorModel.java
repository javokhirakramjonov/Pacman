package org.example.domain.model;

import org.example.domain.util.Direction;

import javax.swing.*;
import java.awt.*;

public abstract class ActorModel {

    private final int cellSize;
    private Point position;
    private int speed;
    private Direction direction;
    private boolean isLive;
    private volatile JPanel image;
    public ActorModel(int size) {
        this.cellSize = size;
    }

    public int getCellSize() {
        return cellSize;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public JPanel getImage() {
        return image;
    }

    public void setImage(JPanel image) {
        this.image = image;
    }
}
