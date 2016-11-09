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

    public MainMenuBar(JMenu jMenu, FileMenu fileMenu, EditMenu editMenu) {
        this.editMenu = editMenu;
        this.fileMenu = fileMenu;
        this.initMenus();
    }

    public void setFileMenuDelegate(FileMenuDelegate fileMenuDelegate) {
        this.fileMenu.setFileMenuDelegate(fileMenuDelegate);
    }

    public void setEditMenuDelegate(EditMenuDelegate editMenuDelegate) {
        this.editMenu.setEditMenuDelegate(editMenuDelegate);
    }

    private void initMenus() {
        this.add(this.fileMenu);
        this.add(this.editMenu);
    }


}
