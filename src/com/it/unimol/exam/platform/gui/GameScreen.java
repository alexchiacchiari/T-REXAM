package com.it.unimol.exam.platform.gui;

import com.it.unimol.exam.platform.app.EnemiesManager;
import com.it.unimol.exam.platform.app.MainPlayer;
import com.it.unimol.exam.platform.animation.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.concurrent.locks.LockSupport;

/**
 * Questa classe rappresenta il JPanel che viene aggiunto al JFrame di gioco (extends JPanel)
 * Viene dichiarato e implementato il thread che gestisce l'intero motore di gioco, e lo fa attravero
 * i vari metodi update() presenti nelle varie classi cardine
 * Questa classe gestisce l'intera grafica di gioco, perché si occupa di richiamare il metodo
 * {@link #startGame()}}
 * @author Alex Chiacchiari
 * */

public class GameScreen extends JPanel {
    public static final int GAME_FIRST_STATE = 0;
    public static final int GAME_PLAY_STATE = 1;
    public static final int GAME_OVER_STATE = 2;
    private int gameState = GAME_FIRST_STATE;
    public static final float GRAVITY = 0.1f;
    public static final int FLOOR = 110;
    private Thread gameThread;
    private MainPlayer player;
    private Land land;
    private EnemiesManager enemiesManager;
    private BufferedImage gameOverImage;
    private int score = 0;

    public GameScreen() {
        land = new Land();
        player = new MainPlayer();
        player.setX(50);
        player.setY(60);
        enemiesManager = new EnemiesManager(player, this);
        gameOverImage = Resource.getImage("Files/gameover_text.png");
    }

    private static final long FRAME_TIME_NS = 10_000_000L; // 10ms = 100 FPS
    private static final Color BG_COLOR = new Color(0xf7f7f7);

    public void startGame() {
        gameThread = new Thread(() -> {
            long nextFrame = System.nanoTime();
            while (true) {
                update();
                repaint();
                nextFrame += FRAME_TIME_NS;
                long remaining = nextFrame - System.nanoTime();
                if (remaining > 0) {
                    LockSupport.parkNanos(remaining);
                }
            }
        });
        gameThread.setDaemon(true);
        gameThread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
        //g.drawLine(0, FLOOR, getWidth(), FLOOR);  //SOLO PER ORIENTAMENTO: TERRENO

        switch (gameState) {
            case GAME_FIRST_STATE:
                player.draw(g);
                break;
            case GAME_PLAY_STATE:
                land.draw(g);
                player.draw(g);
                enemiesManager.draw(g);
                g.drawString("SCORE: " + score, getWidth() - 90, 20);
                break;
            case GAME_OVER_STATE:
                land.draw(g);
                player.draw(g);
                enemiesManager.draw(g);
                g.drawImage(gameOverImage,
                        getWidth()/2 - (gameOverImage.getWidth()/2),
                        getHeight()/2,
                        null);
                g.setColor(Color.red);
                g.drawString("PREMI LA SPACEBAR PER GIOCARE...",
                                    getWidth()/2 - (gameOverImage.getWidth()/2),
                                    getHeight()/2 + 35);
                break;
        }
        Toolkit.getDefaultToolkit().sync();
    }

    public void setScore(int score) {
        this.score += score;
    }

    public class PlayerController implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP) {
                player.jump();
            }

            if (key == KeyEvent.VK_X) {
                System.exit(0);
            }
        }

        @Override
        public void keyTyped(KeyEvent e) { }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_SPACE) {
                if (gameState == GAME_FIRST_STATE) {
                    gameState = GAME_PLAY_STATE;
                } else if (gameState == GAME_PLAY_STATE) {
                    player.jump();
                } else if (gameState == GAME_OVER_STATE) {
                    resetGame();
                    gameState = GAME_PLAY_STATE;
                }
            }

        }
    }

    private void update() {
        if (gameState == GAME_PLAY_STATE) {
            player.update();
            land.updateLand();
            enemiesManager.updateEnemies();
            if (!player.getAlive()) {
                gameState = GAME_OVER_STATE;
            }
        }
    }

    private void resetGame() {
        player.setAlive(true);
        player.setX(50);
        player.setY(60);
        enemiesManager.resetGame();
        this.score = 0;
    }

}
