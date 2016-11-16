package shapes;

import base.Rect;

import java.awt.*;

/**
 * Created by un4 on 15/11/16.
 */
public class ImageShape extends Shape {

    private String path;
    private Image image;

    public ImageShape(Rect rect){
        super(rect);
    }

    @Override
    public void updateShape() {

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
