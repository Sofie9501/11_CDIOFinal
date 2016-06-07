package dk.dtu.cdiofinal.client.serverconnection.operator;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.dtu.cdiofinal.shared.OperatorDTO;

public class ClientOperatorImpl implements OperatorServiceAsync{
	
	private OperatorServiceAsync service;
	String url = GWT.getModuleBaseURL() + "service";
	
	public ClientOperatorImpl(){
		this.service = GWT.create(OperatorService.class);
	}

	@Override
	public void getOperators(AsyncCallback<List<OperatorDTO>> callback) {
		this.service.getOperators(callback);
	}

	@Override
	public void updateOperator(OperatorDTO opr, AsyncCallback<Boolean> callback) {
		this.service.updateOperator(opr, callback);
	}

	@Override
	public void createOperator(OperatorDTO opr, AsyncCallback<Boolean> callback) {
		this.service.createOperator(opr, callback);
	}

	// returns operatordto of loggedin operator
	@Override
	public void getOperator(AsyncCallback<OperatorDTO> callback) {
		this.service.getOperator(callback);		
	}

	

}
