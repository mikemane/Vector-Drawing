package uk.ac.stand.cs.cs5001.gwttest.client;

import uk.ac.stand.cs.cs5001.gwttest.client.servicestubs.AddTextService;
import uk.ac.stand.cs.cs5001.gwttest.client.servicestubs.AddTextServiceAsync;
import uk.ac.stand.cs.cs5001.gwttest.client.servicestubs.GetStateService;
import uk.ac.stand.cs.cs5001.gwttest.client.servicestubs.GetStateServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CS5001_Simple_GWT_Example implements EntryPoint {

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */

	private final GetStateServiceAsync getStateService = GWT.create(GetStateService.class);
	private final AddTextServiceAsync addTextService = GWT.create(AddTextService.class);

	
	final Button addButton = new Button("Add Text");
	final TextBox inputField = new TextBox();
	final TextArea outputArea = new TextArea();

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		setupComponents();
		update();
	}


	
	private void setupComponents(){
		inputField.setText("");
		inputField.setReadOnly(false);
		outputArea.setReadOnly(true);
		outputArea.setHeight("300px");
		outputArea.setWidth("400px");

		// We can add style names to widgets
		addButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("inputFieldContainer").add(inputField);
		RootPanel.get("addButtonContainer").add(addButton);
		RootPanel.get("outputAreaContainer").add(outputArea);

		// Focus the cursor on the name field when the app loads
		inputField.setFocus(true);
		inputField.selectAll();



		// Add handler for addButton
		addButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				addText();
			}
		});
		
		// Add key listener for input field
		inputField.addKeyPressHandler(new KeyPressHandler(){
			public void onKeyPress(KeyPressEvent event) {
				if(event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER){
					addText();
				}	
			}
			
		});
		
	}

	
	private void addText(){
		addTextService.addText(inputField.getText(), new AsyncCallback<String>(){
			public void onFailure(Throwable caught) {
				System.err.println("addButton service call got failure");
			}

			public void onSuccess(String result) {
				inputField.setText("");
				update();
			}
		});
	}
	
	public void update(){
		getStateService.getState("", new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				System.err.println("getState failed");
			}

			public void onSuccess(String result) {
				outputArea.setText(result);
			}
		});
	}
	
}
