package shapes;

import base.Rect;

import java.awt.Point;
import java.awt.geom.Line2D;

/**
 * Created by un4 on 08/11/16.
 */
public class Line extends Shape {

    public Line(Rect rect){
        super(rect);
    }
    @Override
    public java.awt.Shape getShape() {
        Point origin = rect.getOrigin();
        Point end = rect.getEndPoint();
        return new Line2D.Float(origin, end);
    }
}
