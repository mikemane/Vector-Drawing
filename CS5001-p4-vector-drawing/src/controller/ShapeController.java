package controller;

import base.Rect;
import model.ShapeModel;
import shapes.ImageShape;
import shapes.Shape;
import shapes.ShapeFactory;
import shapes.ShapeType;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by un4 on 13/11/16.
 */
public class ShapeController implements ShapeAction {

    private ShapeModel shapeModel;
    private ShapeFactory shapeFactory;

    public ShapeController(ShapeModel shapeModel) {
        this.shapeModel = shapeModel;
        this.shapeFactory = new ShapeFactory();
    }


    @Override
    public void fill(Point point, Color color) {
        Shape shape = shapeModel.containsPoint(point);
        if (shape != null) {
            Shape newClone = shape.clone();
            newClone.setFillColor(color);
            this.shapeModel.copyToUndo();
            int i = this.shapeModel.getShapeList().indexOf(shape);
            shapeModel.purgeShape(shape);
            shapeModel.getShapeList().add(i, newClone);
            shapeModel.updateObservers();
        }
    }

    /**
     * Stroke the shape with a color.
     *
     * @param point the point to check for the required shape.
     * @param color the color to change the shape to.
     */
    @Override
    public void stroke(Point point, Color color) {
        Shape shape = this.containsPoint(point);
        if (shape == null) return;
        Shape newClone = shape.clone();
        newClone.setStrokeColor(color);
        this.shapeModel.copyToUndo();
        int i = this.shapeModel.getShapeList().indexOf(shape);
        shapeModel.purgeShape(shape);
        shapeModel.getShapeList().add(i, newClone);
        shapeModel.updateObservers();
    }


    /**
     * Clears all the shapes from the model.
     */
    @Override
    public void clearAll() {
        this.shapeModel.clear();
        shapeModel.updateObservers();
    }

    /**
     * Adds a shape to the model.
     *
     * @param shape the shape to be added to the model.
     */
    @Override
    public void addShape(Shape shape) {
        this.shapeModel.add(shape);
        shapeModel.updateObservers();
    }

    /**
     * Adds a stack of shapes to the model.
     *
     * @param shapes the stack of shapes to be added.
     */
    @Override
    public void addShapes(ArrayList<Shape> shapes) {
        this.shapeModel.addAll(shapes);
        this.shapeModel.updateObservers();
    }

    @Override
    public void willMoveShapeAtPoint(Point p) {
        Shape shape = containsPoint(p);
        if (shape != null) {
            int index = this.shapeModel.get(shape);
            this.shapeModel.setLastIndex(index);
            this.shapeModel.copyToUndo();
            shape = this.shapeModel.get(index);
            this.shapeModel.setMovableShape(shape.deepCopy());
            this.shapeModel.purgeShape(shape);
        }
    }


    /**
     * after the point have been moved.
     */
    @Override
    public void didMoveShapeToPoints() {
        Shape movableShape = this.shapeModel.getMovableShape();
        if (movableShape == null) return;
        movableShape.setAlpha((float) 1.0);
        this.shapeModel.getShapeList().add(this.shapeModel.getLastIndex(), movableShape);
        this.shapeModel.setMovableShape(null);
        this.shapeModel.setLastIndex(null);
        this.shapeModel.updateObservers();
    }

    /**
     * check if the shape contains the specific point.
     *
     * @param point the point the shape should contain.
     * @return if the first shape that contains the point.
     */
    public Shape containsPoint(Point point) {
        return this.shapeModel.containsPoint(point);
    }


    /**
     * returns shape containing point.
     *
     * @param point the point the shape should contain.
     * @return if the shape has been successfully Removed.
     */
    @Override
    public boolean removeShape(Point point) {
        Shape shape = this.containsPoint(point);
        if (shape != null) {
            this.shapeModel.removeShape(shape);
            this.shapeModel.updateObservers();
            return true;
        }
        return false;
    }

    /**
     * performs an undo action.
     */
    @Override
    public void undoAction() {
        if (shapeModel.undo()) {
            this.shapeModel.updateObservers();
        }
    }

    /**
     * Re do the action that was last undone.
     */
    @Override
    public void redoAction() {
        if (shapeModel.redo()) {
            shapeModel.updateObservers();
        }
    }


    /**
     * gets the shape from the panel.
     *
     * @param shapeType the shape type to be created
     * @param origin    the origin of the shape.
     * @param end       the end of the shape.
     * @return the shape requested.
     */
    public void createShape(ShapeType shapeType, Point origin, Point end, Color strokeColor, Color fillColor, int strokeWidth) {
        Rect rect = new Rect(origin, end);
        Shape shape = shapeFactory.getShape(shapeType, rect, strokeColor, fillColor);
        shape.setStrokeWidth(strokeWidth);
        this.shapeModel.add(shape);
        this.shapeModel.setHighlightedShape(null);
        this.shapeModel.updateObservers();
    }


    public ShapeModel getShapeModel() {
        return this.shapeModel;
    }

    /**
     * this updates the shape type to the current position.
     *
     * @param shapeType     this is the shape  type of the shape to create.
     * @param startDragging this is the current position to start dragging from.
     * @param point         this is the end point to drag towards.
     * @param strokeWidth   this is the stroke width to drag towards.
     */
    public void updateTempShape(ShapeType shapeType, Point startDragging, Point point, int strokeWidth) {
        Shape shape = shapeFactory.getShape(shapeType, new Rect(startDragging, point), Color.LIGHT_GRAY, null);
        shape.setStrokeWidth(strokeWidth);
        this.shapeModel.setHighlightedShape(shape);
        this.shapeModel.updateObservers();
    }


    public void createImageFromPath(String aFilePath) {
        ImageShape imageShape = new ImageShape(aFilePath);
        if (imageShape == null) return;
        this.shapeModel.add(imageShape);
        shapeModel.updateObservers();
    }

    /**
     * this is the moving shape.
     *
     * @param startDragging this is the start dragging.
     * @param endDragging   this end dragging position.
     */
    @Override
    public void isMovingShape(Point startDragging, Point endDragging) {
        if (shapeModel.getMovableShape() != null) {
            shapeModel.getMovableShape().move(startDragging, endDragging);
            this.shapeModel.updateObservers();
        }
    }

    /**
     * TODO experiment with the affine transform stuff.
     *
     * @param a the start coordinate.
     * @param b the end coordinate.
     */
    public void isRotating(Point a, Point b) {
        if (shapeModel.getMovableShape() != null) {
            double theta = Math.atan(b.y - a.y / b.x - a.x);
            System.out.println(theta);
            this.shapeModel.getMovableShape().rotate(theta);
            shapeModel.updateObservers();
        }
    }

    /**
     * Scaling mechanism that subtract the value to the end point.
     *
     * @param startDragging the start location
     * @param endDragging   this is then end dragging coordinates.
     */
    public void isScaling(Point startDragging, Point endDragging) {
        if (shapeModel.getMovableShape() != null) {
            int scaleX = (endDragging.x - startDragging.x);
            int scaleY = (endDragging.y - startDragging.y);
            this.shapeModel.getMovableShape().scale(scaleX, scaleY);
            shapeModel.updateObservers();
        }
    }
}
