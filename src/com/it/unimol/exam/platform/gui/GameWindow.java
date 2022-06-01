package com.it.unimol.exam.platform.gui;

import javax.swing.*;

/**
 *Finestra principale di gioco,
 *che ha il metodo {@link #startGame()} che permette di avviare il thread di gameScreen nel main
 *@author Alex Chiacchiari
 * */

public class GameWindow extends JFrame {
    private GameScreen gameScreen = new GameScreen();
    private GameScreen.PlayerController playerController = gameScreen.new PlayerController();
    private static final int SCREEN_WIDTH = 600;

    public GameWindow() {
        super("T - REXAM");
        prepareGui();
        add(gameScreen);
        addKeyListener(playerController);
    }

    public void startGame() {
        gameScreen.startGame();
    }

    public void prepareGui() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(SCREEN_WIDTH, 175);
        setResizable(false);
        setLocation(400, 200);
    }


}

