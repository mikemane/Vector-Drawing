package uk.ac.stand.cs.cs5001.gwttest.server.serviceimpl;

import uk.ac.stand.cs.cs5001.gwttest.client.servicestubs.AddTextService;
import uk.ac.stand.cs.cs5001.gwttest.server.model.MyModel;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class AddTextServiceImpl extends RemoteServiceServlet implements AddTextService {

    public String addText(String text) throws IllegalArgumentException {
        MyModel.getInstance().addText(text);
        return "Ok";
    }

}
