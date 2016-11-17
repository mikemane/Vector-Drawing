package shapes;

import base.Rect;
import javafx.scene.transform.Affine;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.*;

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
    public static final Color DEFAULT_COLOR = Color.WHITE;
    public static final Color DEFAULT_STROKE_COLOR = Color.BLACK;
    private Color strokeColor;
    private int strokeWidth;
    protected Rect rect;
    protected java.awt.Shape shape;
    protected Color fillColor;
    protected float alpha;
    protected AffineTransform transform;

    /**
     * Sets the strokeColor and shape of the shape.
     *
     * @param strokeColor this is the strokeColor to set in shape;
     * @param rect        the rectangle being passed in,
     */
    public Shape(Rect rect, Color strokeColor) {
        this.rect = rect;
        this.strokeColor = strokeColor;
        this.alpha = DEFAULT_ALPHA;
        this.transform = new AffineTransform();
//        this.transform.scale(2, 2);
//        this.transform.rotate(Math.toRadians(45));
    }

    /**
     * the shape constructor that sets the shape shapes default parameters.
     *
     * @param rect the rectangle of the shape boundaries
     */
    public Shape(Rect rect) {
        this(rect, DEFAULT_COLOR);
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
     * this creates a well formatted string that is readable by the user.
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

    /**
     * Performs a deep copy.
     *
     * @return the deep copy of the object.
     */
    public Shape deepCopy() {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Shape shapeCopy = null;
        try {
            // deep copy
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            // serialize and pass the object
            oos.writeObject(this);
            oos.flush();
            ByteArrayInputStream bin =
                    new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bin);
            // return the new object
            shapeCopy = (Shape) ois.readObject();
            shapeCopy.setFillColor(this.fillColor);
            shapeCopy.setStrokeColor(this.strokeColor);
            oos.close();
            oos.close();
        } catch (IOException e) {
            System.out.println("Exception in main = " + e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return shapeCopy;
    }

    public void rotate(double theta) {
        this.transform.rotate(theta);
    }

    private boolean isPositive(double number) {
        return (number >= 0);
    }

    public void scale(int x, int y) {
        int newEXPoint = getRect().getEndPoint().x + x; //end.x - start.x;
        int newEYPoint = getRect().getEndPoint().y + y; //end.y - start.y;
        getRect().setEndPoint(new Point(newEXPoint, newEYPoint));
        this.updateShape();
    }

    public void setTransform(AffineTransform transform) {
        this.transform = transform;
    }

    public AffineTransform getTransform() {
        return transform;
    }

    /**
     * This gets the shape type of the shape.
     *
     * @return this returns the shape type of the shape.
     */
    abstract public ShapeType getShapeType();
}