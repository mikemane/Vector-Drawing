package view;

import javax.swing.*;

/**
 * Created by un4 on 07/11/16.
 */
public class EditMenu extends JMenu implements EditMenuDelegate {


    private JMenuItem undo;
    private JMenuItem redo;


    public EditMenu() {
        this.setText("Edit");
        this.undo = new JMenuItem("Un-do");
        this.redo = new JMenuItem("Re-do");
        initMenus();
    }


    public void setFileMenuDelegate(FileMenuDelegate fileMenuDelegate) {

    }

    private void initMenus() {
        this.add(undo);
        this.add(redo);
    }


    @Override
    public boolean undoAction() {
        return false;
    }

    @Override
    public boolean redoAction() {
        return false;
    }
}
