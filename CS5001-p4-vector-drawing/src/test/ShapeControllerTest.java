package test;

import base.Rect;
import controller.ShapeController;
import model.ShapeModel;
import org.junit.Before;
import org.junit.Test;
import shapes.Shape;
import shapes.ShapeFactory;
import shapes.ShapeType;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by un4 on 17/11/16.
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
    }

    @Test
    public void fill() throws Exception {
        shapeController.addShape(shape);
        Color color = Color.GREEN;
        Point point = new Point(5, 5);
        shapeController.fill(point, Color.GREEN);
        assertEquals(shapeController.containsPoint(point).getFillColor(), color);

    }

    @Test
    public void stroke() throws Exception {
        shapeController.addShape(shape);
        Color color = Color.BLUE;
        Point point = new Point(5, 5);
        shapeController.stroke(point, color);
        assertEquals(shapeController.containsPoint(point).getStrokeColor(), color);
    }

    @Test
    public void clearAll() throws Exception {
        assertTrue(shapeModel.getShapeList().isEmpty());
        for (int i = 0; i < 10; i++) {
            this.shapeController.addShape(shape.deepCopy());
        }
        assertFalse(shapeModel.getShapeList().isEmpty());
        shapeController.clearAll();
        assertTrue(shapeModel.getShapeList().isEmpty());
    }

    @Test
    public void addShape() throws Exception {
        shapeController.addShape(shape);
        assertTrue(shapeController.getShapeModel().getShapeList().contains(shape));
    }

    @Test
    public void addShapes() throws Exception {

    }

    @Test
    public void willMoveShapeAtPoint() throws Exception {
        Point p = new Point(5, 5);
        this.shapeController.addShape(shape);
        this.shapeController.willMoveShapeAtPoint(p);
        assertFalse(shapeModel.containsShape(shape));
    }


    @Test
    public void containsPoint() throws Exception {
        shapeController.addShape(shape);
        assertEquals(shapeController.containsPoint(new Point(5, 5)), shape);
        assertEquals(shapeController.containsPoint(new Point(9, 5)), shape);
    }

    @Test
    public void removeShape() throws Exception {
        shapeController.addShape(shape);
        assertTrue(shapeModel.containsShape(shape));
        shapeController.removeShape(new Point(5, 5));
        assertFalse(shapeModel.containsShape(shape));
    }

    @Test
    public void undoAction() throws Exception {
        shapeController.addShape(shape);
        assertTrue(shapeModel.containsShape(shape));
        shapeController.undoAction();
        assertFalse(shapeModel.containsShape(shape));
        shapeController.addShape(shape);
        assertTrue(shapeModel.containsShape(shape));
        shapeController.removeShape(new Point(5, 5));
        assertFalse(shapeModel.containsShape(shape));
        shapeController.undoAction();
        assertTrue(shapeModel.containsShape(shape));
    }

    @Test
    public void redoAction() throws Exception {
        shapeController.addShape(shape);
        assertTrue(shapeModel.containsShape(shape));
        shapeController.undoAction();
        assertFalse(shapeModel.containsShape(shape));
        shapeController.redoAction();
        assertTrue(shapeModel.containsShape(shape));
    }

    @Test
    public void createShape() throws Exception {
        assertTrue(shapeModel.getShapeList().isEmpty());
        shapeController.createShape(ShapeType.HEXAGON, new Point(0, 0), new Point(10, 10), Color.BLACK, Color.BLUE, 20);
        assertFalse(shapeModel.getShapeList().isEmpty());
    }


    @Test
    public void createImageFromPath() throws Exception {
//        shapeController.createImageFromPath("filePath");
    }

}