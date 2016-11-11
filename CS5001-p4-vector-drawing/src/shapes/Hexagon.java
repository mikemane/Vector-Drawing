package shapes;

import base.Rect;

import java.awt.*;

/**
 * Created by un4 on 09/11/16.
 */
public class Hexagon extends Shape {


    /**
     * Hexagon Constructor.
     *
     * @param rect ractangle.
     */
    public Hexagon(Rect rect) {
        super(rect);
        makeShape(rect.getOrigin(), rect.getEndPoint());
    }


    private void makeShape(Point start, Point endPoint) {
        for (int i = 0; i < 6; i++) {

        }

        int midX = Math.abs(start.x + endPoint.x) / 2;
        int quaterY = (int) (Math.abs(start.y + endPoint.y) * 0.25);
        int tquaterY = (int) (Math.abs(start.y + endPoint.y) * 0.75);
//
////        Point a = new Point(midX, start.y);
////
////        //Quater side sides.
////        Point b = new Point(start.x, quaterY);
////        Point c = new Point(endPoint.x, quaterY);
////
////        // 3 Quaters sides.
////        Point d = new Point(start.x, tquaterY);
////        Point e = new Point(endPoint.x, tquaterY);
//
//        //
//        Point f = new Point(midX, endPoint.y);

        int[] xPositions = {midX, start.x, start.x, midX, endPoint.x, endPoint.x};
        int[] yPositions = {start.y, quaterY, tquaterY, endPoint.y, tquaterY, quaterY};
        Polygon polygon = new Polygon(xPositions, yPositions, xPositions.length);
////        Polygon polygon = new Polygon();

        this.setShape(polygon);
    }

    @Override
    public void move(Point start, Point end) {

    }
}

