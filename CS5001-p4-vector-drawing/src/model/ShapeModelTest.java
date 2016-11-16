package model;

import base.Rect;
import org.junit.Before;
import org.junit.Test;
import shapes.Shape;
import shapes.ShapeFactory;
import shapes.ShapeType;

import java.awt.*;

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
        this.shape = factory.getShape(ShapeType.RECTANGLE, new Rect(0, 0, 10, 10), Color.BLACK, Color.BLUE);

    }

    @Test
    public void removeShape() throws Exception {
        shapeModel.removeShape(shape);
        assertFalse(shapeModel.containsShape(shape));
    }

    @Test
    public void purgeShape() throws Exception {

    }

    @Test
    public void redo() throws Exception {

    }

    @Test
    public void undo() throws Exception {

    }

    @Test
    public void getSize() throws Exception {

    }

    @Test
    public void containsShape() throws Exception {

    }

    @Test
    public void addAll() throws Exception {

    }

    @Test
    public void containsPoint() throws Exception {

    }

    @Test
    public void add() throws Exception {

    }

    @Test
    public void getShapeList() throws Exception {

    }

    @Test
    public void getLastListOfStack() throws Exception {

    }

}