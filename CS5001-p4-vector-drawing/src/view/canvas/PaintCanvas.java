package view.canvas;

import base.Rect;
import controller.ShapeController;
import model.ShapeModel;
import shapes.ImageShape;
import shapes.Shape;
import shapes.ShapeFactory;
import shapes.ShapeType;
import utils.ImageExporter;
import utils.SaveFile;
import utils.OpenFile;
import view.menu.EditMenuAction;
import view.menu.IEditMenu;
import view.menu.FileMenuAction;
import view.menu.IFileMenu;
import view.sidebar.IColorChange;
import view.sidebar.ISidebar;
import view.sidebar.ITopBar;
import view.sidebar.PaintAction;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by un4 on 08/11/16.
 */
public class PaintCanvas extends JComponent implements ISidebar, IEditMenu, IFileMenu, ITopBar, Observer {

    private MouseHandler mouseHandler = new MouseHandler();
    private Point endDragging;
    private Point startDragging;
    private ShapeModel shapeModel;
    private Color currentStrokeColor;
    private Color currentFillColor;
    private int strokeWidth;
    private ShapeType shapeType;
    private ShapeFactory shapeFactory;
    private PaintAction paintAction;
    private ShapeController shapeController;
    private int keyEvent = KeyEvent.VK_SHIFT;
    private IColorChange iColorChange;

    /**
     * This is a paont canvas and passes in a monad.
     *
     * @param shapeModel      shape model to be passed ti eht paint canvas.
     * @param shapeController
     */
    public PaintCanvas(ShapeModel shapeModel, ShapeController shapeController) {
        this.setBackground(Color.BLACK);
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
        this.shapeModel = shapeModel;
        this.shapeController = shapeController;
        this.shapeType = ShapeType.RECTANGLE;
        this.currentStrokeColor = Color.BLACK;
        this.strokeWidth = Shape.DEFAULT_STROKE;
        this.shapeFactory = new ShapeFactory();
        this.shapeModel.addObserver(this);
    }


    /**
     * This is responsible for the color change of the system.
     *
     * @param iColorChange this is i color interface to change.
     */
    public void setiColorChange(IColorChange iColorChange) {
        this.iColorChange = iColorChange;
    }

    /**
     * this returns the icolor change interface.
     *
     * @return this returns the interface.
     */
    public IColorChange getiColorChange() {
        return iColorChange;
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
        if (!shapeModel.getShapeList().isEmpty())
            this.shapeModel.getShapeList().forEach(shape -> drawShape(shape, graphics2D));
        Shape shape = getShapeModel().getHighlightedShape();
        if (shape != null && paintAction == null) {
            graphics2D.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.40f));
            drawShape(shape, graphics2D);
        } else if (paintAction == PaintAction.MOVE) {
            shape = getShapeModel().getMovableShape();
            if (shape != null) {
                this.drawShape(shape, graphics2D);
            }
        }
    }

    /**
     * gets the shape from the panel.
     *
     * @param shapeType     the shape type to be created
     * @param startDragging the origin of the shape.
     * @param endDragging   the end of the shape.
     * @return the shape requested.
     */
    private Shape getShape(ShapeType shapeType, Point startDragging, Point endDragging) {

        Rect rect = new Rect(startDragging, endDragging);
        Shape shape = shapeFactory.getShape(shapeType, rect, currentStrokeColor, currentFillColor);
        return shape;
    }

    /**
     * this draws the shape based on the graphics.
     *
     * @param shape    the shape to be drawn.
     * @param graphics the graphics object that draws the shape.
     */
    private void drawShape(Shape shape, Graphics2D graphics) {
//        graphics.transform(shape.getTransform());
//        graphics.setTransform(shape.getTransform());
        if (shape.getShapeType() == ShapeType.IMAGE) {
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, shape.getAlpha()));
            ImageShape imageShape = (ImageShape) shape;
            Point a = shape.getRect().getOrigin();
            Point b = shape.getRect().getEndPoint();
            graphics.drawImage(imageShape.getImage(), Math.min(a.x, a.x), Math.min(a.y, b.y), null);
        } else {
            if (shape.getStrokeColor() != null)
                graphics.setPaint(shape.getStrokeColor());
            graphics.setStroke(new BasicStroke(shape.getStrokeWidth()));
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, shape.getAlpha()));
            graphics.draw(shape.getShape());
            if (shape.getFillColor() != null) {
                graphics.setColor(shape.getFillColor());
                graphics.fill(shape.getShape());
            }
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
    public void changeColor(Color color, boolean stroke) {
        if (stroke) {
            this.currentStrokeColor = color;
            this.iColorChange.changeStrokeColor(color);
        } else {
            this.currentFillColor = color;
            this.iColorChange.changeFillColor(color);
        }
    }


    /**
     * Perform Paint actions.
     * TODO Should be splitted to two actions. Mouse Actions and instant actions or something.
     *
     * @param action
     * @return
     */
    @Override
    public boolean performAction(PaintAction action) {
        this.paintAction = action;
        return false;
    }


    @Override
    public void clearAll() {
        this.shapeController.clearAll();
    }


    /**
     * File menu actions.
     *
     * @param fileMenuAction file action to perform.
     */
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
            case OPENIMG:
                shapeController.createImageFromPath(OpenFile.getAFilePath());
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
//        SaveFile.SaveFile(this.shapeModel);
        SaveFile.WriteToFile(this.shapeModel);
    }

    /**
     * Opens the model file.
     */
    public void openFile() {
        ArrayList<Shape> openedFiles =  OpenFile.readFile();//importShapes();
        shapeController.addShapes(openedFiles);
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
//        this.shapeModel.undo();
        shapeController.undoAction();
    }


    public void redoAction() {
        shapeController.redoAction();
//        this.shapeModel.redo();
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
    private void willMoveShape(Point p) {
        this.shapeController.willMoveShapeAtPoint(p);
//        this.tempShape = shape != null ? shape : null;
    }

    /**
     * after shape has been moved.
     */
    private void didMoveShape() {
        this.shapeController.didMoveShapeToPoints();
    }

    /**
     * removes a shape at a point.
     *
     * @param p the point.
     */
    private void removeShape(Point p) {
        this.shapeController.removeShape(p);
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
                this.willMoveShape(point);
                break;
            case DELETE:
                this.removeShape(point);
                break;
            case FILL:
                this.fillShape(point);
                break;
            case STROKESHAPE:
                this.strokeShape(point);
                break;
            case ROTATE:
                this.willMoveShape(point);
                break;
            case SCALE:
                this.willMoveShape(point);
                break;
        }
    }

    /**
     * strokes a shape at the selected point.
     *
     * @param point
     */
    private void strokeShape(Point point) {
        shapeController.stroke(point, this.currentStrokeColor);
    }


    /**
     * TODO update controller.
     *
     * @param point
     */
    private void fillShape(Point point) {
        this.shapeController.fill(point, this.currentFillColor);
    }

    @Override
    public void update(Observable observable, Object o) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                repaint();
            }
        });
    }

    /**
     * mouse listener to be extended.
     */
    class MouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            startDragging = new Point(e.getX(), e.getY());// e.getPoint();
            endDragging = startDragging;
            performAction(paintAction, startDragging);
        }

        /**
         * When mouse is dragged my the mouse events.
         *
         * @param e mouse event.
         */
        @Override
        public void mouseDragged(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            Point point = new Point(x, y);
            endDragging = point;
            if (paintAction == null)
                shapeController.updateTempShape(shapeType, startDragging, point, strokeWidth);
            else {
                if (paintAction == PaintAction.MOVE) {
                    shapeController.isMovingShape(startDragging, endDragging);
//                    startDragging = point;
                } else if (paintAction == PaintAction.ROTATE) {
                    shapeController.isRotating(startDragging, endDragging);
//                    startDragging = point;
                } else if (paintAction == PaintAction.SCALE) {
                    shapeController.isScaling(startDragging, endDragging);
//                    startDragging = point;
                }
                startDragging = point;
            }
        }

        /**
         * this is when the mouse is released.
         *
         * @param e the event to be clicked on.
         */
        @Override
        public void mouseReleased(MouseEvent e) {
//            Point p = new Point(e.getX(), e.getY());
            if (paintAction == PaintAction.MOVE || paintAction == PaintAction.ROTATE || paintAction == PaintAction.SCALE) {
                didMoveShape();
            } else if (paintAction == null) {
                shapeController.createShape(shapeType, startDragging, endDragging, currentStrokeColor, currentFillColor, strokeWidth);
            }
            startDragging = null;
            endDragging = null;
        }
    }


}
