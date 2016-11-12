package model;

import shapes.Shape;

import java.util.*;

/**
 * Created by un4 on 07/11/16.
 */
public class ShapeModel extends Observable {
    private Stack<Shape> undoShapes;
    private Stack<Shape> shapeStack;

    /**
     * Initialising the shape constructor with new models.
     */
    public ShapeModel() {
        this(new Stack<>());
    }

    /**
     * Initialising the shape constructor with a shape collection.
     *
     * @params shapeStack this is the list of all the shapes.
     */
    public ShapeModel(Stack<Shape> shapeStack) {
        this.shapeStack = shapeStack;
        this.undoShapes = new Stack<>();
    }

    /**
     * This gets the undo shapes.
     *
     * @return this returns all the shapes to be undo.
     */
    public Stack<Shape> getUndoShapes() {
        return undoShapes;
    }

    public int undoShapeSize() {
        return this.undoShapes.size();
    }

    /**
     * Sets the undo shape.
     *
     * @param undoShapes
     */
    public void setUndoShapes(Stack<Shape> undoShapes) {
        this.undoShapes = undoShapes;
    }

    /**
     * This adds a shape to the list.
     *
     * @param shape this represents the shape to be added.
     */
    public void addShape(Shape shape) {
        this.shapeStack.add(shape);
    }

    /**
     * Removes a shape from the list.
     *
     * @param shape the shape to remove.
     */
    public boolean removeShape(Shape shape) {
        if (this.shapeStack.remove(shape)) {
            this.undoShapes.push(shape);
            return true;
        }
        return false;
    }

    /**
     * Performs an undo action.
     *
     * @return true if action is done.
     */
    public boolean redo() {
        if (this.undoShapes.isEmpty()) {
            return false;
        }
        Shape shape = (Shape) this.undoShapes.pop();
        if (shape != null) {
            this.shapeStack.add(shape);
            return true;
        }
        return false;
    }

    /**
     * undo the action. checks if the shape is empty and remove from the undo stack and insert into the main stack.
     *
     * @return returns true if the problem was successful.
     */
    public boolean undo() {
        if (this.shapeStack.isEmpty()) {
            return false;
        }
        Shape shape = (Shape) this.shapeStack.pop();
        if (shape != null) {
            this.undoShapes.add(shape);
            return true;
        }
        return false;
    }

    /**
     * Returns the size of the shape stack.
     *
     * @return the size of the getSize of the shape.
     */
    public int getSize() {
        return this.shapeStack.size();
    }

    /**
     * checks if the shape stack contains the value.
     *
     * @param shape the shape to be checked.
     * @return true if the contains the shape.
     */
    public boolean containsShape(Shape shape) {
        return this.shapeStack.contains(shape);
    }

    private Shape get(int index) {
        Shape shape = this.shapeStack.get(index);
        if (shape == null) {
            return null;
        }
        return shape;
    }

    /**
     * returns the Shape stack.
     *
     * @return shape stack.
     */
    public Stack<Shape> getShapeStack() {
        return shapeStack;
    }

    /**
     * Clears the shape stack.
     */
    public void clear() {
        this.getShapeStack().forEach(this::removeShape);
    }

    /**
     * Adds all the files to the current shape file.
     *
     * @param openedFiles opened files.
     */
    public void addAll(Stack<Shape> openedFiles) {
        this.shapeStack.addAll(openedFiles);
    }
}
