package shapes;

import base.Rect;

import java.awt.Point;
import java.awt.geom.Line2D;

/**
 * Created by un4 on 08/11/16.
 */
public class Line extends Shape {

    private Line2D line;

    /**
     * Line constructor.
     *
     * @param rect the rectangle to set.
     */
    public Line(Rect rect) {
        super(rect);
        Point origin = rect.getOrigin();
        Point end = rect.getEndPoint();
        this.setShape(new Line2D.Float(origin, end));
    }

    /**
     * moves the line from a point.
     *
     * @param start start coordinates.
     * @param end   end coordinates.
     */
    @Override
    public void move(Point start, Point end) {
        Line2D line = (Line2D) getShape();
        Point startPoint = new Point((int) line.getX1() + (end.x - start.x), (int) line.getY1() + (end.y - start.y));
        Point endPoint = new Point((int) line.getX2() + (end.x - start.x), (int) line.getY2() + (end.y - start.y));
        Line2D newline = new Line2D.Float(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        this.setShape(newline);
    }
}
