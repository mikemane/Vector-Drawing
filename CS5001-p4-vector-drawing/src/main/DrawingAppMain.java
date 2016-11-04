package main;

import controller.ShapeController;
import model.ShapeFactory;
import view.ShapeView;

/**
 * Created by un4 on 04/11/16.
 */
public class DrawingAppMain {

    /**
     * Main function that executes the programme.
     * @param args takes in an array of command line parameters.
     */
    public static void main(String[] args) {
        //Shape factory.
        ShapeFactory shapeFactory = new ShapeFactory();
        //Shape Controller.
        ShapeController shapeController = new ShapeController();
        // Shape view.
        new ShapeView(shapeFactory,shapeController);
    }
}
