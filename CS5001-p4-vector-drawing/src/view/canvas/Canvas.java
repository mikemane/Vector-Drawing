package view.canvas;

import shapes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by un4 on 08/11/16.
 */
public class Canvas extends JPanel {

    boolean drawing = false;
    GeneralPath path;
    MouseHandler mouseHandler = new MouseHandler();
    Point endDragging;
    Point startDragging;
    ArrayList<Shape> shapes;
    Shape currentShape;
    Shape highlightShape;


    public Canvas(Dimension dimension) {
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }

    public Canvas() {
        this.setBackground(Color.BLACK);
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
        this.shapes = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paintBackground(graphics2D);
        Color[] colors = {Color.YELLOW, Color.MAGENTA, Color.CYAN, Color.RED, Color.BLUE, Color.PINK};
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));
        int counter = 0;
        this.shapes.forEach(shape -> {
            graphics2D.setPaint(Color.BLACK);
            graphics2D.draw(shape);
            graphics2D.setPaint(colors[0]);
            graphics2D.fill(shape);
        });

//        graphics2D.draw(currentShape);
//        graphics2D.fill(currentShape);


        if (startDragging != null && endDragging != null) {
            graphics2D.setPaint(Color.LIGHT_GRAY);
            Shape shape = this.makeRectangle(startDragging.x, startDragging.y, endDragging.x, endDragging.y);
            graphics2D.draw(shape);
        }
    }

    private void paintBackground(Graphics2D g2){
        g2.setPaint(Color.LIGHT_GRAY);
        for (int i = 0; i < getSize().width; i += 10) {
            Shape line = new Line2D.Float(i, 0, i, getSize().height);
            g2.draw(line);
        }

        for (int i = 0; i < getSize().height; i += 10) {
            Shape line = new Line2D.Float(0, i, getSize().width, i);
            g2.draw(line);
        }


    }

    class MouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            startDragging = new Point(e.getX() , e.getY());// e.getPoint();
            endDragging = startDragging;
            currentShape = makeRectangle(startDragging.x, startDragging.y, endDragging.x, endDragging.y);
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            endDragging = new Point(e.getX(), e.getY());
//            currentShape.
            currentShape = makeRectangle(startDragging.x, startDragging.y, endDragging.x, endDragging.y);
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            shapes.add(currentShape);
            startDragging = null;
            endDragging = null;
            repaint();
        }
    }

    private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
        return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }
}
