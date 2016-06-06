package dk.dtu.cdiofinal.client.serverconnection;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("MenuService")
public interface MenuService extends RemoteService{
	
	int isLoggedIn();
	boolean login(int oprId, String password);
	boolean logout();

}
