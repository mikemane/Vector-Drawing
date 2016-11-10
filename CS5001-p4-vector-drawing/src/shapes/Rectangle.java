package shapes;

import base.Rect;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by un4 on 04/11/16.
 */
public class Rectangle extends Shape {

    private java.awt.Rectangle rectangle;

    public Rectangle(Rect rect) {
        super(rect);
    }

    @Override
    public java.awt.Shape getShape() {
//        return new Rectangle2D.Float(rect.getOrigin().x, rect.getOrigin().y, rect.getWidth(), rect.getHeight());
        return this.rectangle;
//        return new Rectangle2D.Float(Math.min(origin.x, endPoint.x), Math.min(origin.y, endPoint.y), Math.abs(origin.x - endPoint.x), Math.abs(origin.y - endPoint.y));
//        return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

    @Override
    public boolean contains(Point point) {
        return this.getShape().contains(point);
    }

     public void makeObject(Point startDrag, Point endDrag) {
        java.awt.Rectangle r = new java.awt.Rectangle(Math.min(startDrag.x, endDrag.x), Math.min(startDrag.y, endDrag.y), Math.abs(startDrag.x - endDrag.x), Math.abs(startDrag.y - endDrag.y));
        this.rectangle = r;
    }

    @Override
    public void updateShape() {
        Point origin = rect.getOrigin();
        Point endPoint = rect.getEndPoint();
        this.rectangle = new java.awt.Rectangle(Math.min(origin.x, endPoint.x), Math.min(origin.y, endPoint.y), Math.abs(origin.x - endPoint.x), Math.abs(origin.y - endPoint.y));
    }
}
