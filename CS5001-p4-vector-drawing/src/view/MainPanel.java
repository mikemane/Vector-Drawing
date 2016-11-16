package view;

import controller.ShapeController;
import model.ShapeModel;
import view.canvas.PaintCanvas;
import view.sidebar.Sidebar;
import view.sidebar.Topbar;

import javax.swing.JPanel;
import java.awt.*;

/**
 * Created by un4 on 09/11/16.
 */
public class MainPanel extends JPanel {

    private PaintCanvas paintCanvas;
    private Topbar topbar;
    private Sidebar sidebar;
    private static final Insets insets = new Insets(0, 0, 0, 0);

    /**
     * Main panel that represents the collation of all other panel.
     *
     * @param shapeModel shape model.
     * @param shapeController
     */
    public MainPanel(ShapeModel shapeModel, ShapeController shapeController) {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.paintCanvas = new PaintCanvas(shapeModel , shapeController);
        this.add(paintCanvas, BorderLayout.CENTER);
        this.sidebar = new Sidebar();
        sidebar.setiSidebar(paintCanvas);
        this.add(sidebar, BorderLayout.WEST);
        this.topbar = new Topbar();
        this.topbar.setiTopBar(paintCanvas);
        this.add(topbar, BorderLayout.NORTH);

    }

    /**
     * sets shape model.
     *
     * @param shapeModel sets the shape model.
     */
    public void setShapeModel(ShapeModel shapeModel) {
        this.paintCanvas.setShapeModel(shapeModel);
    }

    /**
     * gets the paint canvas.
     *
     * @return the paint  canvas.
     */
    public PaintCanvas getPaintCanvas() {
        return paintCanvas;
    }

    /**
     * Returns the sidebar.
     *
     * @return returns the side bar.
     */
    public Sidebar getSidebar() {
        return sidebar;
    }
}
