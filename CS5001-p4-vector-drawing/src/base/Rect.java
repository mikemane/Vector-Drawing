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


    /**
     * This represented the bounds of the shape region.
     * @param origin this is the origin boundaries.
     * @param width this is width of the shape.
     * @param height this represents the height of the shape.
     */
    public Rect(Point origin, int width, int height) {
        this.origin = origin;
        this.height = height;
        this.width = width;
    }

    /**
     * this is  takes in a x and y position that denotes the origin and the width and height.
     * @param x the x coordinate of the origin.
     * @param y the y coordinate of the origin.
     * @param width the width of the shape.
     * @param height the height of the shape.
     */
    public Rect(int x, int y, int width, int height) {
        this.origin = new Point(x, y);
        this.height = height;
        this.width = width;
    }

    /**
     * this takes in a start and an end point.
     * @param origin this is the origin of the shape.
     * @param endPoint
     */
    public Rect(Point origin, Point endPoint) {
        this.endPoint = endPoint;
        this.origin = origin;
        int width = Math.abs(origin.x - endPoint.x);
        int height = Math.abs(origin.y - endPoint.y);
        this.setWidth(width);
        this.setHeight(height);
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
     * @param height heigth to be set.
     */
    public void setHeight(int height) {
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


    @Override
    public String toString() {
       return "origin x: " + getOrigin().x + " y:" + getOrigin().y + " width: " + getWidth() + " height: " + getHeight();
    }
}
