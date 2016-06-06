package dk.dtu.cdiofinal.client.serverconnection;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class ClientMenuImpl implements ClientMenuInt{

	@Override
	public void isLoggedIn(AsyncCallback<Integer> callback) {
		this.service.isLoggedIn(callback);
	}

	@Override
	public void login(int username, String password, AsyncCallback<Boolean> callback) {
		this.service.login(username, password, callback);
	}

}
