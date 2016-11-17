package utils;

import shapes.*;
import shapes.Shape;
import view.canvas.PaintCanvas;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
            ArrayList<shapes.Shape> shapes = (ArrayList<Shape>) paintCanvas.getShapeModel().getShapeList().clone();
            shapes.forEach(shape -> {
                if (shape.getShapeType() != ShapeType.IMAGE) {
                    saveGraphics.setStroke(new BasicStroke(shape.getStrokeWidth()));
                    saveGraphics.setPaint(shape.getStrokeColor());
                    saveGraphics.draw(shape.getShape());
                    if (shape.getFillColor() != null) {
                        saveGraphics.setColor(shape.getFillColor());
                        saveGraphics.fill(shape.getShape());
                    }
                } else {
                    ImageShape imageShape = (ImageShape) shape;
                    Point a = shape.getRect().getOrigin();
                    Point b = shape.getRect().getEndPoint();
                    saveGraphics.drawImage(imageShape.getImage(), Math.min(a.x, a.x), Math.min(a.y, b.y), null);                }
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
