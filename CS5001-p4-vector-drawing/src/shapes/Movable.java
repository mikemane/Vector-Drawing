package shapes;

import sun.awt.resources.awt;

import java.awt.*;
import java.awt.Shape;

/**
 * Created by un4 on 09/11/16.
 */
public interface Movable {

    /**
     * checks if the requested shape contains this particular point.
     *
     * @param point the point to check
     * @return true if the shape contains point.
     */
    boolean contains(Point point);

    /**
     * @param start
     * @param end
     */
    void move(Point start, Point end);

}
