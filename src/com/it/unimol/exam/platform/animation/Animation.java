package com.it.unimol.exam.platform.animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation {
    private List<BufferedImage> frames;
    private int frameCounter = 0;

    public Animation() {
        frames = new ArrayList<>();
    }

    public void updateFrame() {
        frameCounter++;
        if (frameCounter >= frames.size()) {
            frameCounter = 0;
        }
    }

    public void addFrame(BufferedImage frame) {
        frames.add(frame);
    }

    public BufferedImage getFrame() {
        if (frames.size() > 0) {
            return frames.get(frameCounter);
        }
        return null;
    }
}
