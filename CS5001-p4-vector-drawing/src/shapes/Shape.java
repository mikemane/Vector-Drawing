package shapes;

import base.Rect;

import java.awt.*;

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

    /**
     * Sets the color and shape of the shape.
     *
     * @param color
     * @param rect
     */
    public Shape(Rect rect, Color color) {
        this.rect = rect;
        this.color = color;
    }

    public Shape(Rect rect) {
        this(rect, DEFAULT_COLOR);
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
     * @param strokeWidth desired stroke width.
     */
    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    /**
     * Gets thr desired stroke width.
     * @return the stroke width.
     */
    public int getStrokeWidth() {
        return strokeWidth;
    }


    public void setColor(Color color) {
        this.color = color;
    }
}
