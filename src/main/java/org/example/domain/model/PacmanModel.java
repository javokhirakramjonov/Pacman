package org.example.domain.model;

import org.example.domain.util.State;

import javax.swing.*;
import java.awt.*;

public class PacmanModel extends ActorModel {

    private State state;
    private final int eyeDiameter;

    public PacmanModel(int cellSize) {
        super(cellSize);
        eyeDiameter = cellSize / 4;
        setState(State.STAY_RIGHT);
    }

    public void setState(State state) {
        int cellSize = PacmanModel.this.getCellSize();
        switch (state) {
            case STAY_LEFT -> {
                setImage(new JPanel(){
                    @Override
                    protected void paintComponent(Graphics g) {
                        g.setColor(Color.YELLOW);
                        g.fillOval(0, 0, cellSize, cellSize);
                        g.setColor(Color.BLACK);
                        g.fillOval(cellSize / 2 - eyeDiameter / 2, cellSize / 4 - eyeDiameter / 2, eyeDiameter, eyeDiameter);
                        g.drawLine(0, cellSize / 2, cellSize / 2, cellSize / 2);
                    }
                });
            }
            case STAY_TOP -> {
                setImage(new JPanel(){
                    @Override
                    protected void paintComponent(Graphics g) {
                        g.setColor(Color.YELLOW);
                        g.fillOval(0, 0, cellSize, cellSize);
                        g.setColor(Color.BLACK);
                        g.fillOval(cellSize * 3 / 4 - eyeDiameter / 2, cellSize / 2 - eyeDiameter / 2, eyeDiameter, eyeDiameter);
                        g.drawLine(cellSize / 2, 0, cellSize / 2, cellSize / 2);
                    }
                });
            }
            case STAY_RIGHT -> {
                setImage(new JPanel(){
                    @Override
                    protected void paintComponent(Graphics g) {
                        g.setColor(Color.YELLOW);
                        g.fillOval(0, 0, cellSize, cellSize);
                        g.setColor(Color.BLACK);
                        g.fillOval(cellSize / 2 - eyeDiameter / 2, cellSize / 4 - eyeDiameter / 2, eyeDiameter, eyeDiameter);
                        g.drawLine(cellSize / 2, cellSize / 2, cellSize, cellSize / 2);
                    }
                });
            }
            case STAY_BOTTOM -> {
                setImage(new JPanel(){
                    @Override
                    protected void paintComponent(Graphics g) {
                        g.setColor(Color.YELLOW);
                        g.fillOval(0, 0, cellSize, cellSize);
                        g.setColor(Color.BLACK);
                        g.fillOval(cellSize / 4 - eyeDiameter / 2, cellSize / 2 - eyeDiameter / 2, eyeDiameter, eyeDiameter);
                        g.drawLine(cellSize / 2, cellSize / 2, cellSize / 2, cellSize);
                    }
                });
            }
            case EATING_LEFT -> {
                SwingUtilities.invokeLater(() -> {
                    for(int i = 0; i <= 30; ++ i) {
                        int finalI = i;
                        setImage(new JPanel(){
                            @Override
                            protected void paintComponent(Graphics g) {
                                g.setColor(Color.YELLOW);
                                g.fillArc(0, 0, cellSize, cellSize, 180 - finalI, 180 + finalI);
                                g.setColor(Color.BLACK);
                                g.fillOval(cellSize / 2 - eyeDiameter / 2, cellSize / 4 - eyeDiameter / 2, eyeDiameter, eyeDiameter);
                            }
                        });
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    for(int i = 0; i <= 30; ++ i) {
                        int finalI = i;
                        setImage(new JPanel(){
                            @Override
                            protected void paintComponent(Graphics g) {
                                g.setColor(Color.YELLOW);
                                g.fillArc(0, 0, cellSize, cellSize, 150 + finalI, 210 - finalI);
                                g.setColor(Color.BLACK);
                                g.fillOval(cellSize / 2 - eyeDiameter / 2, cellSize / 4 - eyeDiameter / 2, eyeDiameter, eyeDiameter);
                            }
                        });
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
            case EATING_TOP -> {
                SwingUtilities.invokeLater(() -> {
                    for(int i = 0; i <= 30; ++ i) {
                        int finalI = i;
                        setImage(new JPanel(){
                            @Override
                            protected void paintComponent(Graphics g) {
                                g.setColor(Color.YELLOW);
                                g.fillArc(0, 0, cellSize, cellSize, 90 - finalI, 90 + finalI);
                                g.setColor(Color.BLACK);
                                g.fillOval(cellSize * 3 / 4 - eyeDiameter / 2, cellSize / 2 - eyeDiameter / 2, eyeDiameter, eyeDiameter);
                            }
                        });
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    for(int i = 0; i <= 30; ++ i) {
                        int finalI = i;
                        setImage(new JPanel(){
                            @Override
                            protected void paintComponent(Graphics g) {
                                g.setColor(Color.YELLOW);
                                g.fillArc(0, 0, cellSize, cellSize, 60 + finalI, 120 - finalI);
                                g.setColor(Color.BLACK);
                                g.fillOval(cellSize * 3 / 4 - eyeDiameter / 2, cellSize / 2 - eyeDiameter / 2, eyeDiameter, eyeDiameter);
                            }
                        });
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
            case EATING_RIGHT -> {
                SwingUtilities.invokeLater(() -> {
                    for(int i = 0; i <= 30; ++ i) {
                        int finalI = i;
                        setImage(new JPanel(){
                            @Override
                            protected void paintComponent(Graphics g) {
                                g.setColor(Color.YELLOW);
                                g.fillArc(0, 0, cellSize, cellSize, finalI, 360 - finalI);
                                g.setColor(Color.BLACK);
                                g.fillOval(cellSize / 2 - eyeDiameter / 2, cellSize / 4 - eyeDiameter / 2, eyeDiameter, eyeDiameter);
                            }
                        });
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    for(int i = 0; i <= 30; ++ i) {
                        int finalI = i;
                        setImage(new JPanel(){
                            @Override
                            protected void paintComponent(Graphics g) {
                                g.setColor(Color.YELLOW);
                                g.fillArc(0, 0, cellSize, cellSize, 30 - finalI, 330 + finalI);
                                g.setColor(Color.BLACK);
                                g.fillOval(cellSize / 2 - eyeDiameter / 2, cellSize / 4 - eyeDiameter / 2, eyeDiameter, eyeDiameter);
                            }
                        });
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
            case EATING_BOTTOM -> {
                SwingUtilities.invokeLater(() -> {
                    for(int i = 0; i <= 30; ++ i) {
                        int finalI = i;
                        setImage(new JPanel(){
                            @Override
                            protected void paintComponent(Graphics g) {
                                g.setColor(Color.YELLOW);
                                g.fillArc(0, 0, cellSize, cellSize, 270 - finalI, 270 + finalI);
                                g.setColor(Color.BLACK);
                                g.fillOval(cellSize / 4 - eyeDiameter / 2, cellSize / 2 - eyeDiameter / 2, eyeDiameter, eyeDiameter);
                            }
                        });
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    for(int i = 0; i <= 30; ++ i) {
                        int finalI = i;
                        setImage(new JPanel(){
                            @Override
                            protected void paintComponent(Graphics g) {
                                g.setColor(Color.YELLOW);
                                g.fillArc(0, 0, cellSize, cellSize, 240 + finalI, 300 - finalI);
                                g.setColor(Color.BLACK);
                                g.fillOval(cellSize / 4 - eyeDiameter / 2, cellSize / 2 - eyeDiameter / 2, eyeDiameter, eyeDiameter);
                            }
                        });
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        }
    }
}
