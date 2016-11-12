package view.canvas;

import base.Rect;
import model.ShapeModel;
import shapes.Shape;
import shapes.ShapeFactory;
import shapes.ShapeType;
import utils.ImageExporter;
import utils.SaveFile;
import utils.OpenFile;
import view.menu.EditMenuAction;
import view.menu.EditMenuDelegate;
import view.menu.FileMenuAction;
import view.menu.FileMenuDelegate;
import view.sidebar.ISidebar;
import view.sidebar.ITopBar;
import view.sidebar.PaintAction;

import javax.swing.*;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;

/**
 * Created by un4 on 08/11/16.
 */
public class PaintCanvas extends JComponent implements ISidebar, EditMenuDelegate, FileMenuDelegate, ITopBar {

    private MouseHandler mouseHandler = new MouseHandler();
    private Point endDragging;
    private Point startDragging;
    private ShapeModel shapeModel;
    private Color currentColor;
    private Shape currentShape;
    private int strokeWidth;
    private ShapeType shapeType;
    private ShapeFactory shapeFactory;
    private Shape tempShape;
    private PaintAction paintAction;


    /**
     * This is a paont canvas and passes in a monad.
     *
     * @param shapeModel shape model to be passed ti eht paint canvas.
     */
    public PaintCanvas(ShapeModel shapeModel) {
        this.setBackground(Color.BLACK);
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
        this.shapeModel = shapeModel;
        this.shapeType = ShapeType.RECTANGLE;
        this.currentColor = Color.BLACK;
        this.strokeWidth = Shape.DEFAULT_STROKE;
        this.shapeFactory = new ShapeFactory();
    }

    /**
     * paint component to paint eht graphics.
     *
     * @param g graphics element.
     */
    @Override
    protected void paintComponent(Graphics g) {
//        g.drawImage(backgroundImage, 0, 0, null);
    }

    /**
     * setes the shape method.
     *
     * @param model thid the shape model.
     */
    public void setShapeModel(ShapeModel model) {
        this.shapeModel = model;
    }

    /**
     * sets the shape type.
     *
     * @param shapeType
     */
    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }


    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        this.shapeModel.getShapeStack().forEach(shape -> {
            graphics2D.setStroke(new BasicStroke(shape.getStrokeWidth()));
            graphics2D.setPaint(shape.getColor());
            graphics2D.draw(shape.getShape());
            if (shape.getFillColor() != null) {
                graphics2D.setColor(shape.getFillColor());
                graphics2D.fill(shape.getShape());
            }
        });

        if (startDragging != null && endDragging != null && paintAction == null) {
            // Makes the guide shape transparent
            graphics2D.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.40f));
            // Make guide shape gray for professional look
            graphics2D.setStroke(new BasicStroke(this.strokeWidth));
            graphics2D.setPaint(Color.LIGHT_GRAY);
            Shape aShape = getShape(this.shapeType, startDragging, endDragging);
            graphics2D.draw(aShape.getShape());
        }

        if (paintAction == PaintAction.MOVE) {
            graphics2D.draw(tempShape.getShape());
        }

    }

    /**
     * Implements the shape type method and changes the required shape type.
     *
     * @param shapeType this is the shape type;
     */
    @Override
    public void selectShape(ShapeType shapeType) {
        this.shapeType = shapeType;
        this.paintAction = null;
    }

    @Override
    public void changeColor(Color color) {
        this.currentColor = color;
    }


    @Override
    public boolean performAction(PaintAction action) {
        this.paintAction = action;
        return false;
    }


    @Override
    public void performFileAction(FileMenuAction fileMenuAction) {
        switch (fileMenuAction) {
            case OPEN:
                openFile();
                break;
            case SAVE:
                saveFile();
                break;
            case EXPORT:
                exportFile();
                break;
        }
    }

    /**
     * Returns shape model.
     *
     * @return the shape model to be returned.
     */
    public ShapeModel getShapeModel() {
        return shapeModel;
    }

    @Override
    public void performEditAction(EditMenuAction action) {
        switch (action) {
            case UNDO:
                this.undoAction();
                break;
            case REDO:
                this.redoAction();
                break;
        }
    }

    /**
     * Saves the file of the shape model.
     */
    private void saveFile() {
        SaveFile.SaveFile(this.shapeModel);
    }

    /**
     * Opens the model file.
     */
    public void openFile() {
        Stack<Shape> openedFiles = OpenFile.importShapes();
        if (openedFiles != null) {
            shapeModel.addAll(openedFiles);
            repaint();
        }
    }

    /**
     * Exports the file as an image.
     */
    public void exportFile() {
        ImageExporter.export(this);
    }

    /**
     * Undo the action.
     *
     * @return true if  the action is s
     */
    public void undoAction() {
        this.shapeModel.undo();
        this.repaint();
    }


    public void redoAction() {
        this.shapeModel.redo();
        this.repaint();
    }

    @Override
    public void changeStrokeWidth(int value) {
        this.strokeWidth = value;
    }

    /**
     * moves the shape.
     *
     * @param p shape to move.
     */
    private void moveShape(Point p) {
        for (Shape s : shapeModel.getShapeStack()) {
            if (s.contains(p)) {
                tempShape = s;
                shapeModel.removeShape(s);
                break;
            }
        }

    }

    /**
     * removes a shape at a point.
     *
     * @param p the point.
     */
    private void removeShape(Point p) {
        for (Shape shape : shapeModel.getShapeStack()) {
            if (shape.contains(p)) {
                shapeModel.removeShape(shape);
                break;
            }
        }
    }

    /**
     * Performd a pait action.
     *
     * @param action action to be performed.
     * @param point  point of interest.
     */
    private void performAction(PaintAction action, Point point) {
        if (action == null) return;
        switch (action) {
            case MOVE:
                this.moveShape(point);
                break;
            case DELETE:
                this.removeShape(point);
                break;
            case FILL:
                this.fillShape(point);
                break;
        }
    }

    private void fillShape(Point point) {
        for (Shape shape : shapeModel.getShapeStack()) {
            if (shape.contains(point)) {
                shape.setFillColor(this.currentColor);
                break;
            }
        }
    }

    /**
     * mouse listener to bbe extended.
     */
    class MouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            startDragging = new Point(e.getX(), e.getY());// e.getPoint();
            endDragging = startDragging;
            performAction(paintAction, startDragging);
            repaint();
        }

        /**
         * When mouse is dragged my the mouse events.
         *
         * @param e mouse event.
         */
        @Override
        public void mouseDragged(MouseEvent e) {
            endDragging = new Point(e.getX(), e.getY());
            repaint();
        }

        /**
         * this is when the mouse is released.
         *
         * @param e the event to be clicked on.
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            Point p = new Point(e.getX(), e.getY());
            if (paintAction == PaintAction.MOVE) {
                if (tempShape.contains(p)) {
                    tempShape.move(startDragging, p);
                    shapeModel.addShape(tempShape);
                }
            } else if (paintAction == null) {
                currentShape = getShape(shapeType, startDragging, endDragging);
                currentShape.setStrokeWidth(strokeWidth);
                shapeModel.addShape(currentShape);
            }
            startDragging = null;
            endDragging = null;
            repaint();
        }
    }

    /**
     * gets the shape from the panel.
     *
     * @param shapeType the shape type to be created
     * @param origin    the origin of the shape.
     * @param end       the end of tjhe shape.
     * @return the shape requested.
     */
    private Shape getShape(ShapeType shapeType, Point origin, Point end) {
        Rect rect = new Rect(origin, end);
        Shape shape = shapeFactory.getShape(shapeType, rect, currentColor, currentColor);
        return shape;
    }
}
