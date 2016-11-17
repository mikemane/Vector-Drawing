package shapes;

import base.Rect;

import java.awt.*;

/**
 * Created by un4 on 09/11/16.
 */
public class Hexagon extends Shape {

    private final int NUMBER_OF_SIDES = 6;

    /**
     * Hexagon Constructor.
     *
     * @param rect ractangle.
     */
    public Hexagon(Rect rect) {
        super(rect);
        if (rect.getEndPoint() == null) {
            this.updateShape();
        } else {
            makeShape(rect.getOrigin(), rect.getEndPoint());
        }
    }


    /**
     * makes a shape from two points.
     *
     * @param start    the start point.
     * @param endPoint the end point.
     */
    private void makeShape(Point start, Point endPoint) {

        int minXPosition = Math.min(start.x, endPoint.x);
        int minYPosition = Math.min(start.y, endPoint.y);

        int width = Math.abs(start.x - endPoint.x);
        int height = Math.abs(start.y - endPoint.y);
        Polygon polygon = createPolygon(minXPosition, minYPosition, width, height);
        this.setShape(polygon);
    }

    private Polygon createPolygon(int minXPosition, int minYPosition, int width, int height) {
        Polygon polygon = new Polygon();
        for (int i = 0; i < NUMBER_OF_SIDES; i++) {
            int x = (int) (minXPosition + width * Math.cos(i * 2 * Math.PI / NUMBER_OF_SIDES));
            int y = (int) (minYPosition + height * Math.sin(i * 2 * Math.PI / NUMBER_OF_SIDES));
            Point p = new Point(x, y);
            polygon.addPoint(x, y);
        }
        return polygon;
    }

    @Override
    public void updateShape() {
        this.makeShape(getRect().getOrigin(), getRect().getEndPoint());
    }

    @Override
    public ShapeType getShapeType() {
        return ShapeType.HEXAGON;
    }
}

