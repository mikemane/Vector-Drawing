package model;

import java.awt.*;
import java.util.Observable;

/**
 * Created by un4 on 04/11/16.
 */
abstract public class Shape extends Observable {

    private static final Color DEFAULT_COLOR = Color.BLUE;
    private Color color;

    /**
     * Constructor that takes in a parameter of a color. that sets the default color of the shape.
     * @param color
     */
    public Shape(Color color){
        this.color = color;
    }

    /**
     * This is a shape costructor that takes no parameter and sets the default color to blue.
     */
    public Shape(){
        this.color = DEFAULT_COLOR;
    }

    /**
     * Returns the color of the shape.
     * @return the color of the shape.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Updates Models preferences.
     */
    private void update(){
        this.setChanged();
        this.notifyObservers();
    }
}
