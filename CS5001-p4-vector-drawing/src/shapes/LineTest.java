package shapes;

import base.Rect;
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
        Rect rect = new Rect(start,end);
        line = new Line(rect);
    }



}