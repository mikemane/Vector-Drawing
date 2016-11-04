package model;

import java.util.Observable;

public class CalcModel extends Observable implements ICalcModel {
	
	private double total;

	public CalcModel() { 
		this.total = 0.0;
		//this.setChanged();
	}

	
	private void update(){
		this.setChanged();
		this.notifyObservers();		
	}
	
	/* (non-Javadoc)
	 * @see Model.ICalcModel#reset()
	 */
	public void reset(){
		total = 0.0;
		update();
	}
	
	/* (non-Javadoc)
	 * @see Model.ICalcModel#add(double)
	 */
	public void add(double value) {
		total = total + value;
		update();
	}

	/* (non-Javadoc)
	 * @see Model.ICalcModel#subtract(double)
	 */
	public void subtract(double value) {
		total = total - value;
		update();
	}
	/* (non-Javadoc)
	 * @see Model.ICalcModel#multiply(double)
	 */
	public void multiply(double value) {
		total = total * value;
		update();
	}

	/* (non-Javadoc)
	 * @see Model.ICalcModel#divide(double)
	 */
	public void divide(double value) {
		total = total / value;
		update();
	}

	/* (non-Javadoc)
	 * @see Model.ICalcModel#getTotal()
	 */
	public double getTotal() {
		return total;
	}

}
