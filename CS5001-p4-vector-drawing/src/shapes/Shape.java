package shapes;

import base.Rect;

import java.awt.*;

/**
 * Created by un4 on 04/11/16.
 */
abstract public class Shape implements Drawable {

    private static final Color DEFAULT_COLOR = Color.BLUE;
    private Color color;
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
}
