package shapes;

import java.awt.Point;

/**
 * Created by un4 on 08/11/16.
 */
public class Line extends Shape {

//    private Point start;
//    private Point end;

//    private LinkedHashSet<Point> points;
//    public Line(LinkedHashSet<Point> points) {
//        this.points = points;
//    }
//
//    public Line() {
//        this.points = new LinkedHashSet<>();
//    }
//
//    public void add(Point point) {
//        this.points.add(point);
//    }
//
//    public boolean removePoint(Point point) {
//        return this.points.remove(point);
//    }

    private Point start;
    private Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Gets the end coordinates.
     *
     * @return the end coordinates
     */
    public Point getEnd() {
        return end;
    }

    /**
     * Returns the start coordinates.
     *
     * @return Returns the start.
     */
    public Point getStart() {
        return start;
    }

    /**
     * Sets the start of the line.
     *
     * @param start This returns the start coordinates.
     */
    public void setStart(Point start) {
        this.start = start;
    }

    /**
     * Sets the end of the line.
     *
     * @param end This sets the end coordinates.
     */
    public void setEnd(Point end) {
        this.end = end;
    }

    public void translate(int x, int y) {
        this.start.translate(x, y);
        this.end.translate(x, y);
    }

    @Override
    public java.awt.Shape getShape() {
        return null;
    }
}
