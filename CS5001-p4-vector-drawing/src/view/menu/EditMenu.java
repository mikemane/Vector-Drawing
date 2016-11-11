package view.menu;

import javax.swing.*;

/**
 * Created by un4 on 07/11/16.
 */
public class EditMenu extends JMenu {


    private JMenuItem undo;
    private JMenuItem redo;
    private EditMenuDelegate editMenuDelegate;


    /**
     * Edit menu constructor.
     */
    public EditMenu() {
        this.setText("Edit");
        this.undo = new JMenuItem("Un-do");
        this.redo = new JMenuItem("Re-do");

        undo.addActionListener(e -> {
            if (editMenuDelegate != null) {
                editMenuDelegate.performEditAction(EditMenuAction.UNDO);
            }
        });
        redo.addActionListener(e -> {
            if (editMenuDelegate != null) {
                editMenuDelegate.performEditAction(EditMenuAction.REDO);
            }
        });
        initMenus();
    }


    /**
     * Sets the edit menu delegate to perform actions.
     * @param editMenuDelegate the edit menu delegate
     */
    public void setEditMenuDelegate(EditMenuDelegate editMenuDelegate) {
        this.editMenuDelegate = editMenuDelegate;
    }

    /**
     * Initialises the menu items.
     */
    private void initMenus() {
        this.add(undo);
        this.add(redo);
    }
}
