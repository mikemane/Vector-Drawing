package shapes;

import base.Rect;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by un4 on 04/11/16.
 */
public class Ellipse extends Shape {

    private Ellipse2D ellipse2D;

    public Ellipse(Rect rect) {
        super(rect);
    }

    @Override
    public void updateShape() {
        Point origin = rect.getOrigin();
        Point endPoint = rect.getEndPoint();
        this.ellipse2D = new Ellipse2D.Float(Math.min(origin.x, endPoint.x), Math.min(origin.y, endPoint.y), Math.abs(origin.x - endPoint.x), Math.abs(origin.y - endPoint.y));
    }

    public void setEllipse2D(Ellipse2D ellipse2D) {
        this.ellipse2D = ellipse2D;
    }

    @Override
    public java.awt.Shape getShape() {
        return this.ellipse2D;
    }


    /**
     * @param point
     * @return
     */
    @Override
    public boolean contains(Point point) {
        return this.ellipse2D.contains(point);
    }


}
