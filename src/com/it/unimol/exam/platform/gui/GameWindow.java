package com.it.unimol.exam.platform.gui;

import javax.swing.*;

/**
 *Finestra principale di gioco,
 *che ha il metodo {@link #startGame()} che permette di avviare il thread di gameScreen nel main
 *@author Alex Chiacchiari
 * */

public class GameWindow extends JFrame {
    private final GameScreen gameScreen = new GameScreen();
    private static final int SCREEN_WIDTH = 600;

    public GameWindow() {
        super("T-REXAM");
        prepareGui();
        add(gameScreen);
        GameScreen.PlayerController playerController = gameScreen.new PlayerController();
        addKeyListener(playerController);
        setVisible(true);
    }

    public void startGame() {
        gameScreen.startGame();
    }

    public void prepareGui() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(SCREEN_WIDTH, 175);
        setResizable(false);
        setLocation(400, 200);
    }


}

