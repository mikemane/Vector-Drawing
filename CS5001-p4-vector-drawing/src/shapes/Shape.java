package shapes;

import base.Rect;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created by un4 on 04/11/16.
 */
abstract public class Shape implements Movable, Savable {

    /**
     * Default stroke value if none is set.
     */
    public static int DEFAULT_STROKE = 5;
    /**
     * Default color if none is set.
     */
    private static final Color DEFAULT_COLOR = Color.BLUE;
    private Color color;
    private Color strokeColor;
    private int strokeWidth;
    protected Rect rect;
    protected java.awt.Shape shape;
    protected Color fillColor;

    /**
     * Sets the color and shape of the shape.
     *
     * @param color
     * @param rect
     */
    public Shape(Rect rect, Color color, boolean shouldFill) {
        this.rect = rect;
        this.color = color;
    }

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
     * sets the stroke color.
     *
     * @param strokeColor the stroke color.
     */
    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    /**
     * Gets Stroke Color.
     *
     * @return get stroke color.
     */
    public Color getStrokeColor() {
        return strokeColor;
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
    @Override
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
     * @return
     */
    public Rect getRect() {
        return rect;
    }

    @Override
    public void writeToFile(BufferedWriter bufferedWriter) {
        try {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(getClass().getSimpleName()).append(";")
                    .append(getShape().getBounds().x).append(";")
                    .append(getShape().getBounds().y).append(";")
                    .append(getShape().getBounds().width).append(";")
                    .append(getShape().getBounds().height).append(";")
                    .append(getColor().getRed()).append(";")
                    .append(getColor().getGreen()).append(";")
                    .append(getColor().getBlue()).append(";");

            if (strokeColor != null) {
                stringbuilder.append(getStrokeColor().getBlue()).append(";")
                        .append(getStrokeColor().getGreen()).append(";")
                        .append(getStrokeColor().getBlue());
            } else {
                stringbuilder.append("null").append(";")
                        .append("null").append(";")
                        .append("null");
            }
            bufferedWriter.write(stringbuilder.toString());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
