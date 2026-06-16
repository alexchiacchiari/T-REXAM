package com.it.unimol.exam.platform.app;

import com.it.unimol.exam.platform.gui.GameScreen;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Cactus implements Enemy {
    private BufferedImage cactus;
    private int posX, posY;
    private final MainPlayer player;
    private final Rectangle rectangle;

    public Cactus(MainPlayer player) {
        this.player = player;
        posX = 200;
        rectangle = new Rectangle();
    }

    public void draw(Graphics g) {
        g.drawImage(cactus, posX, posY, null);
        g.drawRect(posX,
                posY,
                cactus.getWidth(),
                cactus.getHeight());  //rettangolo intorno al cactus*/
    }

    private static final int HITBOX_INSET = 5;

    public void updateCactus(int speed) {
        posX = posX - speed;
        rectangle.x = posX + HITBOX_INSET;
        rectangle.y = posY + HITBOX_INSET;
        rectangle.width = cactus.getWidth() - HITBOX_INSET * 2;
        rectangle.height = cactus.getHeight() - HITBOX_INSET * 2;
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

    public void setImage(BufferedImage image) {
        this.cactus = image;
        this.posY = GameScreen.FLOOR - image.getHeight();
    }
}
