package dk.dtu.cdiofinal.client.serverconnection;

import com.google.gwt.user.client.rpc.RemoteService;

public interface MenuService extends RemoteService{
	
	int isLoggedIn();
	boolean login(int oprId, String password);
	boolean logout();

}
