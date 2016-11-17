package view.menu;

import javax.swing.*;

/**
 * Created by un4 on 07/11/16.
 */
public class FileMenu extends JMenu {

    private int id;
    private JMenuItem openMenuItem;
    private JMenuItem openFileImage;
    private JMenuItem saveMenuItem;
    private JMenuItem exportMenuItem;
    private IFileMenu IFileMenu;

    /**
     * This is the constructor of the file menu.
     */
    public FileMenu() {
        this.setText("File");
        this.openMenuItem = new JMenuItem("Open File");
        this.openFileImage = new JMenuItem("Open Image");
        this.saveMenuItem = new JMenuItem("Save File");
        this.exportMenuItem = new JMenuItem("Export Image");
        this.add(openMenuItem);
        this.add(openFileImage);
        this.add(saveMenuItem);
        this.add(exportMenuItem);
        this.openMenuItem.addActionListener(e -> {
            if (IFileMenu != null) {
                IFileMenu.performFileAction(FileMenuAction.OPEN);
            }
        });
        this.openFileImage.addActionListener(e -> {
            if (IFileMenu != null) {
                IFileMenu.performFileAction(FileMenuAction.OPENIMG);
            }
        });
        this.saveMenuItem.addActionListener(e -> {
            if (IFileMenu != null) {
                IFileMenu.performFileAction(FileMenuAction.SAVE);
            }
        });
        this.exportMenuItem.addActionListener(e -> {
            if (IFileMenu != null) {
                IFileMenu.performFileAction(FileMenuAction.EXPORT);
            }
        });
    }

    /**
     * this sets the file menu delegate.
     *
     * @param IFileMenu the file menu delegate to set.
     */
    public void setIFileMenu(IFileMenu IFileMenu) {
        this.IFileMenu = IFileMenu;
    }

}
