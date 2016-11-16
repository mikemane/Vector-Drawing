package utils;

import model.ShapeModel;
import shapes.Shape;

import javax.swing.JFileChooser;
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
                for (Shape shape : shapeModel.getShapeList()) {
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


    /**
     * this writes to the file.
     *
     * @param model this is the model to write towards.
     */
    public static void WriteToFile(ShapeModel model) {
        JFileChooser fileChooser = new JFileChooser();
        int approvedOption = fileChooser.showSaveDialog(fileChooser);
        if (approvedOption == JFileChooser.APPROVE_OPTION) {
            try {
                FileOutputStream fi = new FileOutputStream(fileChooser.getSelectedFile() + ".shp");
                ObjectOutputStream oos = new ObjectOutputStream(fi);
                oos.writeObject(model.getShapeList());
                oos.close();
                fi.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}