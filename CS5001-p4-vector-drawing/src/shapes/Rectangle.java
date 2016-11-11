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
        Point origin = rect.getOrigin();
        Point endPoint = rect.getEndPoint();
        makeObject(origin, endPoint);
    }


    /**
     * creates a rectangle object/.
     *
     * @param startDrag starting drag location.
     * @param endDrag   the position of the end location.
     */
    public void makeObject(Point startDrag, Point endDrag) {
        java.awt.Rectangle r = new java.awt.Rectangle(Math.min(startDrag.x, endDrag.x), Math.min(startDrag.y, endDrag.y), Math.abs(startDrag.x - endDrag.x), Math.abs(startDrag.y - endDrag.y));
        this.setShape(r);
    }

    /**
     * this moves the shape.
     *
     * @param start start coordinates.
     * @param end   end coordinates.
     */
    @Override
    public void move(Point start, Point end) {
        java.awt.Rectangle rectangle = (java.awt.Rectangle) getShape();
        Point origin = new Point(rectangle.x + (end.x - start.x), rectangle.y + (end.y - start.y));
        java.awt.Rectangle r = new java.awt.Rectangle(rectangle.x + (end.x - start.x), rectangle.y + (end.y - start.y), rectangle.width, rectangle.height);
        this.rect.setHeight(r.height);
        this.rect.setWidth(r.width);
        this.rect.setOrigin(origin);
        this.setShape(r);
    }
}
