package dk.dtu.cdiofinal.client.serverconnection.operator;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.dtu.cdiofinal.shared.OperatorDTO;

public interface OperatorServiceAsync {
	
	void getOperators(AsyncCallback<List<OperatorDTO>> callback);
	void getOperator(AsyncCallback<OperatorDTO> callback);
	void updateOperator(OperatorDTO opr, AsyncCallback<Boolean> callback);
	void createOperator(OperatorDTO opr, AsyncCallback<Boolean> callback);

}
