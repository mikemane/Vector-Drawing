package shapes;

import base.Rect;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by un4 on 04/11/16.
 */
abstract public class Shape implements Drawable {

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
    protected Shape shape;
    protected boolean shouldFill;
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
        this.shouldFill = shouldFill;
        updateShape();
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

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    /**
     * should fill the shape.
     *
     * @param shouldFill should fill.
     */
    public void setShouldFill(boolean shouldFill) {
        this.shouldFill = shouldFill;
    }

    /**
     * should fill the shape.
     *
     * @return should fill.
     */
    public boolean isShouldFill() {
        return shouldFill;
    }

    /**
     * Does a translation.
     *
     * @param start start coordinates.
     * @param end   end coordinates.
     */
    public void move(Point start, Point end) {

        this.rect.setOrigin(new Point((end.x - start.x), this.rect.getOrigin().y + (end.y - start.y)));
        this.rect.setHeight(this.rect.getWidth());
        this.rect.setHeight(this.rect.getHeight());
//        this.getRect().x + (endDrag.x - startDrag.x), this.getRect().y + (endDrag.y - startDrag.y), this.getRect().width, this.getRect().height)
//        this.updateShape();
    }

}
