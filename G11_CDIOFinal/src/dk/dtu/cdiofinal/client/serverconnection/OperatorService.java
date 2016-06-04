package dk.dtu.cdiofinal.client.serverconnection;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;

import dk.dtu.cdiofinal.shared.OperatoerDTO;

public interface OperatorService extends RemoteService {
	
	int isLoggedIn();
	boolean login(int oprId, String password);
	boolean logout();
	List<OperatoerDTO> getOperators();
	OperatoerDTO getOperator();
	boolean updateOperator(OperatoerDTO opr);
	boolean createOperator(OperatoerDTO opr);

}
