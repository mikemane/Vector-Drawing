package view.menu;

import view.menu.EditMenu;
import view.menu.EditMenuDelegate;
import view.menu.FileMenu;
import view.menu.FileMenuDelegate;

import javax.swing.*;

/**
 * Created by un4 on 07/11/16.
 */
public class MainMenuBar extends JMenuBar {

    //    private
    private FileMenu fileMenu;
    private EditMenu editMenu;

    public MainMenuBar() {
        this.fileMenu = new FileMenu();
        this.editMenu = new EditMenu();
        this.initMenus();
    }

    /**
     * This is the main menu bar.
     * @param fileMenu this is the file menu.
     * @param editMenu this is the edit menu.
     */
    public MainMenuBar(FileMenu fileMenu, EditMenu editMenu) {
        this.editMenu = editMenu;
        this.fileMenu = fileMenu;
        this.initMenus();
    }

    /**
     * sets the file menu delegate.
     * @param fileMenuDelegate the file menu delegate.
     */
    public void setFileMenuDelegate(FileMenuDelegate fileMenuDelegate) {
        this.fileMenu.setFileMenuDelegate(fileMenuDelegate);
    }

    /**
     * this represents the edit menu delegate.
     * @param editMenuDelegate the edit menu delegate.
     */
    public void setEditMenuDelegate(EditMenuDelegate editMenuDelegate) {
        this.editMenu.setEditMenuDelegate(editMenuDelegate);
    }

    /**
     * this initialises the menu items.
     */
    private void initMenus() {
        this.add(this.fileMenu);
        this.add(this.editMenu);
    }


}
