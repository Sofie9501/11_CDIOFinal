package dk.dtu.cdiofinal.client.serverconnection.operator;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dk.dtu.cdiofinal.shared.OperatoerDTO;

@RemoteServiceRelativePath("service")
public interface OperatorService extends RemoteService {
	
	
	List<OperatoerDTO> getOperators();
	OperatoerDTO getOperator();
	boolean updateOperator(OperatoerDTO opr);
	boolean createOperator(OperatoerDTO opr);

}
