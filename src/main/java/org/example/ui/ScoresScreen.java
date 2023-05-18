package org.example.ui;

import org.example.controller.HighScoresController;
import org.example.domain.model.ScoreModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ScoresScreen extends JFrame {

    private final HighScoresController controller;

    public ScoresScreen() {
        super("High Scores");
        controller = new HighScoresController();
    }

    public void start() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        List<ScoreModel> scores = controller.getHighScores();

        DefaultTableModel scoreTableModel = new DefaultTableModel();
        scoreTableModel.addColumn("Name");
        scoreTableModel.addColumn("Score");
        scoreTableModel.addColumn("Time");

        scores.forEach(scoreModel ->
                scoreTableModel.addRow(
                        new Object[]{
                                scoreModel.getName(),
                                scoreModel.getScore(),
                                scoreModel.getTime()
                        }
                )
        );

        JTable table = new JTable(scoreTableModel);

        JScrollPane scoresPane = new JScrollPane(table);
        scoresPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scoresPane);

        setSize(new Dimension(500, 500));
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
