package org.example.domain.model;

import org.example.domain.util.Direction;
import org.example.domain.util.Move;

import javax.swing.*;
import java.awt.*;

public abstract class ActorModel implements Move {

    private final int cellSize;
    private Point position;
    private int speed;
    private Direction direction;
    private boolean isLive;
    private JComponent image;

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

    public JComponent getImage() {
        return image;
    }

    public void setImage(JComponent image) {
        this.image = image;
    }

    @Override
    public void goUp() {
        position.x--;
    }

    @Override
    public void goRight() {
        position.y++;
    }

    @Override
    public void goDown() {
        position.x++;
    }

    @Override
    public void goLeft() {
        position.y--;
    }
}
