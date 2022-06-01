package com.it.unimol.exam.platform.app;

import com.it.unimol.exam.platform.gui.GameScreen;
import com.it.unimol.exam.platform.animation.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class EnemiesManager {
    private List<Enemy> enemies;
    private Cactus cactus;
    private Random random;
    private BufferedImage cactus1, cactus2;
    private MainPlayer player;
    private GameScreen gameScreen;

    public EnemiesManager(MainPlayer player, GameScreen gameScreen) {
        this.player = player;
        this.gameScreen = gameScreen;
        cactus1 = Resource.getImage("Files/cactus1.png");
        cactus2 = Resource.getImage("Files/cactus2.png");
        enemies = new ArrayList<Enemy>();
        random = new Random();
        enemies.add(getRandomCactus());
    }

    public void updateEnemies() {
        for(Enemy e : enemies) {
            e.updateCactus();
            if(e.playerIsOverCactus()) {
                gameScreen.setScore(1);
            }
            //GESTIONE DELLA COLLISIONE
            if(e.getRectangle().intersects(player.getRectangle())) {
                player.setAlive(false);
            }
        }

        Enemy firstEnemy = enemies.get(0);
        if(firstEnemy.cactusIsOutOfScreen()) {
            enemies.remove(firstEnemy);
            enemies.add(getRandomCactus());
        }

    }

    public void draw(Graphics g) {
        for(Enemy e: enemies) {
            e.draw(g);
        }
    }

    public void resetGame() {
        enemies.clear();
        enemies.add(getRandomCactus());
    }

    private Cactus getRandomCactus() {
        Cactus cactus = new Cactus(player);
        cactus.setPosX(600);
        if(random.nextBoolean()) {
            cactus.setImage(cactus1);
        } else {
            cactus.setImage(cactus2);
        }
        return cactus;
    }


}
