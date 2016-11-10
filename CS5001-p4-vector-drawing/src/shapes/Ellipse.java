package shapes;

import base.Rect;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by un4 on 04/11/16.
 */
public class Ellipse extends Shape {

    public Ellipse(Rect rect){
        super(rect);
    }
    @Override
    public java.awt.Shape getShape() {
        Point origin = rect.getOrigin();
        Point endPoint = rect.getEndPoint();
        return new Ellipse2D.Float(Math.min(origin.x, endPoint.x), Math.min(origin.y, endPoint.y), Math.abs(origin.x - endPoint.x), Math.abs(origin.y - endPoint.y));
    }
}
