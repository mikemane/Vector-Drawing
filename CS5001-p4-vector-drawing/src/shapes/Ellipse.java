package shapes;

import base.Rect;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by un4 on 04/11/16.
 */
public class Ellipse extends Shape {


    /**
     * This is the ellipse class denoted by the rect bounds.
     *
     * @param rect the rect bounds.
     */
    public Ellipse(Rect rect) {
        super(rect);
        if (rect.getEndPoint() == null) {
            this.updateShape();
        } else {
            makeShape(rect.getOrigin(), rect.getEndPoint());
        }
    }

    /**
     * This makes the shape based on the origin and end point.
     *
     * @param origin   this is the origin point.
     * @param endPoint this is the end point.
     */
    public void makeShape(Point origin, Point endPoint) {
        Ellipse2D ellipse2D = new Ellipse2D.Float(Math.min(origin.x, endPoint.x), Math.min(origin.y, endPoint.y), Math.abs(origin.x - endPoint.x), Math.abs(origin.y - endPoint.y));
        this.setShape(ellipse2D);
    }


    /**
     * this updates the shape channel,
     */
    @Override
    public void updateShape() {
        this.makeShape(getRect().getOrigin(), getRect().getEndPoint());
    }
}
