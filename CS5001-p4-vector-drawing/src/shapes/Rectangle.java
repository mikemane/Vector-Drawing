package shapes;

import base.Rect;

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
        return new Rectangle2D.Float(rect.getOrigin().x, rect.getOrigin().y, rect.getWidth(), rect.getHeight());
    }
}
