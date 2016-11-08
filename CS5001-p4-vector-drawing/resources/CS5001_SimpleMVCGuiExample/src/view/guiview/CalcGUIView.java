package view.guiview;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

import model.ICalcModel;

import controller.ICalcController;

public class CalcGUIView implements Observer, ActionListener {

    private ICalcModel model;
    private ICalcController controller;

    private JFrame mainFrame;
    private JPanel calcViewPanel;
    private JPanel calcControlPanel;

    private static int DEFAULT_FRAME_WIDTH = 400;
    private static int DEFAULT_FRAME_HEIGHT = 128;

    protected static String BUTTON_CLEAR_COMMAND = "C";
    protected static String BUTTON_ADD_COMMAND = "+";
    protected static String BUTTON_SUBTRACT_COMMAND = "-";
    protected static String BUTTON_MULTIPLY_COMMAND = "*";
    protected static String BUTTON_DIVIDE_COMMAND = "/";

    private JButton clearButton;
    private JButton addButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JTextField valueField;
    private JTextField totalField;


    public CalcGUIView(ICalcModel model, ICalcController controller) {
        this.model = model;
        this.controller = controller;

        // create frame for view of sheep and control buttons
        mainFrame = new JFrame("Simple MVC Calculator");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT); // set frame size
        mainFrame.setVisible(true); // display frame

        calcControlPanel = new JPanel();
        calcViewPanel = new JPanel();

        mainFrame.getContentPane().add(calcViewPanel, BorderLayout.CENTER); // add grid to centre of frame
        mainFrame.getContentPane().add(calcControlPanel, BorderLayout.SOUTH); // add buttons to bottom of frame

        addControlElements();
        addViewElements();

        // Add the GUI as a listener for the GUI buttons and observer of model updates
        addActionListenerForButtons(this);
        ((Observable) model).addObserver(this);

        mainFrame.paintAll(mainFrame.getGraphics());
        mainFrame.pack();
    }


    private void addViewElements() {
        totalField = new JTextField(20);
        calcViewPanel.add(totalField);
        totalField.setText("" + model.getTotal());
        totalField.setEditable(false);
    }


    private void addControlElements() {
        clearButton = new JButton(BUTTON_CLEAR_COMMAND);
        addButton = new JButton(BUTTON_ADD_COMMAND);
        subtractButton = new JButton(BUTTON_SUBTRACT_COMMAND);
        multiplyButton = new JButton(BUTTON_MULTIPLY_COMMAND);
        divideButton = new JButton(BUTTON_DIVIDE_COMMAND);
        valueField = new JTextField(10);
        calcControlPanel.add(clearButton);
        calcControlPanel.add(addButton);
        calcControlPanel.add(subtractButton);
        calcControlPanel.add(multiplyButton);
        calcControlPanel.add(divideButton);
        calcControlPanel.add(valueField);
    }

    public void addActionListenerForButtons(ActionListener al) {
        clearButton.addActionListener(al);
        addButton.addActionListener(al);
        subtractButton.addActionListener(al);
        multiplyButton.addActionListener(al);
        divideButton.addActionListener(al);
    }


    public void update(Observable arg0, Object arg1) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                totalField.setText("" + model.getTotal());
                mainFrame.repaint();
            }
        });
    }

    // Could have instantiated separate ActionListeners (in anonymous inner classes)
    // for each button. This shows an alternative in which we use a single listener
    // and check the source of the event to decide what to do
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {
            controller.controlClear();
        } else if (e.getSource() == addButton) {
            controller.controlAdd(valueField.getText());
        } else if (e.getSource() == subtractButton) {
            controller.controlSubtract(valueField.getText());
        } else if (e.getSource() == multiplyButton) {
            controller.controlMultiply(valueField.getText());
        } else if (e.getSource() == divideButton) {
            controller.controlDivide(valueField.getText());
        }
    }
}


