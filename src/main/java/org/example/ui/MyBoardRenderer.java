package org.example.ui;

import org.example.domain.model.ActorModel;
import org.example.domain.model.CellModel;
import org.example.domain.model.GhostModel;
import org.example.domain.model.PacmanModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class MyBoardRenderer extends JPanel implements TableCellRenderer {

    private final int cellSize;
    private final int borderSize;
    List<Object> cellData;
    private Image image;

    public MyBoardRenderer(int cellSize) {
        setLayout(new OverlayLayout(this));
        this.cellSize = cellSize;
        borderSize = 4;
        setOpaque(false);
        setSize(new Dimension(cellSize, cellSize));
        try {
            image = ImageIO.read(getClass().getClassLoader().getResource("floor.jpg"));
            if (image != null) {
                image = image.getScaledInstance(cellSize, cellSize, Image.SCALE_DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {
        cellData = (List) value;
        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
        removeAll();
        if (image != null) {
            g.drawImage(image, 0, 0, null);
        }
        for (Object element : cellData) {
            if (element instanceof List cell) {
                drawBoardElements((List<CellModel>) cell, g);
            }
        }
        for (Object element : cellData) {
            if (element instanceof ActorModel actor) {
                drawActor(actor, g);
            }
        }
    }

    private void drawActor(ActorModel actor, Graphics g) {
        if (actor instanceof GhostModel ghost) {
            g.drawImage(ghost.getImage(), 0, 0, null);
        }
        if (actor instanceof PacmanModel pacman) {
            pacman.drawState(g);
        }
    }

    private void drawBoardElements(List<CellModel> cellData, Graphics g) {
        for (CellModel cell : cellData) {
            g.setColor(Color.BLACK);
            switch (cell) {
                case FOOD -> {
                    g.setColor(new Color(0, 255, 140, 255));
                    g.fillOval(cellSize / 4, cellSize / 4, cellSize / 2, cellSize / 2);
                }
                case BLOCK -> {
                    g.fillRect(0, 0, cellSize, cellSize);
                }
                case HORIZONTAL_DOOR -> {
                    g.fillRect(0, 0, cellSize, borderSize);
                    g.fillRect(0, cellSize - borderSize, cellSize, borderSize);
                }
                case VERTICAL_DOOR -> {
                    g.fillRect(0, 0, borderSize, cellSize);
                    g.fillRect(cellSize - borderSize, 0, borderSize, cellSize);
                }
                case LEFT_TOP_BORDER -> {
                    g.fillRect(0, 0, borderSize, cellSize);
                    g.fillRect(0, 0, cellSize, borderSize);
                }
                case TOP_BORDER -> {
                    g.fillRect(0, 0, cellSize, borderSize);
                }
                case RIGHT_TOP_BORDER -> {
                    g.fillRect(0, 0, cellSize, borderSize);
                    g.fillRect(cellSize - borderSize, 0, borderSize, cellSize);
                }
                case RIGHT_BORDER -> {
                    g.fillRect(cellSize - borderSize, 0, borderSize, cellSize);
                }
                case RIGHT_BOTTOM_BORDER -> {
                    g.fillRect(0, cellSize - borderSize, cellSize, borderSize);
                    g.fillRect(cellSize - borderSize, 0, borderSize, cellSize);
                }
                case BOTTOM_BORDER -> {
                    g.fillRect(0, cellSize - borderSize, cellSize, borderSize);
                }
                case LEFT_BOTTOM_BORDER -> {
                    g.fillRect(0, 0, borderSize, cellSize);
                    g.fillRect(0, cellSize - borderSize, cellSize, borderSize);
                }
                case LEFT_BORDER -> {
                    g.fillRect(0, 0, borderSize, cellSize);
                }
            }
        }
    }
}
