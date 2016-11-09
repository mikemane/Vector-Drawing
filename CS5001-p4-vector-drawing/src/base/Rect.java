package base;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by un4 on 08/11/16.
 */
public class Rect {
    private Point origin;
    private Point endPoint;
    private int width;
    private int height;


    public Rect(Point origin, int width, int height) {
        this.origin = origin;
        this.height = height;
        this.width = width;
    }

    public Rect(int x, int y, int width, int height) {
        this.origin = new Point(x, y);
        this.height = height;
        this.width = width;
    }

    public Rect(Point origin, Point endPoint) {
        this.endPoint = endPoint;
        this.origin = origin;
        this.setWidth((origin.x - endPoint.x));
        this.setHeight((origin.y - endPoint.y));
    }

    /**
     * Gets the width of the rect.
     *
     * @return
     */
    public Point getOrigin() {
        return origin;
    }

    /**
     * Gets the origin of the rect.
     *
     * @param origin the origin of the rect.
     */
    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    /**
     * Gets the width of the rect.
     *
     * @return the width of the  width.
     */
    public int getWidth() {
        return width;
    }


    /**
     * gets end point.
     *
     * @return
     */
    public Point getEndPoint() {
        return endPoint;
    }

    /**
     * Sets end point.
     *
     * @param endPoint
     */
    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    /**
     * Sets the width of the rect.
     *
     * @param width gets the width of the rectangle.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets the heigth of the Rect.
     *
     * @return the height of the rect.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the heigth iof the rect.
     *
     * @param heigth heigth to be set.
     */
    public void setHeight(int heigth) {
        this.height = height;
    }

    /**
     * Translate the origin of the shape.
     *
     * @param x x coordinates.
     * @param y y coordinates.
     */
    public void translate(int x, int y) {
        this.origin.translate(x, y);
    }

}
