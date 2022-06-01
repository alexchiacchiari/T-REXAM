package com.it.unimol.exam.platform.app;

import com.it.unimol.exam.platform.animation.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Cactus extends Enemy {
    private BufferedImage cactus;
    private int posX, posY;
    private MainPlayer player;
    private Rectangle rectangle;

    public Cactus(MainPlayer player) {
        this.player = player;
        cactus = Resource.getImage("Files/cactus1.png");
        posX = 200;
        posY = 65;
        rectangle = new Rectangle();
    }

    public void draw(Graphics g) {
        g.drawImage(cactus, posX, posY, null);
        rectangle.x = posX;
        rectangle.y = posY;
        rectangle.width = cactus.getWidth();
        rectangle.height = cactus.getHeight();
    }

    public void updateCactus() {
        posX = posX - 2;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public boolean cactusIsOutOfScreen() {
        return (posX + cactus.getWidth() < 0);
    }

    public boolean playerIsOverCactus() {
        return (player.getX() > getPosX());
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setImage(BufferedImage image) {
        this.cactus = image;
    }
}
