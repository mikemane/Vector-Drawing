package view;

import view.sidebar.Sidebar;

import javax.swing.JPanel;
import java.awt.*;

/**
 * Created by un4 on 09/11/16.
 */
public class MainPanel extends JPanel {

    private view.canvas.Canvas canvas;
    private Sidebar sidebar;
    private static final Insets insets = new Insets(0,0,0,0);

    public MainPanel(){
        this.setLayout(new BorderLayout());
//        this.setBackground();
        this.sidebar = new Sidebar();
        this.add(sidebar, BorderLayout.WEST);
//        addComponent(this,sidebar,0,0,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH);
        this.canvas = new view.canvas.Canvas();
        this.add(canvas,BorderLayout.CENTER);
    }

//    private void addComponent(Container container, Component component,
//                                     int gridx, int gridy, int gridwidth, int gridheight, int anchor,
//                                     int fill) {
//        this.constraints.gridwidth = gridwidth;
//        this.constraints.gridheight = gridheight;
//        this.constraints.gridy = gridy;
//        this.constraints.gridx = gridx;
//        this.constraints.anchor = anchor;
//        this.constraints.fill = fill;
//        this.constraints.insets = insets;
//        container.add(component, this.constraints);
//    }


    public view.canvas.Canvas getCanvas() {
        return canvas;
    }

    public Sidebar getSidebar() {
        return sidebar;
    }
}
