package org.example.ui;

import org.example.controller.HighScoresController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ScoresScreen extends JFrame {

    private final HighScoresController controller;

    public ScoresScreen() {
        super("High Scores");
        controller = new HighScoresController();
    }

    public void start() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                new MainScreen().start();
            }
        });

        DefaultTableModel scoreTableModel = new DefaultTableModel();
        scoreTableModel.addColumn("Name");
        scoreTableModel.addColumn("Score");
        scoreTableModel.addColumn("Time");

        controller.getHighScores().forEach(scoreModel ->
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

        JButton clearButton = new JButton("Clear list");
        clearButton.addActionListener(e -> {
            controller.clear();
            scoreTableModel.setRowCount(0);
            scoreTableModel.fireTableDataChanged();
        });

        setLayout(new BorderLayout());

        add(clearButton, BorderLayout.NORTH);
        add(scoresPane, BorderLayout.CENTER);

        setSize(new Dimension(500, 500));
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
