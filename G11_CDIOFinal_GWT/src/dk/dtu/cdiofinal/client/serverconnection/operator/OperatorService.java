package dk.dtu.cdiofinal.client.serverconnection.operator;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dk.dtu.cdiofinal.shared.OperatorDTO;

@RemoteServiceRelativePath("OperatorService")
public interface OperatorService extends RemoteService {
	
	
	List<OperatorDTO> getOperators();
	OperatorDTO getOperator();
	boolean updateOperator(OperatorDTO opr, int oldID);
	boolean createOperator(OperatorDTO opr);

}
