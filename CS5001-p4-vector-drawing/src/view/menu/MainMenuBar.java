package view.menu;

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
     * @param IFileMenu the file menu delegate.
     */
    public void setFileMenuDelegate(IFileMenu IFileMenu) {
        this.fileMenu.setIFileMenu(IFileMenu);
    }

    /**
     * this represents the edit menu delegate.
     * @param IEditMenu the edit menu delegate.
     */
    public void setEditMenuDelegate(IEditMenu IEditMenu) {
        this.editMenu.setIEditMenu(IEditMenu);
    }

    /**
     * this initialises the menu items.
     */
    private void initMenus() {
        this.add(this.fileMenu);
        this.add(this.editMenu);
    }


}
