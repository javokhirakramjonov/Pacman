package org.example.domain.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    private final int BLOCK_SIZE = 24;
    private final int MAX_GHOSTS_COUNT;

    private final PacmanModel pacman;
    private final List<GhostModel> ghosts;
    private final int ghostsCount;


    public Repository(Dimension dimension) {
        ghostsCount = dimension.height * dimension.width / 10;
        MAX_GHOSTS_COUNT = ghostsCount * 2;
        pacman = new PacmanModel();
        ghosts = new ArrayList<>();
    }
}
