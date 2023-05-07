package org.example;

import org.example.ui.MainScreen;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainScreen mainScreen = new MainScreen();
            mainScreen.start();
        });
    }
}