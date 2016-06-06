package dk.dtu.cdiofinal.client.serverconnection;


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;


public class ClientMenuImpl implements MenuServiceAsync{
	
	
	private MenuServiceAsync service;
	String url = GWT.getModuleBaseURL() + "menuService";
	
	public ClientMenuImpl(){
		this.service = GWT.create(MenuService.class);
	}
	
	@Override
	public void isLoggedIn(AsyncCallback<Integer> callback) {
		this.service.isLoggedIn(callback);
	}

	@Override
	public void login(int username, String password, AsyncCallback<Boolean> callback) {
		this.service.login(username, password, callback);
	}

	@Override
	public void logout(AsyncCallback<Boolean> callback) {
		this.service.logout(callback);
		
	}

}
