package view.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by un4 on 07/11/16.
 */
public class FileMenu extends JMenu implements ActionListener {

    private int id;
    private JMenuItem openMenuItem;
    private FileMenuDelegate fileMenuDelegate;

    public FileMenu() {
        this.setText("File");
        this.openMenuItem = new JMenuItem("Open");
        this.add(openMenuItem);
        this.openMenuItem.addActionListener(this);
    }

    public void setFileMenuDelegate(FileMenuDelegate fileMenuDelegate) {
        this.fileMenuDelegate = fileMenuDelegate;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (openMenuItem.equals(actionEvent.getSource())) {
            if (fileMenuDelegate != null) {
                fileMenuDelegate.openFile();
            }
        }
    }
}
