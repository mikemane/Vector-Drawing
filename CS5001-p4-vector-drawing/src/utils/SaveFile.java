package utils;

import model.ShapeModel;
import shapes.Shape;

import javax.swing.*;
import java.io.*;

/**
 * Created by un4 on 11/11/16.
 */
public class SaveFile {

    /**
     * saves the model into a file.
     *
     * @param shapeModel shape model to be saved.
     * @return true if the file is saved successfully.
     */
    public static boolean SaveFile(ShapeModel shapeModel) {
        JFileChooser fileChooser = new JFileChooser();
        int approvedOption = fileChooser.showSaveDialog(fileChooser);
        if (approvedOption == JFileChooser.APPROVE_OPTION) {
            try {
                FileOutputStream fi = new FileOutputStream(fileChooser.getSelectedFile() + ".shp");
                OutputStreamWriter out = new OutputStreamWriter(fi);
                BufferedWriter b = new BufferedWriter(out);
                for (Shape shape : shapeModel.getShapeStack()) {
                    b.write(shape.toString());
                    b.newLine();
                }
                b.close();
                return true;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }


}
