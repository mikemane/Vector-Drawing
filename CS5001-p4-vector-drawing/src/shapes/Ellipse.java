package shapes;

import base.Rect;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by un4 on 04/11/16.
 */
public class Ellipse extends Shape {


    public Ellipse(Rect rect) {
        super(rect);
        makeShape(rect.getOrigin(), rect.getEndPoint());
    }

    public void makeShape(Point origin, Point endPoint) {
        Ellipse2D ellipse2D = new Ellipse2D.Float(Math.min(origin.x, endPoint.x), Math.min(origin.y, endPoint.y), Math.abs(origin.x - endPoint.x), Math.abs(origin.y - endPoint.y));
        this.setShape(ellipse2D);
    }

    @Override
    public void move(Point start, Point end) {

    }
}
