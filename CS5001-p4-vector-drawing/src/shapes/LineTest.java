package shapes;

import org.junit.Before;
import org.junit.Test;

import java.awt.Point;

import static org.junit.Assert.*;

/**
 * Created by un4 on 08/11/16.
 */
public class LineTest {

    Line line;
    Point start;
    Point end;

    @Before
    public void initLine() {
        start = new Point(10, 10);
        end = new Point(10, 10);
        line = new Line(start, end);
    }

    @Test
    public void translate() throws Exception {
        int x = 10;
        int y = 15;

        line.translate(x, y);
        assertEquals(20, (int) line.getStart().getX());
        assertEquals(25, (int) line.getStart().getY());
    }

}