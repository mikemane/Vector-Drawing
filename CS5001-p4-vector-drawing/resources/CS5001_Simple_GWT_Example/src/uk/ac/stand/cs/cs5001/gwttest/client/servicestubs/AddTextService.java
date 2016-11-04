package uk.ac.stand.cs.cs5001.gwttest.client.servicestubs;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("addText")
public interface AddTextService extends RemoteService {
  String addText(String text) throws IllegalArgumentException;
}
