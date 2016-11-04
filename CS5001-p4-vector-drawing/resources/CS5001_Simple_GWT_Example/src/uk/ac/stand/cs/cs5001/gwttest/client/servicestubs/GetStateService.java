package uk.ac.stand.cs.cs5001.gwttest.client.servicestubs;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("getState")
public interface GetStateService extends RemoteService {
  String getState(String dummy) throws IllegalArgumentException;
}
