package main;


import view.guiview.CalcGUIView;
import model.CalcModel;
import model.ICalcModel;
import controller.CalcController;
import controller.ICalcController;

public class MVCMain {
    public static void main(String[] args) {
        //create Model
        ICalcModel model = new CalcModel();

        // Create controller
        ICalcController controller = new CalcController(model);

        // Create View (GUI)
        new CalcGUIView(model, controller);

    }
}
