package org.example.domain.model;

import org.example.domain.util.Move;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.stream.IntStream;

public class Repository implements Move {

    private final int columns;
    private final int rows;
    private final List<CellModel>[][] board;

    private final int CELL_SIZE = 28;
    private final int MAX_GHOSTS_COUNT;

    private final PacmanModel pacman;

    private final List<GhostModel> ghosts;
    private final int[] directionX = {-1, 0, 1, 0};
    private final int[] directionY = {0, 1, 0, -1};
    private int ghostsCount;

    public Repository(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        ghostsCount = columns * rows / 20;
        MAX_GHOSTS_COUNT = ghostsCount * 2;

        pacman = new PacmanModel(CELL_SIZE);
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
        int top = rows / 2 - rows / 5;
        int left = columns / 2 - columns / 5;
        int bottom = rows - top - 1;
        int right = columns - left - 1;

        for (int i = left; i <= right; ++i) {
            board[top][i].add(CellModel.BLOCK);
            board[bottom][i].add(CellModel.BLOCK);
        }

        for (int i = top; i <= bottom; ++i) {
            board[i][left].add(CellModel.BLOCK);
            board[i][right].add(CellModel.BLOCK);
        }

        Dimension smallBlockSize = new Dimension((rows + 9) / 10, (columns + 9) / 10);

        for (int i = 1; i <= smallBlockSize.height; ++i) {
            for (int j = 1; j <= smallBlockSize.width; ++j) {
                board[i][j].add(CellModel.BLOCK);
            }
        }

        for (int i = 1; i <= smallBlockSize.height; ++i) {
            for (int j = 1; j <= smallBlockSize.width; ++j) {
                board[rows - i - 1][j].add(CellModel.BLOCK);
            }
        }

        for (int i = 1; i <= smallBlockSize.height; ++i) {
            for (int j = 1; j <= smallBlockSize.width; ++j) {
                board[i][columns - j - 1].add(CellModel.BLOCK);
            }
        }

        for (int i = 1; i <= smallBlockSize.height; ++i) {
            for (int j = 1; j <= smallBlockSize.width; ++j) {
                board[rows - i - 1][columns - j - 1].add(CellModel.BLOCK);
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
                    .filter(i -> !board[i][1].contains(CellModel.BLOCK))
                    .forEach(freeLeft::add);
            List<Integer> freeRight = new ArrayList<>();
            IntStream
                    .range(0, rows)
                    .filter(i -> !board[i][columns - 2].contains(CellModel.BLOCK))
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
                    .filter(i -> !board[1][i].contains(CellModel.BLOCK))
                    .forEach(freeTop::add);
            List<Integer> freeBottom = new ArrayList<>();
            IntStream
                    .range(0, columns)
                    .filter(i -> !board[rows - 2][i].contains(CellModel.BLOCK))
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

        if (!board[0][0].contains(CellModel.HORIZONTAL_DOOR) && !board[0][0].contains(CellModel.VERTICAL_DOOR)) {
            board[0][0].add(CellModel.LEFT_TOP_BORDER);
        }
        if (!board[0][columns - 1].contains(CellModel.HORIZONTAL_DOOR) && !board[0][columns - 1].contains(CellModel.VERTICAL_DOOR)) {
            board[0][columns - 1].add(CellModel.RIGHT_TOP_BORDER);
        }
        if (!board[rows - 1][0].contains(CellModel.HORIZONTAL_DOOR) && !board[rows - 1][0].contains(CellModel.VERTICAL_DOOR)) {
            board[rows - 1][0].add(CellModel.LEFT_BOTTOM_BORDER);
        }
        if (!board[rows - 1][columns - 1].contains(CellModel.HORIZONTAL_DOOR) && !board[rows - 1][columns - 1].contains(CellModel.VERTICAL_DOOR)) {
            board[rows - 1][columns - 1].add(CellModel.RIGHT_BOTTOM_BORDER);
        }

        IntStream
                .range(1, rows - 1)
                .filter(row ->
                        !board[row][0].contains(CellModel.HORIZONTAL_DOOR) &&
                                !board[row][0].contains(CellModel.VERTICAL_DOOR))
                .forEach(row -> board[row][0].add(CellModel.LEFT_BORDER));

        IntStream
                .range(1, rows - 1)
                .filter(row ->
                        !board[row][columns - 1].contains(CellModel.HORIZONTAL_DOOR) &&
                                !board[row][columns - 1].contains(CellModel.VERTICAL_DOOR))
                .forEach(row -> board[row][columns - 1].add(CellModel.RIGHT_BORDER));

        IntStream
                .range(1, columns - 1)
                .filter(column ->
                        !board[0][column].contains(CellModel.HORIZONTAL_DOOR) &&
                                !board[0][column].contains(CellModel.VERTICAL_DOOR))
                .forEach(column -> board[0][column].add(CellModel.TOP_BORDER));

        IntStream
                .range(1, columns - 1)
                .filter(column ->
                        !board[rows - 1][column].contains(CellModel.HORIZONTAL_DOOR) &&
                                !board[rows - 1][column].contains(CellModel.VERTICAL_DOOR))
                .forEach(column -> board[rows - 1][column].add(CellModel.BOTTOM_BORDER));
    }

    @Override
    public void goUp(ActorModel actorModel) {
        if (actorModel instanceof PacmanModel pacman) {

        }
    }

    @Override
    public void goRight(ActorModel actorModel) {

    }

    @Override
    public void goDown(ActorModel actorModel) {

    }

    @Override
    public void goLeft(ActorModel actorModel) {

    }
}
