package shapes;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.LinkedHashSet;

/**
 * Created by un4 on 09/11/16.
 */
public class Path extends Shape {

    private LinkedHashSet<Point> points;

    /**
     * Overriding constructor.
     *
     * @param points linked hash map points
     */
    public Path(LinkedHashSet<Point> points) {
        this.points = points;
    }

    /**
     * Default constructor.
     */
    public Path() {
        this(new LinkedHashSet<Point>());
    }

    public void addPoint(Point point) {
        this.points.add(point);
    }

    public boolean removePoint(Point point) {
        return this.points.remove(point);
    }

    public boolean containsPoint(Point point) {
        return this.points.contains(point);
    }


    /**
     * Returns a linked hash set of paths.
     * @return
     */
    public LinkedHashSet<Point> getPoints() {
        return points;
    }

    public GeneralPath getDrawablePath(){
        GeneralPath path = new GeneralPath();
        return path;
    }

    @Override
    public java.awt.Shape getShape() {
        return null;
    }
}
