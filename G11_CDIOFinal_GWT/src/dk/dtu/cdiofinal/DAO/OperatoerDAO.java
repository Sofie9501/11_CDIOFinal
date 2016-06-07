package dk.dtu.cdiofinal.DAO;

import java.util.List;

import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.shared.OperatorDTO;

public interface OperatoerDAO {
	OperatorDTO getOperatoer(int oprId) throws DALException;
	List<OperatorDTO> getOperatoerList() throws DALException;
	void createOperator(OperatorDTO opr) throws DALException;
	void updateOperator(OperatorDTO opr, int oldID) throws DALException;
}
