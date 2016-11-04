package view;

import controller.ShapeController;
import model.ShapeFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by un4 on 04/11/16.
 */
public class ShapeView implements Observer, ActionListener {

    private static int FRAME_WIDTH = 500;
    private static int FRAME_HEIGHT = 500;


    private ShapeFactory shapeFactory;
    private ShapeController shapeController;

    private JFrame mainFrame;
    private JPanel panel; // The main panel.
    private GridBagConstraints gridBagConstraints; // Constraints for the panel.
    private JMenuBar mainMenuBar;


    public ShapeView(ShapeFactory shapeFactory, ShapeController shapeController) {
        this.shapeFactory = shapeFactory;
        this.shapeController = shapeController;

        //SetUpMenuBar
        mainMenuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(createMenuItem("open", null, null));
        mainMenuBar.add(fileMenu); // adds file menu.

        panel = new JPanel(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        this.mainFrame = new JFrame("Vector Drawing");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.mainFrame.setJMenuBar(this.mainMenuBar);
        this.mainFrame.setVisible(true);
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        return panel;
    }

    private void addMenuItem(JMenuItem menuItem) {
        this.mainMenuBar.add(menuItem);
    }

    private JMenuItem createMenuItem(String menuName, KeyEvent keyEvent, ActionListener actionListener) {
        JMenuItem menuItem = new JMenuItem(menuName);
        if (keyEvent != null) {
            menuItem.setMnemonic(keyEvent.getKeyChar());
        }
        menuItem.addActionListener(actionListener);
        return menuItem;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
