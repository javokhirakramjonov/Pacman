package org.example.domain.model;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.stream.IntStream;

public class Repository {

    private final int columns;
    private final int rows;
    private final List<CellModel>[][] board;

    private final int CELL_SIZE = 32;
    private final int MAX_GHOSTS_COUNT;

    private final PacmanModel pacman;

    private final List<GhostModel> ghosts;
    private int ghostsCount;

    public Repository(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        ghostsCount = columns * rows / 20;
        MAX_GHOSTS_COUNT = ghostsCount * 2;

        pacman = new PacmanModel();
        ghosts = new ArrayList<>(ghostsCount);

        board = new List[rows][columns];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                board[i][j] = new LinkedList<>();
            }
        }
        addBlocks(board);
        addFoods(board);
        addBorders(board);
    }

    public List<CellModel>[][] getBoard() {
        return board;
    }

    public int getCellSize() {
        return CELL_SIZE;
    }

    public Dimension getDimension() {
        return new Dimension(columns * CELL_SIZE, rows * CELL_SIZE);
    }

    private void addBlocks(List<CellModel>[][] board) {
        Random random = new Random();
        int blockCount = rows * columns / 6;

        int count = 0;
        boolean[][] used = new boolean[rows][columns];

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                used[i][j] = false;
            }
        }

        while (count < blockCount) {
            Point point = new Point(random.nextInt(rows), random.nextInt(columns));
            if (!used[point.x][point.y]) {
                used[point.x][point.y] = true;
                count++;
            }
        }

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                if (used[i][j]) {
                    board[i][j].add(CellModel.BLOCK);
                }
            }
        }
    }

    private void addFoods(List<CellModel>[][] board) {
        Arrays
                .stream(board)
                .flatMap(Arrays::stream)
                .filter(List::isEmpty)
                .forEach(cell -> cell.add(CellModel.FOOD));
    }

    private void addBorders(List<CellModel>[][] board) {
        Random random = new Random();
        boolean horizontalDoor = random.nextBoolean();

        if (horizontalDoor) {
            List<Integer> freeLeft = new ArrayList<>();
            IntStream
                    .range(0, rows)
                    .filter(i -> !board[i][0].contains(CellModel.BLOCK))
                    .forEach(freeLeft::add);
            List<Integer> freeRight = new ArrayList<>();
            IntStream
                    .range(0, rows)
                    .filter(i -> !board[i][columns - 1].contains(CellModel.BLOCK))
                    .forEach(freeRight::add);
            if (!freeLeft.isEmpty() && !freeRight.isEmpty()) {
                Collections.shuffle(freeLeft);
                Collections.shuffle(freeRight);

                int leftDoorRow = freeLeft.get(0);
                int rightDoorRow = freeRight.get(0);

                board[leftDoorRow][0].add(CellModel.HORIZONTAL_DOOR);
                board[rightDoorRow][columns - 1].add(CellModel.HORIZONTAL_DOOR);
            }
        } else {
            List<Integer> freeTop = new ArrayList<>();
            IntStream
                    .range(0, columns)
                    .filter(i -> !board[0][i].contains(CellModel.BLOCK))
                    .forEach(freeTop::add);
            List<Integer> freeBottom = new ArrayList<>();
            IntStream
                    .range(0, columns)
                    .filter(i -> !board[rows - 1][i].contains(CellModel.BLOCK))
                    .forEach(freeBottom::add);
            if (!freeTop.isEmpty() && !freeBottom.isEmpty()) {
                Collections.shuffle(freeTop);
                Collections.shuffle(freeBottom);

                int topDoorColumn = freeTop.get(0);
                int bottomDoorColumn = freeBottom.get(0);

                board[0][topDoorColumn].add(CellModel.VERTICAL_DOOR);
                board[rows - 1][bottomDoorColumn].add(CellModel.VERTICAL_DOOR);
            }
        }
    }

}
