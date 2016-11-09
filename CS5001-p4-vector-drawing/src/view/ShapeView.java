package view;

import model.ShapeModel;
import view.menu.EditMenuDelegate;
import view.menu.FileMenuDelegate;
import view.menu.MainMenuBar;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

import view.canvas.Canvas;
import view.sidebar.Sidebar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by un4 on 04/11/16.
 */
public class ShapeView implements Observer, ActionListener, FileMenuDelegate, EditMenuDelegate {

    private static int FRAME_WIDTH = 750;
    private static int FRAME_HEIGHT = 750;


    private ShapeModel shapeModel;

    ///delegates.
    private FileMenuDelegate fileMenuDelegate;
    private EditMenuDelegate editMenuDelegate;


    private JFrame mainFrame;

    private MainPanel mainPanel;
    private Canvas canvas;
    private Sidebar sidebar;

    private MainMenuBar mainMenuBar;
    /**
     * Shape Mode
     *
     * @param shapeModel
     */
    public ShapeView(ShapeModel shapeModel) {
        this.shapeModel = shapeModel;

        //SetUpMenuBar
        mainMenuBar = new MainMenuBar();
        mainMenuBar.setFileMenuDelegate(this);
        mainMenuBar.setEditMenuDelegate(this);


        mainPanel = new MainPanel();
        mainPanel.setPreferredSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));

        this.mainFrame = new JFrame("Vector Drawing");

        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.mainFrame.setJMenuBar(this.mainMenuBar);
        this.mainFrame.add(mainPanel);
        this.mainFrame.pack();
        this.mainFrame.setVisible(true);
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


    // Menu delegates
    @Override
    public void openFile() {
        System.out.println("I am Michael");
    }

    @Override
    public void saveFile() {

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
