package dk.dtu.cdiofinal.client.serverconnection;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ClientMenuInt {
	void isLoggedIn(AsyncCallback<Integer> callback);
	void login(int oprId, String password, AsyncCallback<Boolean> callback);

}
