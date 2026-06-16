package com.it.unimol.exam.platform.gui;

import com.it.unimol.exam.platform.animation.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class Land {
    private final List<ImageLand> listImage;
    private final BufferedImage land1;
    private final BufferedImage land2;
    private final BufferedImage land3;
    private final Random random;

    public Land() {
        land1 = Resource.getImage("resources/land-1.png");
        land2 = Resource.getImage("resources/land-2.png");
        land3 = Resource.getImage("resources/land-3.png");
        random = new Random();
        listImage = new ArrayList<ImageLand>();
        int numberOfLand = GameWindow.SCREEN_WIDTH / land1.getWidth() + 2;

        for (int i = 0; i < numberOfLand; i++) {
            ImageLand imageLand = new ImageLand();
            imageLand.setPosX(i * land1.getWidth());
            imageLand.setImage(getImageLand());
            listImage.add(imageLand);
        }
    }

    public void draw(Graphics g) {  //disegno dei monti
        for (ImageLand imageLand : listImage) {
            g.drawImage(imageLand.getImage(),
                        imageLand.getPosX(),
                     GameScreen.FLOOR - 14,
                    null);
        }
    }

    public void updateLand(int speed) {
        int landSpeed = Math.max(1, speed / 2);
        for (ImageLand imageLand : listImage) {
            imageLand.setPosX(imageLand.getPosX() - landSpeed);
        }

        ImageLand firstElement = listImage.get(0);
        if (firstElement.getPosX() + land1.getWidth() < 0) {
            firstElement.setPosX(
                    listImage.get(listImage.size() - 1).getPosX() //posizione dell'ultimo elemento
                    + land1.getWidth());
            listImage.add(firstElement);
            listImage.remove(0);
        }
    }

    public BufferedImage getImageLand() {
        int i = random.nextInt(3);
        switch(i) {
            case 0:
                return land1;
            case 1:
                return land2;
            default:
                return land3;
        }
    }

    public class ImageLand {
        private int posX;
        private BufferedImage image;

        public void setPosX(int width) {
            this.posX = width;
        }

        public int getPosX() {
            return this.posX;
        }

        public void setImage(BufferedImage image) {
            this.image = image;
        }

        public BufferedImage getImage() {
            return this.image;
        }
    }
}
