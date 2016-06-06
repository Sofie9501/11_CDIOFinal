package dk.dtu.cdiofinal.client.serverconnection;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.dtu.cdiofinal.shared.IngredientDTO;
import dk.dtu.cdiofinal.shared.OperatoerDTO;

public interface MenuServiceAsync {
	
	void isLoggedIn(AsyncCallback<Integer> callback);
	void login(int oprId, String password, AsyncCallback<Boolean> callback);
	void logout(AsyncCallback<Boolean> callback);
	
	
}
