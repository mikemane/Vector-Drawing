package uk.ac.stand.cs.cs5001.gwttest.client.servicestubs;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>AddTextService</code>.
 */
public interface AddTextServiceAsync {
    void addText(String input, AsyncCallback<String> callback)
            throws IllegalArgumentException;
}
