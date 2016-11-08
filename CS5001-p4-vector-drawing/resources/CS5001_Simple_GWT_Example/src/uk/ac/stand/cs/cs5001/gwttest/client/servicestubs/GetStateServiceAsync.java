package uk.ac.stand.cs.cs5001.gwttest.client.servicestubs;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GetStateService</code>.
 */
public interface GetStateServiceAsync {
    void getState(String dummy, AsyncCallback<String> callback)
            throws IllegalArgumentException;
}
