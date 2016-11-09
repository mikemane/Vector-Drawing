package view.sidebar;

import shapes.ShapeType;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
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

    public Sidebar() {
        this.setLayout(new GridLayout(7, 1));
        this.rectangle = createButton("rectangle.png");
        this.ellipse = createButton("ellipse.png");
        this.line = createButton("line.png");
        this.freeDrawing = createButton("brush.png");
        this.hexagon = createButton("hexagon.png");
        this.eraser = createButton("erase.png");
        this.hexagon = createButton("hexagon.png");
    }

    private JButton createButton(String path) {
        ImageIcon icon = new ImageIcon("icons/" + path);
        JButton button = new JButton(icon);
        button.setBackground(Color.LIGHT_GRAY );
        button.setSize(50,50);
        this.add(button);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JButton source = (JButton) actionEvent.getSource();
        if (source == rectangle)
            iSidebar.selectShape(ShapeType.RECTANGLE);
        if (source == ellipse)
            iSidebar.selectShape(ShapeType.ELLIPSE);
        if (source == line)
            iSidebar.selectShape(ShapeType.LINE);
        if (source == freeDrawing)
            iSidebar.selectShape(ShapeType.PATH);
        if (source == hexagon)
            iSidebar.selectShape(ShapeType.HEXAGON);
    }
}
