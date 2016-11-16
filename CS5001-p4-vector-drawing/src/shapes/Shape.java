package shapes;

import base.Rect;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by un4 on 04/11/16.
 */
abstract public class Shape implements Serializable, Cloneable {

    /**
     * Default stroke value if none is set.
     */
    public static int DEFAULT_STROKE = 5;
    public static float DEFAULT_ALPHA = 1;
    /**
     * Default strokeColor if none is set.
     */
    private static final Color DEFAULT_COLOR = Color.BLUE;
    private transient Color strokeColor;
    private int strokeWidth;
    protected Rect rect;
    protected java.awt.Shape shape;
    protected transient Color fillColor;
    protected float alpha;

    /**
     * Sets the strokeColor and shape of the shape.
     *
     * @param strokeColor this is the strokeColor to set in shape;
     * @param rect        the rectangle being passed in,
     */
    public Shape(Rect rect, Color strokeColor, boolean shouldFill) {
        this.rect = rect;
        this.strokeColor = strokeColor;
        this.alpha = DEFAULT_ALPHA;
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
     * This is a shape constructor that takes no parameter and sets the default strokeColor to blue.
     */
    public Shape() {
        this.strokeColor = DEFAULT_COLOR;
    }

    /**
     * Returns the strokeColor of the shape.
     *
     * @return the strokeColor of the shape.
     */
    public Color getStrokeColor() {
        return strokeColor;
    }

    /**
     * sets the stroke color.
     *
     * @param strokeColor the stroke color to set.
     */
    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
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
     * Checks if the shape contains the point,
     *
     * @param point the point to check.
     * @return
     */
    public boolean contains(Point point) {
        return this.getShape().contains(point);
    }

    /**
     * sets fill strokeColor of the shape.
     *
     * @param fillColor fill strokeColor to set.
     */
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    /**
     * gets fill strokeColor.
     *
     * @return the fill strokeColor of the shape.
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
     * this sets the alpha channel.
     *
     * @param alpha this is the alpha channel to this valuel
     *              .
     */
    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    /**
     * this gets the alpha of  the shape.
     *
     * @return this returns an int that denotes the alpha channel.
     */
    public float getAlpha() {
        return alpha;
    }

    /**
     * this does a movement of the shape.
     *
     * @param start the start move point.
     * @param end   coordinates to move towards.
     */
    public void move(Point start, Point end) {
        int newXPoint = getRect().getOrigin().x + end.x - start.x;
        int newYPoint = getRect().getOrigin().y + end.y - start.y;
        int newEXPoint = getRect().getEndPoint().x + end.x - start.x;
        int newEYPoint = getRect().getEndPoint().y + end.y - start.y;
        getRect().setOrigin(new Point(newXPoint, newYPoint));
        getRect().setEndPoint(new Point(newEXPoint, newEYPoint));
        this.updateShape();
    }

    /**
     * this creates a well formarted string that is readable by the user.
     *
     * @return this returns a string valut that will make it easier for a user to read.
     */
    @Override
    public String toString() {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getClass().getSimpleName()).append(";")
                .append(getShape().getBounds().x).append(";")
                .append(getShape().getBounds().y).append(";")
                .append(getShape().getBounds().width).append(";")
                .append(getShape().getBounds().height).append(";")
                .append(getStrokeColor().getRed()).append(";")
                .append(getStrokeColor().getGreen()).append(";")
                .append(getStrokeColor().getBlue()).append(";");

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

    /**
     * Updates the shape when an action has been done to the shape.
     */
    public abstract void updateShape();

    /**
     * This just clones the shape and returns the shape.
     *
     * @return this is the return of the clone.
     */
    public Shape clone() {
        try {
            Shape clone = (Shape) super.clone();
            return clone;
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
