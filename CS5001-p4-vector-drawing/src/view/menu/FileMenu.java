package view.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by un4 on 07/11/16.
 */
public class FileMenu extends JMenu {

    private int id;
    private JMenuItem openMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem exportMenuItem;
    private FileMenuDelegate fileMenuDelegate;

    /**
     * This is the constructor of the file menu.
     */
    public FileMenu() {
        this.setText("File");
        this.openMenuItem = new JMenuItem("Open File");
        this.saveMenuItem = new JMenuItem("Save Image");
        this.exportMenuItem = new JMenuItem("Export Image");
        this.add(openMenuItem);
        this.add(saveMenuItem);
        this.add(exportMenuItem);
        this.openMenuItem.addActionListener(e -> {
            if (fileMenuDelegate != null) {
                fileMenuDelegate.performFileAction(FileMenuAction.OPEN);
            }
        });
        this.saveMenuItem.addActionListener(e -> {
            if (fileMenuDelegate != null) {
                fileMenuDelegate.performFileAction(FileMenuAction.SAVE);
            }
        });
        this.exportMenuItem.addActionListener(e -> {
            if (fileMenuDelegate != null) {
                fileMenuDelegate.performFileAction(FileMenuAction.EXPORT);
            }
        });
    }

    /**
     * this sets the file menu delegate.
     *
     * @param fileMenuDelegate the file menu delegate to set.
     */
    public void setFileMenuDelegate(FileMenuDelegate fileMenuDelegate) {
        this.fileMenuDelegate = fileMenuDelegate;
    }

}
