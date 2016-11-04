package controller;

import model.ICalcModel;

public class CalcController implements ICalcController{
	
	private ICalcModel model;

	
	public CalcController(ICalcModel model) {
		this.model = model;
	}
		
	
	public void controlClear() {
		model.reset();
	}
	
	public void controlSubtract(String s) {
		try{
			model.subtract(Double.parseDouble(s));
		} catch (NumberFormatException e){ }
	}

	public void controlAdd(String s) {
		try{
			model.add(Double.parseDouble(s));
		} catch (NumberFormatException e){ }
	}

	public void controlMultiply(String s) {
		try{
			model.multiply(Double.parseDouble(s));
		} catch (NumberFormatException e){ }
	}

	public void controlDivide(String s) {
		try{
			model.divide(Double.parseDouble(s));
		} catch (NumberFormatException e){ }
	}
}

