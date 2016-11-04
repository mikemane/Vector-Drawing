package uk.ac.stand.cs.cs5001.gwttest.server.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;

/**
 * A simple model class whose purpose is to store text entered by a user.
 * 
 * The model's state is altered by user input when the delegate calls the addText()
 * method below. 
 * 
 * The model extends the Observable class and is observed by the Delegate class. This form
 * of loose coupling permits the Delegate (View) to be updated when the model has changed.
 * 
 * @author jonl
 *
 */
public class MyModel extends Observable {

	/**
	 * the text to store
	 */
	private StringBuffer text;
	
	private FileWriter fw;
	private PrintWriter pw;

	static private MyModel instance = null; 
	
	
	public static MyModel getInstance(){
		if (instance == null){
			instance = new MyModel();
		}
		return instance;
	}
	
	/**
	 * Constructs a new SimpleModel instance.
	 * Initialises the StringBuffer.
	 */
	private MyModel(){
		text = new StringBuffer();
		try{
			fw = new FileWriter("log.txt");	
			pw = new PrintWriter(fw);
		} catch (IOException ioe){
			System.out.println(ioe.getMessage());
		}
	}
	
	/**
	 * This method is called by the GUI control aspect of the delegate to update the model according to user input
	 * The method adds the text entered by the user to its buffer and calls setChanged() and notifiyObservers()
	 * so that the GUI view is notified that the model has changed and can update the display
	 * @param str the string to add to the buffer
	 */
	public void addText(String str){
		text.append(str + "\n");
		pw.println(str);
		pw.flush();
		setChanged();
		notifyObservers();
		
	}
	
	
	/**
	 * This method is needed by the GUI to get hold of model state when updating the output text area in the GUI.
	 * @return the content of the model's text buffer
	 */
	public String getText(){
		return text.toString();
	}
	
	
}
