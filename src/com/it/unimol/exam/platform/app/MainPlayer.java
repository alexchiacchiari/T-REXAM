package com.it.unimol.exam.platform.app;

import com.it.unimol.exam.platform.animation.Animation;
import com.it.unimol.exam.platform.animation.Resource;
import java.awt.*;
import static com.it.unimol.exam.platform.gui.GameScreen.FLOOR;
import static com.it.unimol.exam.platform.gui.GameScreen.GRAVITY;

public class MainPlayer {
    private int x = 0;
    private int y = 0;
    private float speedY = 0;
    private Animation playerRunImage;
    private Rectangle rectangle;
    private boolean isAlive = true;

    public MainPlayer() {
        playerRunImage = new Animation();
        rectangle = new Rectangle();
        allFrames();
    }


    public void update() {
        playerRunImage.updateFrame();

        int playerHeight = playerRunImage.getFrame().getHeight();
        if (y >= FLOOR - playerHeight) {
            speedY = 0;
            y = FLOOR - playerHeight;
        } else {
            y = (int) (y + speedY);
            speedY = speedY + GRAVITY;
        }
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = playerRunImage.getFrame().getWidth();
        rectangle.height = playerRunImage.getFrame().getHeight();
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        //g.drawRect( x,
        //            y,
        //            playerRunImage.getFrame().getWidth(),
        //            playerRunImage.getFrame().getHeight());  //rettangolo intorno al personaggio

        g.drawImage(playerRunImage.getFrame(), x, y, null);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void jump() {
         speedY = -3;
        y = (int) (y + speedY);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean getAlive() {
        return this.isAlive;
    }

    private void allFrames() {
        playerRunImage.addFrame(Resource.getImage("Files/main-character1.png"));
        playerRunImage.addFrame(Resource.getImage("Files/main-character2.png"));
    }


}
