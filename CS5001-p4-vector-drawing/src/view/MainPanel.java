package view;

import model.ShapeModel;
import view.sidebar.Sidebar;
import view.sidebar.Topbar;

import javax.swing.JPanel;
import java.awt.*;

/**
 * Created by un4 on 09/11/16.
 */
public class MainPanel extends JPanel {

    private view.canvas.Canvas canvas;
    private Topbar topbar;
    private Sidebar sidebar;
    private static final Insets insets = new Insets(0, 0, 0, 0);

    public MainPanel(ShapeModel shapeModel) {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.canvas = new view.canvas.Canvas(shapeModel);
        this.add(canvas, BorderLayout.CENTER);
                this.sidebar = new Sidebar();
        sidebar.setiSidebar(canvas);
        this.add(sidebar, BorderLayout.WEST);
        this.topbar = new Topbar();
        this.topbar.setiTopBar(canvas);
        this.add(topbar,BorderLayout.NORTH);

    }

    public void setShapeModel(ShapeModel shapeModel){
        this.canvas.setShapeModel(shapeModel);
    }


    public view.canvas.Canvas getCanvas() {
        return canvas;
    }

    public Sidebar getSidebar() {
        return sidebar;
    }
}
