package model;

import base.Rect;
import model.ShapeModel;
import shapes.Shape;
import org.junit.Before;
import org.junit.Test;
import shapes.ShapeFactory;
import shapes.ShapeType;

import java.awt.*;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by un4 on 08/11/16.
 */
public class ShapeModelTest {

    ShapeFactory shapeFactory;
    ShapeModel shapeModel;
    Shape shape;

    @Before
    public void initShapes() {
        shapeFactory = new ShapeFactory();
        shapeModel = new ShapeModel();
        shape = shapeFactory.getShape(ShapeType.ELLIPSE, new Rect(0, 0, 0, 0), Color.BLUE);
    }


    @Test
    public void addShape() throws Exception {
        shapeModel.addShape(shape);
        assertTrue(shapeModel.containsShape(shape));
    }

    @Test
    public void removeShape() throws Exception {
        shapeModel.addShape(shape);
        assertTrue(shapeModel.removeShape(shape));
        assertEquals(0, shapeModel.getSize());
        assertFalse(shapeModel.containsShape(shape));
        assertEquals(1, shapeModel.undoShapeSize());
        assertTrue(shapeModel.undo());
    }

    @Test
    public void getShapeCount() throws IOException {
        shapeModel.addShape(shape);
        assertEquals(1, shapeModel.getSize());
    }

    @Test
    public void undoRemoveShape() throws Exception {

    }
}