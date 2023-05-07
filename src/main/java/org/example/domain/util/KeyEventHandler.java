package org.example.domain.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class KeyEventHandler extends KeyAdapter {

    private static KeyEventHandler instance;
    private final List<Observer<KeyEvent>> observers = new ArrayList<>();

    private KeyEventHandler() {
    }

    public static KeyEventHandler getInstance() {
        if (instance == null) {
            instance = new KeyEventHandler();
        }
        return instance;
    }

    public void register(Observer<KeyEvent> observer) {
        observers.add(observer);
    }

    public void unRegister(Observer<KeyEvent> observer) {
        observers.remove(observer);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (Observer<KeyEvent> observer : observers) {
            observer.observe(e);
        }
    }
}
