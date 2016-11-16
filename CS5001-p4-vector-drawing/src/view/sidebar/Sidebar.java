package view.sidebar;

import shapes.ShapeType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by un4 on 08/11/16.
 */
public class Sidebar extends JPanel implements ActionListener {

    private static final int SIZE = 5;
    private ISidebar iSidebar;

    private JButton rectangle;
    private JButton ellipse;
    private JButton line;
    private JButton freeDrawing;
    private JButton hexagon;
    private JButton eraser;
    private JButton fill;
    private JButton move;
    private JButton strokeShape;
    private JButton clearAll;


    /**
     * Sidebar constructor.
     */
    public Sidebar() {
        this.setLayout(new GridLayout(10, 3));
        this.rectangle = createButton("rectangle.png");
        this.ellipse = createButton("ellipse.png");
        this.line = createButton("line.png");
        this.hexagon = createButton("hexagon.png");
        this.eraser = createButton("trash.png");
        this.move = createButton("move.png");
        this.fill = createButton("paint.png");
        this.strokeShape = createButton("stroke.png");
        this.clearAll = createButton("clear.png");
    }

    /**
     * creates buttons.
     *
     * @param path path of the image icon.
     * @return the image icon.
     */
    private JButton createButton(String path) {
        ImageIcon icon = new ImageIcon("icons/" + path);
        JButton button = new JButton(icon);
        button.setBackground(Color.LIGHT_GRAY);
        button.setSize(50, 50);
        button.addActionListener(this);
        this.add(button);
        return button;
    }

    /**
     * Sets the class to implement this interface.
     *
     * @param iSidebar Delegate.
     */
    public void setiSidebar(ISidebar iSidebar) {
        this.iSidebar = iSidebar;
    }

    /**
     * Isidebar delegate.
     *
     * @param actionEvent action performed.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JButton source = (JButton) actionEvent.getSource();
        if (source == rectangle)
            iSidebar.selectShape(ShapeType.RECTANGLE);
        else if (source == ellipse)
            iSidebar.selectShape(ShapeType.ELLIPSE);
        else if (source == line)
            iSidebar.selectShape(ShapeType.LINE);
        else if (source == hexagon)
            iSidebar.selectShape(ShapeType.HEXAGON);
        else if (source == move)
            iSidebar.performAction(PaintAction.MOVE);
        else if (source == fill) {
            iSidebar.changeColor(JColorChooser.showDialog(null, "Pick a color", Color.BLACK), false);
            iSidebar.performAction(PaintAction.FILL);
        } else if (source == strokeShape) {
            iSidebar.changeColor(JColorChooser.showDialog(null, "Pick a color", Color.BLACK), true);
            iSidebar.performAction(PaintAction.STROKESHAPE);
        } else if (source == clearAll)
            iSidebar.clearAll();
        else if (source == eraser) {
            iSidebar.performAction(PaintAction.DELETE);
        }
    }
}
