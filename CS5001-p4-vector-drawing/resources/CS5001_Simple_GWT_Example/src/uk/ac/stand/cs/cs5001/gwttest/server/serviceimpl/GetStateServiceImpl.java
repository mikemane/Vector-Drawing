package uk.ac.stand.cs.cs5001.gwttest.server.serviceimpl;

import uk.ac.stand.cs.cs5001.gwttest.client.servicestubs.GetStateService;
import uk.ac.stand.cs.cs5001.gwttest.server.model.MyModel;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GetStateServiceImpl extends RemoteServiceServlet implements GetStateService {
	
	public String getState(String dummy) throws IllegalArgumentException {
		return MyModel.getInstance().getText();
	}

}
