package view;

import controller.ShapeController;
import model.ShapeModel;

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
public class ShapeView implements Observer, ActionListener, FileMenuDelegate {

    private static int FRAME_WIDTH = 500;
    private static int FRAME_HEIGHT = 500;


    private ShapeModel shapeModel;
    private ShapeController shapeController;

    ///delegates.
    private FileMenuDelegate fileMenuDelegate;
    private EditMenuDelegate editMenuDelegate;


    private JFrame mainFrame;
    private JPanel panel; // The main panel.
    private GridBagConstraints gridBagConstraints; // Constraints for the panel.
    private JMenuBar mainMenuBar;


    public ShapeView(ShapeModel shapeModel, ShapeController shapeController) {
        this.shapeModel = shapeModel;
        this.shapeController = shapeController;

        //SetUpMenuBar
        mainMenuBar = new MainMenuBar();

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
        switch (actionEvent.getID()) {

        }
    }

    @Override
    public void update(Observable observable, Object o) {

    }

    @Override
    public void openFile() {
        System.out.println("I am Michael");
    }
}
