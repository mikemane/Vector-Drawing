package model;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by un4 on 07/11/16.
 */
public class ShapeModel {

    private Stack<Shape> redoshapes;
    private Queue<Shape> undoShapes;

    public ShapeModel() {
        this.undoShapes = new PriorityQueue<>();
        this.redoshapes = new Stack<>();
    }

    public Queue<Shape> getUndoShapes() {
        return undoShapes;
    }

    public Stack<Shape> getRedoshapes() {
        return redoshapes;
    }


    public void setRedoshapes(Stack<Shape> redoshapes) {
        this.redoshapes = redoshapes;
    }

    public void setUndoShapes(Queue<Shape> undoShapes) {
        this.undoShapes = undoShapes;
    }
}
