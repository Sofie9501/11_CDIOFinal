package dk.dtu.cdiofinal.client.serverconnection;


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;


public class ClientMenuImpl implements MenuServiceAsync{
	
	
	private MenuServiceAsync service;
	String url = GWT.getModuleBaseURL() + "menuService";
	
	// Constructor vigtig. Husk at ændre create(MenuService.class) til et eller andet andet .class
	public ClientMenuImpl(){
		this.service = GWT.create(MenuService.class);
	}
	
	//check if person is logged in
	@Override
	public void isLoggedIn(AsyncCallback<Integer> callback) {
		this.service.isLoggedIn(callback);
	}

	//login 
	@Override
	public void login(int username, String password, AsyncCallback<Boolean> callback) {
		this.service.login(username, password, callback);
	}

	//logout
	@Override
	public void logout(AsyncCallback<Boolean> callback) {
		this.service.logout(callback);
		
	}

	//gets the name of the person who is logged in
	@Override
	public void loggedInName(AsyncCallback<String> callback) {
		this.service.loggedInName(callback);
		
	}

}
