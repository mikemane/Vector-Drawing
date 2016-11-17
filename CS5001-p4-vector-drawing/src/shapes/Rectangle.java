package shapes;

import base.Rect;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by un4 on 04/11/16.
 */
public class Rectangle extends Shape {

    /**
     * Rectangle constructor that takes in a rect object that denotes the bounds.
     *
     * @param rect this is the rect of the rectangle.
     */
    public Rectangle(Rect rect) {
        super(rect);
        if (rect.getEndPoint() == null) {
            this.updateShape();
        } else {
            makeObject(rect.getOrigin(), rect.getEndPoint());
        }
    }

    /**
     * creates a rectangle object.
     */
    public void makeObject(Point start, Point end) {
        java.awt.Rectangle r = new java.awt.Rectangle(Math.min(start.x, end.x), Math.min(start.y, end.y), Math.abs(start.x - end.x), Math.abs(start.y - end.y));
        this.setShape(r);
    }



    @Override
    public void updateShape() {
        this.makeObject(getRect().getOrigin(), rect.getEndPoint());
    }

    @Override
    public ShapeType getShapeType() {
        return ShapeType.RECTANGLE;
    }

}
