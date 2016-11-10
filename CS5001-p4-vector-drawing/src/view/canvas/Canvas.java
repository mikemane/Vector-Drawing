package view.canvas;

import base.Rect;
import model.ShapeModel;
import shapes.Shape;
import shapes.ShapeFactory;
import shapes.ShapeType;
import utils.ImageExporter;
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
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by un4 on 08/11/16.
 */
public class Canvas extends JComponent implements ISidebar, EditMenuDelegate, FileMenuDelegate, ITopBar {

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
    Graphics2D graphics;


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
        this.graphics = graphics2D;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        paintBackground(graphics2D);
        Color[] colors = {Color.YELLOW, Color.MAGENTA, Color.CYAN, Color.RED, Color.BLUE, Color.PINK};
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        this.shapeModel.getShapeStack().forEach(shape -> {
            graphics2D.setStroke(new BasicStroke(shape.getStrokeWidth()));
            graphics2D.setPaint(shape.getColor());
            graphics2D.draw(shape.getShape());
            if (shape.isShouldFill())
                graphics2D.fill(shape.getShape());
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

    private void saveFile() {
    }

    public void openFile() {

    }

    public void exportFile() {
//        String path = JFileChooser.
        JFileChooser fileChooser = new JFileChooser();
        int saveAFile = fileChooser.showSaveDialog(fileChooser);
        if (saveAFile == JFileChooser.APPROVE_OPTION) {
//            BufferedImage bufferedImage = ImageExporter.createImage(this);
            BufferedImage bufferedImage = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D saveGraphics = bufferedImage.createGraphics();
            saveGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            saveGraphics.setColor(Color.white);
            saveGraphics.fillRect(0, 0, getSize().width, getSize().height);
            shapeModel.getShapeStack().forEach(shape -> {
                saveGraphics.setStroke(new BasicStroke(shape.getStrokeWidth()));
                saveGraphics.setPaint(shape.getColor());
                saveGraphics.draw(shape.getShape());
                if (shape.isShouldFill())
                    saveGraphics.fill(shape.getShape());
            });
            //TODO save file here


            File file = fileChooser.getSelectedFile();
            ImageExporter.save(bufferedImage, file.toString(), ImageExporter.Filetype.PNG);
        }
    }


    public boolean undoAction() {
        this.shapeModel.undo();
        this.repaint();
        return true;
    }


    public boolean redoAction() {
        this.shapeModel.redo();
        this.repaint();
        return true;
    }

    @Override
    public void changeStrokeWidth(int value) {
        this.strokeWidth = value;
    }


    private void moveShape(Point p) {
        for (Shape s : shapeModel.getShapeStack()) {
            if (s.contains(p)) {
                tempShape = s;
                shapeModel.removeShape(s);
                break;
            }
        }

    }

    private void removeShape(Point p) {
        for (Shape shape : shapeModel.getShapeStack()) {
            if (shape.contains(p)) {
                shapeModel.removeShape(shape);
                break;
            }
        }
    }

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
                shapeModel.fill(shape);
                break;
            }
        }
    }

    class MouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            startDragging = new Point(e.getX(), e.getY());// e.getPoint();
            endDragging = startDragging;
            performAction(paintAction, startDragging);
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            endDragging = new Point(e.getX(), e.getY());
            repaint();
        }

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

    private Shape getShape(ShapeType shapeType, Point origin, Point end) {
        Rect rect = new Rect(origin, end);
        Shape shape = shapeFactory.getShape(shapeType, rect, currentColor);
        return shape;
    }
}
