package org.example.domain.model;

import org.example.domain.util.Direction;
import org.example.domain.util.GameStateListener;
import org.example.domain.util.PositionListener;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.*;
import java.util.stream.IntStream;

public class Repository {

    private final int GHOST_SPEED_MS = 300;
    private final int columns;
    private final int rows;
    private final List<CellModel>[][] board;

    private final int CELL_SIZE = 28;
    private final int MAX_GHOSTS_COUNT;

    private final PacmanModel pacman;
    private final int[] directionX = {-1, 0, 1, 0};
    private final int[] directionY = {0, 1, 0, -1};
    private final int ghostsCount;
    private final Map<Direction, Integer> nextPos = new HashMap<>();
    private final Map<Integer, Direction> nextPosKeyboard = new HashMap<>();
    private final List<GhostModel> ghosts;
    private boolean isPlaying;

    private PositionListener positionListener;

    private GameStateListener gameStateListener;

    public Repository(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        ghostsCount = 4;
        MAX_GHOSTS_COUNT = ghostsCount * 2;

        pacman = new PacmanModel(CELL_SIZE);
        ghosts = new ArrayList<>(ghostsCount);

        nextPos.put(Direction.UP, 0);
        nextPos.put(Direction.RIGHT, 1);
        nextPos.put(Direction.DOWN, 2);
        nextPos.put(Direction.LEFT, 3);
        nextPosKeyboard.put(KeyEvent.VK_UP, Direction.UP);
        nextPosKeyboard.put(KeyEvent.VK_RIGHT, Direction.RIGHT);
        nextPosKeyboard.put(KeyEvent.VK_DOWN, Direction.DOWN);
        nextPosKeyboard.put(KeyEvent.VK_LEFT, Direction.LEFT);

        board = new List[rows][columns];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                board[i][j] = new LinkedList<>();
            }
        }
        addBlocks();
        addFoods();
        addBorders();
        createGhosts();
        createPacman();
    }

    public void setGameStateListener(GameStateListener gameStateListener) {
        this.gameStateListener = gameStateListener;
    }

    public void setPositionListener(PositionListener positionListener) {
        this.positionListener = positionListener;
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

    private void createPacman() {
        pacman.setDirection(Direction.RIGHT);
        pacman.setPosition(new Point(rows / 2, 0));
    }

    private void createGhosts() {
        IntStream
                .range(0, ghostsCount)
                .forEach(i -> ghosts.add(new GhostModel(CELL_SIZE)));

        Random random = new Random();

        ghosts.forEach(ghost -> {
            ghost.setPosition(new Point(rows / 2, columns / 2));
            ghost.setLive(true);

            Direction direction = Direction.values()[random.nextInt(Direction.values().length)];
            ghost.setDirection(direction);
        });
    }

    private void addBlocks() {
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

        board[top][left + right >> 1].remove(CellModel.BLOCK);
        board[top][(left + right >> 1) + 1].remove(CellModel.BLOCK);
    }

    private void addFoods() {
        Arrays
                .stream(board)
                .flatMap(Arrays::stream)
                .filter(List::isEmpty)
                .forEach(cell -> cell.add(CellModel.FOOD));
    }

    private void addBorders() {
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

    public void start() {
        isPlaying = true;
        actGhosts();
    }

    private void actGhosts() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(GHOST_SPEED_MS);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                if (isPlaying) {
                    ghosts.forEach(Repository.this::moveFreeDirection);
                }
            }
        }).start();
    }

    private void moveFreeDirection(GhostModel ghost) {

        int x = ghost.getPosition().x;
        int y = ghost.getPosition().y;

        List<Direction> directions = new LinkedList<>();
        for (Direction direction : Direction.values()) {
            if (
                    canMove(
                            x,
                            y,
                            x + directionX[nextPos.get(direction)],
                            y + directionY[nextPos.get(direction)],
                            direction
                    )
            ) {
                directions.add(direction);
            }
        }
        Collections.shuffle(directions);
        Direction direction = directions.get(0);
        ghost.setDirection(direction);
        moveActor(ghost);
    }

    private boolean canMove(int oldX, int oldY, int x, int y, Direction direction) {
        if (board[oldX][oldY].contains(CellModel.VERTICAL_DOOR) && (direction == Direction.UP || direction == Direction.DOWN)) {
            return true;
        }
        if (board[oldX][oldY].contains(CellModel.HORIZONTAL_DOOR) && (direction == Direction.LEFT || direction == Direction.RIGHT)) {
            return true;
        }
        if (board[oldX][oldY].contains(CellModel.VERTICAL_DOOR) && (direction == Direction.LEFT || direction == Direction.RIGHT)) {
            return false;
        }
        if (board[oldX][oldY].contains(CellModel.HORIZONTAL_DOOR) && (direction == Direction.UP || direction == Direction.DOWN)) {
            return false;
        }
        if (!(0 <= x && x < rows && 0 <= y && y < columns)) {
            return false;
        }
        if (board[x][y].contains(CellModel.HORIZONTAL_DOOR) && (direction == Direction.UP || direction == Direction.DOWN)) {
            return false;
        }
        if (board[x][y].contains(CellModel.VERTICAL_DOOR) && (direction == Direction.LEFT || direction == Direction.RIGHT)) {
            return false;
        }
        return !board[x][y].contains(CellModel.BLOCK);
    }

    public List<Object> getCellElements(int rowIndex, int columnIndex) {
        Point position = new Point(rowIndex, columnIndex);
        List<Object> elements = new LinkedList<>(ghosts.stream().filter(ghost -> ghost.getPosition().equals(position)).toList());
        if (pacman.getPosition() != null && pacman.getPosition().equals(position)) {
            if (elements.size() > 0) {
                if (gameStateListener != null) {
                    gameStateListener.finishGame();
                }
            }
            elements.add(pacman);
        }
        elements.add(getBoard()[rowIndex][columnIndex]);
        return elements;
    }

    public void move(int keyCode) {
        int x = pacman.getPosition().x;
        int y = pacman.getPosition().y;
        if (nextPosKeyboard.get(keyCode) != null) {
            Direction direction = nextPosKeyboard.get(keyCode);
            if (canMove(
                    x,
                    y,
                    x + directionX[nextPos.get(direction)],
                    y + directionY[nextPos.get(direction)],
                    direction
            )) {
                pacman.setDirection(direction);
                moveActor(pacman);
            }
        }
    }

    private void moveActor(ActorModel actor) {
        switch (actor.getDirection()) {
            case UP -> {
                actor.goUp();
            }
            case RIGHT -> {
                actor.goRight();
            }
            case DOWN -> {
                actor.goDown();
            }
            case LEFT -> {
                actor.goLeft();
            }
        }
        if (actor.getPosition().x == -1) {
            int column = IntStream
                    .range(0, columns - 1)
                    .filter(i -> board[rows - 1][i].contains(CellModel.VERTICAL_DOOR))
                    .toArray()[0];
            actor.setPosition(new Point(rows - 1, column));
        } else if (actor.getPosition().x == rows) {
            int column = IntStream
                    .range(0, columns - 1)
                    .filter(i -> board[0][i].contains(CellModel.VERTICAL_DOOR))
                    .toArray()[0];
            actor.setPosition(new Point(0, column));
        } else if (actor.getPosition().y == -1) {
            int row = IntStream
                    .range(0, rows - 1)
                    .filter(i -> board[i][columns - 1].contains(CellModel.HORIZONTAL_DOOR))
                    .toArray()[0];
            actor.setPosition(new Point(row, columns - 1));
        } else if (actor.getPosition().y == columns) {
            int row = IntStream
                    .range(0, rows - 1)
                    .filter(i -> board[i][0].contains(CellModel.HORIZONTAL_DOOR))
                    .toArray()[0];
            actor.setPosition(new Point(row, 0));
        }
        positionListener.dataChanged();
    }
}
