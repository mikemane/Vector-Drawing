package controller;

import shapes.Shape;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Stack;
//import java.awt.Shape;

/**
 * Created by un4 on 13/11/16.
 */
public interface ShapeAction {
    void fill(Point point, Color color);

    void stroke(Point point, Color color);

    void clearAll();

    void addShape(Shape shape);

    void addShapes(ArrayList<Shape> shapes);

    void willMoveShapeAtPoint(Point p);

    void isMovingShape(Point start, Point end);

    void didMoveShapeToPoints();

    boolean removeShape(Point point);

    void undoAction();

    void redoAction();
}
