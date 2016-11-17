package shapes;

import base.Rect;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by un4 on 15/11/16.
 */
public class ImageShape extends Shape {

    private String path;
//    private transient BufferedImage image;

    /**
     * this is the image shape constructor.
     *
     * @param path this is the path of the image.
     */
    public ImageShape(String path) {
        try {
            this.path = path;
            BufferedImage image = ImageIO.read(new File(path));
            Point origin = new Point(0, 0);
            Point end = new Point(image.getWidth(), image.getHeight());
            this.rect = new Rect(origin, end);
            this.setImage(image);
            this.makeObject(origin, end);
            this.setAlpha(DEFAULT_ALPHA);
//            super(new Rect(origin, end));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void updateShape() {
//        try {
//            this.image = ImageIO.read(new File(this.path));
        this.makeObject(this.getRect().getOrigin(), getRect().getEndPoint());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    public ShapeType getShapeType() {
        return ShapeType.IMAGE;
    }

    /**
     * This makes the image at the two Point.
     *
     * @param start this is the start point.
     * @param end   this is the end point of the shape.
     */
    public void makeImage(Point start, Point end) {
        BufferedImage bufferedImage;
        int width = Math.abs(start.x - end.x);
        int height = Math.abs(start.x - end.x);
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics = bufferedImage.createGraphics();
        AffineTransform affineTransform = AffineTransform.getScaleInstance(width, height);
//        graphics.drawRenderedImage(image, affineTransform);
//        this.setImage(image);
    }


    /**
     * this sets the buffered Image.
     *
     * @param image this is the image to set.
     */
    public void setImage(BufferedImage image) {
//        this.image = image;
    }

    /**
     * creates a rectangle object.
     */
    public void makeObject(Point start, Point end) {
        java.awt.Rectangle r = new java.awt.Rectangle(Math.min(start.x, end.x), Math.min(start.y, end.y), Math.abs(start.x - end.x), Math.abs(start.y - end.y));
        this.setShape(r);
    }

    /**
     * this gets the buffered image.
     *
     * @return the requested image.
     */
    public BufferedImage getImage() {
//        return image;
        BufferedImage bufferedImage = null;
        try {
            BufferedImage image = ImageIO.read(new File(this.path));
            int width = Math.abs(getRect().getOrigin().x - getRect().getEndPoint().x);
            int height = Math.abs(getRect().getOrigin().x - getRect().getEndPoint().x);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D graphics = bufferedImage.createGraphics();
            AffineTransform affineTransform = AffineTransform.getScaleInstance(width, height);
            graphics.drawImage(image, 0, 0, null); //drawImage(image, affineTransform);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bufferedImage;
    }
}
