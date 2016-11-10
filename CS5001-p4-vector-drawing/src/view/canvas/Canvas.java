package view.canvas;

import base.Rect;
import model.ShapeModel;
import shapes.Shape;
import shapes.ShapeFactory;
import shapes.ShapeType;
import view.menu.EditMenuDelegate;
import view.menu.FileMenuDelegate;
import view.sidebar.ISidebar;
import view.sidebar.ITopBar;

import javax.swing.JComponent;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;

/**
 * Created by un4 on 08/11/16.
 */
public class Canvas extends JComponent implements ISidebar , EditMenuDelegate , FileMenuDelegate , ITopBar {

    private MouseHandler mouseHandler = new MouseHandler();
    private Point endDragging;
    private Point startDragging;
    private ShapeModel shapeModel;
    private Color currentColor;
    private Shape currentShape;
    private int strokeWidth;
    private ShapeType shapeType;
    private ShapeFactory shapeFactory;


    public Canvas(ShapeModel shapeModel) {
        this.setBackground(Color.BLACK);
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
        this.shapeModel = shapeModel;
        this.shapeType = ShapeType.RECTANGLE;
        this.currentColor = Color.BLACK;
        this.strokeWidth = Shape.DEFAULT_STROKE;
        this.shapeFactory = new ShapeFactory();
    }

    @Override
    protected void paintComponent(Graphics g) {
//        g.drawImage(backgroundImage, 0, 0, null);
    }

    public void setShapeModel(ShapeModel model) {
        this.shapeModel = model;
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        paintBackground(graphics2D);
        Color[] colors = {Color.YELLOW, Color.MAGENTA, Color.CYAN, Color.RED, Color.BLUE, Color.PINK};
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        this.shapeModel.getShapeStack().forEach(shape -> {
            graphics2D.setStroke(new BasicStroke(shape.getStrokeWidth()));
            graphics2D.setPaint(shape.getColor());
            graphics2D.draw(shape.getShape());
        });

        if (startDragging != null && endDragging != null) {
            // Makes the guide shape transparent
            graphics2D.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.40f));
            // Make guide shape gray for professional look
            graphics2D.setPaint(Color.LIGHT_GRAY);
            Shape aShape = getShape(this.shapeType, startDragging, endDragging);
            graphics2D.draw(aShape.getShape());
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
    }

    /**
     * Erases the shape Type.
     */
    @Override
    public void erase() {

    }

    @Override
    public void changeColor(Color color) {
        this.currentColor = color;
    }

    @Override
    public void openFile() {

    }

    @Override
    public void saveFile() {

    }

    @Override
    public boolean undoAction() {
        ;
        this.shapeModel.undo();
        this.repaint();
        return true;
    }

    @Override
    public boolean redoAction() {
        this.shapeModel.redo();
        this.repaint();
        return true;
    }

    @Override
    public void changeStrokeWidth(int value) {
        this.strokeWidth = value;
    }

    class MouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            startDragging = new Point(e.getX(), e.getY());// e.getPoint();
            endDragging = startDragging;
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            endDragging = new Point(e.getX(), e.getY());
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            currentShape = getShape(shapeType, startDragging, endDragging);
            currentShape.setStrokeWidth(strokeWidth);
            shapeModel.addShape(currentShape);
            startDragging = null;
            endDragging = null;
            repaint();
        }
    }

    private Shape getShape(ShapeType shapeType, Point origin, Point end) {
        Rect rect = new Rect(origin, end);
        Shape shape = shapeFactory.getShape(shapeType, rect, currentColor);
        return shape;
    }

//    private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
//        return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
//    }
}
