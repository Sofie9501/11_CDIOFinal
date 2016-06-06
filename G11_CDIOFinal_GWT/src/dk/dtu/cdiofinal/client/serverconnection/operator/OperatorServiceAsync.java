package dk.dtu.cdiofinal.client.serverconnection.operator;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.dtu.cdiofinal.shared.OperatoerDTO;

public interface OperatorServiceAsync {
	
	void getOperators(AsyncCallback<List<OperatoerDTO>> callback);
	void getOperator(AsyncCallback<OperatoerDTO> callback);
	void updateOperator(OperatoerDTO opr, AsyncCallback<Boolean> callback);
	void createOperator(OperatoerDTO opr, AsyncCallback<Boolean> callback);

}
