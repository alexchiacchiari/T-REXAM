package com.it.unimol.exam.platform.animation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Resource {
    public static BufferedImage getImage(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Errore! Impossibile caricare le risorse.");
        }
        return image;
    }
}
