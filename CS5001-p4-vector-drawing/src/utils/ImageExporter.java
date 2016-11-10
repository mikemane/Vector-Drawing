package utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by un4 on 10/11/16.
 */
public class ImageExporter {

    public enum Filetype {
        JPG, BMP, PNG;

        public String extension() {
            switch (this) {
                case JPG:
                    return "jpg";
                case BMP:
                    return "bmp";
                case PNG:
                    return "png";
            }
            return null;
        }
    }

    public static BufferedImage createImage(JComponent panel) {
        int w = panel.getWidth();
        int h = panel.getHeight();
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        panel.paint(g);
        return bi;
    }

    public static void save(BufferedImage image, String fileName, Filetype ext) {
        File file = new File(fileName + "." + ext.extension());
        try {
            ImageIO.write(image, ext.extension(), file);  // ignore returned boolean
        } catch (IOException e) {
            System.out.println("Write error for " + file.getPath() +
                    ": " + e.getMessage());
        }
    }
}
