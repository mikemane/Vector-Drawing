package shapes;

import base.Rect;

import java.awt.Point;
import java.awt.geom.Line2D;

/**
 * Created by un4 on 08/11/16.
 */
public class Line extends Shape {

    private Line2D line;

    /**
     * Line constructor.
     *
     * @param rect
     */
    public Line(Rect rect) {
        super(rect);
    }

    @Override
    public java.awt.Shape getShape() {
        Point origin = rect.getOrigin();
        Point end = rect.getEndPoint();
        return new Line2D.Float(origin, end);
    }

    @Override
    public boolean contains(Point point) {
        return false;
    }

    @Override
    public void updateShape() {
        Point origin = rect.getOrigin();
        Point end = rect.getEndPoint();
        this.line = new Line2D.Float(origin, end);
    }
}
