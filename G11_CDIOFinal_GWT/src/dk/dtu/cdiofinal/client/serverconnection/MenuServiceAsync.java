package dk.dtu.cdiofinal.client.serverconnection;

import com.google.gwt.user.client.rpc.AsyncCallback;
public interface MenuServiceAsync {
	
	void isLoggedIn(AsyncCallback<Integer> callback);
	void login(int oprId, String password, AsyncCallback<Boolean> callback);
	void logout(AsyncCallback<Boolean> callback);
	void loggedInName(AsyncCallback<String> callback);
	
	
}
