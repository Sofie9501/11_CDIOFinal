package dk.dtu.cdiofinal.client.serverconnection;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.dtu.cdiofinal.shared.IngredientDTO;
import dk.dtu.cdiofinal.shared.OperatoerDTO;

public class ServiceClientImpl implements OperatorServiceAsync{
	
	private OperatorServiceAsync service;
	String url = GWT.getModuleBaseURL() + "service";
	
	public ServiceClientImpl(){
		this.service = GWT.create(OperatorService.class);
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
	public void getOperators(AsyncCallback<List<OperatoerDTO>> callback) {
		this.service.getOperators(callback);
	}

	@Override
	public void updateOperator(OperatoerDTO opr, AsyncCallback<Boolean> callback) {
		this.service.updateOperator(opr, callback);
	}

	@Override
	public void createOperator(OperatoerDTO opr, AsyncCallback<Boolean> callback) {
		this.service.createOperator(opr, callback);
	}

	// returns operatordto of loggedin operator
	@Override
	public void getOperator(AsyncCallback<OperatoerDTO> callback) {
		this.service.getOperator(callback);		
	}

	@Override
	public void logout(AsyncCallback<Boolean> callback) {
		this.service.logout(callback);
		
	}

	public void createIngredient(IngredientDTO ingre, AsyncCallback<IngredientDTO> myCallback) {
		this.service.createIngredient(callback);
	}

}
