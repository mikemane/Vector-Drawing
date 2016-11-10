package shapes;

import base.Rect;

import java.awt.*;

/**
 * Created by un4 on 09/11/16.
 */
public class Hexagon extends Shape {

    private Polygon polygon;

    public Hexagon(Rect rect) {
        super(rect);
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }


    @Override
    public java.awt.Shape getShape() {

        int midx;
        int firstY;
        int secondY;

        Polygon polygon = new Polygon();

        //
//        Polygon polygon = new Polygon();
//        polygon.a
        return polygon;
    }

    @Override
    public boolean contains(Point point) {
        return this.getShape().contains(point);
    }

    @Override
    public void updateShape() {

    }

}

