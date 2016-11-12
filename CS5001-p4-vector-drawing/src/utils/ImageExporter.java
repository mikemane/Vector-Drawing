package utils;

import view.canvas.PaintCanvas;

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

    /**
     * List Of all filetypes.
     */
    public enum Filetype {
        JPG, BMP, PNG, SHP;

        public String extension() {
            switch (this) {
                case JPG:
                    return "jpg";
                case BMP:
                    return "bmp";
                case PNG:
                    return "png";
                case SHP:
                    return "shp";
            }
            return null;
        }
    }

    /**
     * this takes in a paintCanvas and export file.
     *
     * @param paintCanvas the paintCanvas to export.
     * @return true if it is successful.
     */
    public static boolean export(PaintCanvas paintCanvas) {
        JFileChooser fileChooser = new JFileChooser();
        int saveAFile = fileChooser.showSaveDialog(fileChooser);
        if (saveAFile == JFileChooser.APPROVE_OPTION) {
            BufferedImage bufferedImage = new BufferedImage(paintCanvas.getSize().width, paintCanvas.getSize().height, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D saveGraphics = bufferedImage.createGraphics();
            saveGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            saveGraphics.setColor(Color.white);
            saveGraphics.fillRect(0, 0, paintCanvas.getSize().width, paintCanvas.getSize().height);
            paintCanvas.getShapeModel().getShapeStack().forEach(shape -> {
                saveGraphics.setStroke(new BasicStroke(shape.getStrokeWidth()));
                saveGraphics.setPaint(shape.getColor());
                saveGraphics.draw(shape.getShape());
                if (shape.getFillColor() != null) {
                    saveGraphics.setColor(shape.getFillColor());
                    saveGraphics.fill(shape.getShape());
                }
            });
            File file = fileChooser.getSelectedFile();
            return save(bufferedImage, file.toString(), ImageExporter.Filetype.PNG);
        }
        return false;
    }


    public static boolean save(BufferedImage image, String fileName, Filetype ext) {
        File file = new File(fileName + "." + ext.extension());
        try {
            ImageIO.write(image, ext.extension(), file);  // ignore returned boolean
            return true;
        } catch (IOException e) {
            System.out.println("Write error for " + file.getPath() +
                    ": " + e.getMessage());
        }
        return false;
    }
}
