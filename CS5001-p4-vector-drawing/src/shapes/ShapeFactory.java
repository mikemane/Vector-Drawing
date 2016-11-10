package shapes;

import base.Rect;
import org.junit.Before;

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
    public shapes.Shape getShape(ShapeType shapeType, Rect rect, Color color) {
        Shape shape = null;
        switch (shapeType) {
            case ELLIPSE:
                shape = new Ellipse(rect);
                break;
            case RECTANGLE:
                shape = new shapes.Rectangle(rect);
                break;
            case LINE:
                shape = new shapes.Line(rect);
                break;
            case HEXAGON:
                shape = new Hexagon(rect);
                break;
        }
        shape.setColor(color);
        return shape;
    }
}
