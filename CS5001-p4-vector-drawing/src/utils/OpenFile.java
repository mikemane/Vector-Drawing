package utils;

import base.Rect;
import shapes.Shape;
import shapes.ShapeFactory;
import shapes.ShapeType;
import utils.filefilters.PNGFilter;
import utils.filefilters.ShpFilter;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

/**
 * Created by un4 on 11/11/16.
 */
public class OpenFile {


    /**
     * imporys a  series of shaps and adds it to rhe current pane or canvasa.
     *
     * @return the imported Shapes.
     */
    public static Collection<Shape> importShapes() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new ShpFilter());
        fileChooser.addChoosableFileFilter(new PNGFilter());
        Collection<Shape> shapes = new ArrayList<>();
        ShapeFactory shapeFactory = new ShapeFactory();
        int option = fileChooser.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            if (fileChooser.getSelectedFile().getName().endsWith(".png")) {

            } else if (fileChooser.getSelectedFile().getName().endsWith(".shp")) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] params = line.split(";");
                        ShapeType shapeType = ShapeType.getTypeFromString(params[0]);
                        Rect rect = new Rect(Integer.parseInt(params[1]), Integer.parseInt(params[2]), Integer.parseInt(params[3]), Integer.parseInt(params[4]));
                        Color strokeColor = new Color(Integer.parseInt(params[5]), Integer.parseInt(params[6]), Integer.parseInt(params[7]));
                        Color fillColor;
                        if (params[8].equals("null")) {
                            fillColor = null;
                        } else {
                            fillColor = new Color(Integer.parseInt(params[8]), Integer.parseInt(params[9]), Integer.parseInt(params[10]));
                        }
                        Shape newShape = shapeFactory.getShape(shapeType, rect, strokeColor, fillColor);
                        shapes.add(newShape);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    return null;
                }
            }
        }
        return shapes;
    }
}
