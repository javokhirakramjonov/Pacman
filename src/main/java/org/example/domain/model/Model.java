package org.example.domain.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Model extends JPanel implements ActionListener, Observer<KeyEvent> {

    private final Dimension dimension;
    private final Font font14 = new Font("Serif", Font.BOLD, 14);

    private final int BLOCK_SIZE = 24;
    private final int MAX_GHOSTS_COUNT;

    private final PacmanModel pacman;
    private final List<GhostModel> ghosts;
    private final int ghostsCount;


    public Model(Dimension newDimension) {
        dimension = newDimension;
        ghostsCount = dimension.height * dimension.width / 10;
        MAX_GHOSTS_COUNT = ghostsCount * 2;
        pacman = new PacmanModel();
        ghosts = new ArrayList<>();

        KeyEventHandler.getInstance().register(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void observe(KeyEvent data) {

    }
}
