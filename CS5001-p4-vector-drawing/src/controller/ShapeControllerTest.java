package controller;

import base.Rect;
import model.ShapeModel;
import org.junit.Before;
import org.junit.Test;
import shapes.Shape;
import shapes.ShapeFactory;
import shapes.ShapeType;

import java.awt.*;
import java.util.Stack;

import static org.junit.Assert.*;

/**
 * Created by un4 on 13/11/16.
 */
public class ShapeControllerTest {

    ShapeModel shapeModel;
    ShapeController shapeController;
    ShapeFactory shapeFactory = new ShapeFactory();
    Shape shape;

    @Before
    public void initShapes() {
        this.shapeModel = new ShapeModel();
        this.shapeController = new ShapeController(shapeModel);
        shape = shapeFactory.getShape(ShapeType.RECTANGLE, new Rect(0, 0, 10, 10), Color.black, Color.black);
        this.shapeController.addShape(shape);
    }

    @Test
    public void fill() throws Exception {
        Color color = Color.BLUE;
        shapeController.fill(new Point(5, 5), color);
        assertEquals(shape.getFillColor(), color);
    }

    @Test
    public void stroke() throws Exception {

    }

    @Test
    public void clearAll() throws Exception {
        shapeController.clearAll();
        assertEquals(0, shapeModel.getSize());
    }

    @Test
    public void addShape() throws Exception {
        Shape newShape = shapeFactory.getShape(ShapeType.RECTANGLE, new Rect(0, 0, 10, 10), Color.black, Color.black);
        shapeController.addShape(newShape);
        assertTrue(shapeModel.containsShape(newShape));
        assertEquals(shapeModel.getSize(), 2);
    }

    @Test
    public void addShapes() throws Exception {
    }

    @Test
    public void containsPoint() throws Exception {
        Shape shape = shapeController.containsPoint(new Point(5, 5));
        assertEquals(shape, this.shape);
    }

    /**
     * TODO basically gets ready to move the shape from point a to b.
     *
     * @throws Exception
     */
    @Test
    public void willMoveShape() throws Exception {
        Point point = new Point(5, 5);
        Shape shape = shapeController.containsPoint(point);
//        shapeController.willMoveShape(shape);
        assertFalse(shapeModel.containsShape(shape));
    }

    /**
     * TODO more vicious test to test the coordinates to be moved towards.
     *
     * @throws Exception
     */
    @Test
    public void didMoveShape() throws Exception {
        Point point = new Point(5, 5);
        Shape shape = shapeController.containsPoint(point);
//        shapeController.didMoveShape(shape);
        assertTrue(shapeModel.containsShape(shape));
    }

    @Test
    public void removeShape() throws Exception {

    }

}