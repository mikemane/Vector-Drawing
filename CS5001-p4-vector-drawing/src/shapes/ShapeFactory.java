package shapes;

import base.Rect;

import java.awt.*;

/**
 * Created by un4 on 04/11/16.
 */
public class ShapeFactory {

    /**
     * This factory takes a shape type as a parameter and returns a shape type.
     *
     * @param shapeType the type of shape to return.
     * @return
     */
    public shapes.Shape getShape(ShapeType shapeType, Rect rect) {
        switch (shapeType) {
            case ELLIPSE:
                return new Ellipse(rect);
            case RECTANGLE:
                return new shapes.Rectangle(rect);

        }
        return null;
    }

    public shapes.Shape getLine(Point start, Point end) {
        return new Line(start, end);
    }

}
