package test;

import base.Rect;
import model.ShapeModel;
import org.junit.Before;
import org.junit.Test;
import shapes.Shape;
import shapes.ShapeFactory;
import shapes.ShapeType;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by un4 on 15/11/16.
 */
public class ShapeModelTest {

    ShapeModel shapeModel;
    Shape shape;
    ShapeFactory factory;

    @Before
    public void init() {
        this.shapeModel = new ShapeModel();
        this.factory = new ShapeFactory();
        this.shape = factory.getShape(ShapeType.RECTANGLE, new Rect(new Point(0, 0), new Point(100, 100)), Color.BLACK, Color.BLUE);
    }

    @Test
    public void removeShape() throws Exception {
        shapeModel.add(shape);
        assertTrue(shapeModel.containsShape(shape));
        shapeModel.removeShape(shape);
        assertFalse(shapeModel.containsShape(shape));
    }

    @Test
    public void purgeShape() throws Exception {
        shapeModel.add(shape);
        assertTrue(shapeModel.containsShape(shape));
        shapeModel.purgeShape(shape);
        assertFalse(shapeModel.containsShape(shape));
    }

    @Test
    public void redo() throws Exception {
        assertTrue(shapeModel.getShapeList().isEmpty());
        shapeModel.add(shape);
        shapeModel.undo();
        assertFalse(shapeModel.containsShape(shape));
        shapeModel.redo();
        assertTrue(shapeModel.containsShape(shape));
    }

    @Test
    public void undo() throws Exception {
        shapeModel.add(shape);
        assertTrue(shapeModel.containsShape(shape));
        shapeModel.undo();
        assertFalse(shapeModel.containsShape(shape));
    }

    @Test
    public void getSize() throws Exception {
        assertEquals(shapeModel.getSize(), 0);
        shapeModel.add(shape);
        assertEquals(shapeModel.getSize(), 1);
        shapeModel.removeShape(shape);
        assertEquals(shapeModel.getSize(), 0);
    }

    @Test
    public void containsShape() throws Exception {
        assertFalse(shapeModel.containsShape(shape));
        shapeModel.add(shape);
        assertTrue(shapeModel.containsShape(shape));
        shapeModel.removeShape(shape);
        assertFalse(shapeModel.containsShape(shape));
    }

    @Test
    public void addAll() throws Exception {
        ArrayList<Shape> shapes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            shapes.add(shape.deepCopy());
        }
        shapeModel.addAll(shapes);
        assertEquals(shapeModel.getSize(), 10);
    }

    @Test
    public void containsPoint() throws Exception {
        Point point = new Point(5, 5);
        assertNull(shapeModel.containsPoint(point));
        shapeModel.add(shape);
        assertEquals(shapeModel.containsPoint(point), shape);
    }

    @Test
    public void add() throws Exception {
        assertFalse(shapeModel.containsShape(shape));
        shapeModel.add(shape);
        assertTrue(shapeModel.containsShape(shape));
    }

}