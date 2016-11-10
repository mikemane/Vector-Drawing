package shapes;

import base.Rect;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by un4 on 04/11/16.
 */
public class Rectangle extends Shape {

    public Rectangle(Rect rect) {
        super(rect);
    }


    @Override
    public java.awt.Shape getShape() {
//        return new Rectangle2D.Float(rect.getOrigin().x, rect.getOrigin().y, rect.getWidth(), rect.getHeight());

        Point origin = rect.getOrigin();
        Point endPoint = rect.getEndPoint();
        return new Rectangle2D.Float(Math.min(origin.x, endPoint.x), Math.min(origin.y, endPoint.y), Math.abs(origin.x - endPoint.x), Math.abs(origin.y - endPoint.y));
//        return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }
}
