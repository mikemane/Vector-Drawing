package view.sidebar;

import shapes.ShapeType;

import java.awt.Color;

/**
 * Created by un4 on 08/11/16.
 */
public interface ISidebar {

    /**
     * Select a shape type.
     *
     * @param shapeType this is the shape type;
     */
    void selectShape(ShapeType shapeType);

    void changeColor(Color color, boolean stroke);

    boolean performAction(PaintAction action);

    void clearAll();

}
