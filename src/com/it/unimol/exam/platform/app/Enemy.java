package com.it.unimol.exam.platform.app;

import java.awt.*;

public abstract class Enemy {
    public abstract Rectangle getRectangle();
    public abstract void draw(Graphics g);
    public abstract void updateCactus();
    public abstract boolean cactusIsOutOfScreen();
    public abstract boolean playerIsOverCactus();
}
