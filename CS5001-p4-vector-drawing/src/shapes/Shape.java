package shapes;

import base.Rect;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by un4 on 04/11/16.
 */
abstract public class Shape implements Serializable {

    /**
     * Default stroke value if none is set.
     */
    public static int DEFAULT_STROKE = 5;
    /**
     * Default color if none is set.
     */
    private static final Color DEFAULT_COLOR = Color.BLUE;
    private transient Color color;
    private int strokeWidth;
    protected Rect rect;
    protected java.awt.Shape shape;
    protected transient Color fillColor;

    /**
     * Sets the color and shape of the shape.
     *
     * @param color this is the color to set in shape;
     * @param rect  the rectangle being passed in,
     */
    public Shape(Rect rect, Color color, boolean shouldFill) {
        this.rect = rect;
        this.color = color;
    }

    /**
     * the shape cinstructor that sers the shape to the default type.
     *
     * @param rect the rectangle of the shape boundaries
     */
    public Shape(Rect rect) {
        this(rect, DEFAULT_COLOR, false);
    }

    /**
     * This is a shape constructor that takes no parameter and sets the default color to blue.
     */
    public Shape() {
        this.color = DEFAULT_COLOR;
    }

    /**
     * Returns the color of the shape.
     *
     * @return the color of the shape.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the desired stroke width.
     *
     * @param strokeWidth desired stroke width.
     */
    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    /**
     * Gets thr desired stroke width.
     *
     * @return the stroke width.
     */
    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * sets the shape.
     *
     * @param shape the shape to set.
     */
    public void setShape(java.awt.Shape shape) {
        this.shape = shape;
    }

    /**
     * returns the shape.
     *
     * @return shape to return.
     */
    public java.awt.Shape getShape() {
        return shape;
    }

    /**
     * Does a translation.
     *
     * @param start start coordinates.
     * @param end   end coordinates.
     */
    public abstract void move(Point start, Point end);

    /**
     * Checks if the shape contains the point,
     *
     * @param point the point to check.
     * @return
     */
    public boolean contains(Point point) {
        return this.getShape().contains(point);
    }

    /**
     * sets fill color of the shape.
     *
     * @param fillColor fill color to set.
     */
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    /**
     * gets fill color.
     *
     * @return the fill color of the shape.
     */
    public Color getFillColor() {
        return fillColor;
    }

    /**
     * returns the rect.
     *
     * @return the rectangle boundaries of the shape.
     */
    public Rect getRect() {
        return rect;
    }


    /**
     * serializale;
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getClass().getSimpleName()).append(";")
                .append(getShape().getBounds().x).append(";")
                .append(getShape().getBounds().y).append(";")
                .append(getShape().getBounds().width).append(";")
                .append(getShape().getBounds().height).append(";")
                .append(getColor().getRed()).append(";")
                .append(getColor().getGreen()).append(";")
                .append(getColor().getBlue()).append(";");

        if (fillColor != null) {
            stringbuilder.append(getFillColor().getBlue()).append(";")
                    .append(getFillColor().getGreen()).append(";")
                    .append(getFillColor().getBlue());
        } else {
            stringbuilder.append("null").append(";")
                    .append("null").append(";")
                    .append("null");
        }
        return stringbuilder.toString();
    }
}
