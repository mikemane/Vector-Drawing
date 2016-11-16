package model;

import shapes.Shape;

import java.awt.*;
import java.util.*;

/**
 * Created by un4 on 07/11/16.
 */
public class ShapeModel extends Observable {

    private Stack<ArrayList<Shape>> redoShapeStack;
    private Stack<ArrayList<Shape>> undoShapeStack;
    private Shape movableShape;
    private Shape highlightedShape;
    private Integer lastIndex;


    /**
     * Initialising the shape constructor with a shape collection.
     *
     * @params redoShapeStack this is the list of all the shapes.
     */
    public ShapeModel() {
        this.redoShapeStack = new Stack<>();
        this.undoShapeStack = new Stack<>();
        this.addListOfShapesToStack(this.redoShapeStack, new ArrayList<>());
    }

    /**
     * Removes a shape from the list.
     *
     * @param shape the shape to remove.
     */
    public boolean removeShape(Shape shape) {
        ArrayList list = getListClone(this.redoShapeStack);
        if (list.remove(shape)) {
            this.addListOfShapesToStack(this.redoShapeStack, list);
            return true;
        }
        return false;
    }


    public void setHighlightedShape(Shape highlightedShape) {
        this.highlightedShape = highlightedShape;
    }

    public Shape getHighlightedShape() {
        return highlightedShape;
    }

    public void setMovableShape(Shape movableShape) {
        if (movableShape != null) movableShape.setAlpha((float)0.5);
        this.movableShape = movableShape;
    }

    public Shape getMovableShape() {
        return movableShape;
    }

    /**
     * Performs an undo action.
     *
     * @return true if action is done.
     */
    public boolean redo() {
        if (!undoShapeStack.isEmpty()) {
            ArrayList<Shape> redoList = this.undoShapeStack.pop();
            this.addListOfShapesToStack(this.redoShapeStack, redoList);
        }
        return true;
    }

    /**
     * undo the action. checks if the shape is empty and remove from the undo stack and insert into the main stack.
     *
     * @return returns true if the problem was successful.
     */
    public boolean undo() {
        if (!this.redoShapeStack.isEmpty()) {
            ArrayList<Shape> redoList = this.redoShapeStack.pop();
            this.addListOfShapesToStack(this.undoShapeStack, redoList);
        }
        return true;
    }


    /**
     * Returns the size of the shape stack.
     *
     * @return the size of the getSize of the shape.
     */
    public int getSize() {
        return this.redoShapeStack.size();
    }

    /**
     * checks if the shape stack contains the value.
     *
     * @param shape the shape to be checked.
     * @return true if the contains the shape.
     */
    public boolean containsShape(Shape shape) {
        return this.getShapeList().contains(shape);
    }


    /**
     * Adds all the files to the current shape file.
     *
     * @param openedFiles opened files.
     */
    public void addAll(ArrayList<Shape> openedFiles) {
        ArrayList<Shape> shapeArrayListClone = getListClone(this.redoShapeStack);
        shapeArrayListClone.addAll(openedFiles);
        this.addListOfShapesToStack(this.redoShapeStack, shapeArrayListClone);
    }

    /**
     * adds the list to the undo stack.
     *
     * @param list this is the list thst is going to be added.
     */
    public void addListToUndo(ArrayList<Shape> list) {
        this.addListOfShapesToStack(this.undoShapeStack, list);
    }

    /**
     * @param point this is the point the shape must contain.
     * @return this returns the first shape to contain the point.
     */
    public Shape containsPoint(Point point) {
        for (Shape shape : getShapeList()) {
            if (shape.contains(point)) {
                return shape;
            }
        }
        return null;
    }

    public boolean purgeShape(Shape shape) {
        return getShapeList().remove(shape);
    }

    /**
     * Adds the shape to the stack.
     *
     * @param shape the shape to add to the top stack.
     */
    public void add(Shape shape) {
        addShapeToStack(this.redoShapeStack, shape);
    }

    public void add(Shape shape, boolean undo) {
        if (!undo) {
            this.getShapeList().add(shape);
        } else {
            addShapeToStack(this.redoShapeStack, shape);
        }
    }

    /**
     * Gets the top list in the shape stack.
     *
     * @return the current list of the shape stack or null if  the stack is empty.
     */
    public ArrayList<Shape> getShapeList() {
        return this.getLastListOfStack(this.redoShapeStack);
    }

    /**
     * gets the last item in the stack of shapes.
     *
     * @param shapeStack this is the shape stack to check and return the value.
     * @return this is the list that is returned.
     */
    public ArrayList<Shape> getLastListOfStack(Stack<ArrayList<Shape>> shapeStack) {
        if (!shapeStack.isEmpty())
            return shapeStack.peek();
        return null;
    }

    /**
     * This clears the item in the shape.
     */
    public void clear() {
        this.clearShapesFromStack(this.redoShapeStack, this.undoShapeStack);
    }

    /**
     * Adds as shape to the stack.
     *
     * @param stack the stack to add to the stack.
     * @param shape the shape too add to the stack.
     */
    private boolean addShapeToStack(Stack<ArrayList<Shape>> stack, Shape shape) {
        ArrayList<Shape> newShapeList = getListClone(stack);
        if (newShapeList != null) {
            newShapeList.add(shape);
            stack.push(newShapeList);
            return true;
        }
        return false;
    }

    private boolean addShapeToStack(Stack<ArrayList<Shape>> stack, Shape shape, int index) {
        ArrayList<Shape> newShapeList = getListClone(stack);
        if (newShapeList != null) {
            newShapeList.add(index, shape);
            stack.push(newShapeList);
            return true;
        }
        return false;
    }


    /**
     * Gets the last elemsnt llist clone of the stack array.
     *
     * @param stack the stack to clone the last element from.
     * @return the last arraylist of the list.
     */
    private ArrayList<Shape> getListClone(Stack<ArrayList<Shape>> stack) {
        ArrayList<Shape> shapeArrayList = (ArrayList<Shape>) stack.peek().clone();
        if (shapeArrayList != null) {
            return shapeArrayList;
        }
        return null;
    }

    private void addListOfShapesToStack(Stack<ArrayList<Shape>> stack, ArrayList<Shape> shapes) {
        stack.add(shapes);
    }

    private void clearShapesFromStack(Stack<ArrayList<Shape>> shapeToClear, Stack<ArrayList<Shape>> stackToAdd) {
        stackToAdd.addAll(shapeToClear);
        shapeToClear.clear();
    }


    public void add(ArrayList currentBoardState) {
        this.addListOfShapesToStack(this.redoShapeStack, currentBoardState);
    }

    public void add(Shape shape, int index) {
        this.addShapeToStack(this.redoShapeStack, shape, index);
    }

    public void updateObservers() {
        this.setChanged();
        this.notifyObservers();
    }

    public void copyToUndo() {
//        this.addListOfShapesToStack(this.getShapeList().clone(),this.redoShapeStack);
        ArrayList<Shape> shapeArrayList = (ArrayList<Shape>) this.getShapeList().clone();
        this.addListOfShapesToStack(this.redoShapeStack, shapeArrayList);
    }

    public Integer get(Shape shape) {
        int i = getShapeList().indexOf(shape);
        return (i >= 0) ? i : null;
    }

    public void setLastIndex(Integer lastIndex) {
        this.lastIndex = lastIndex;
    }

    public Integer getLastIndex() {
        return lastIndex;
    }
}
