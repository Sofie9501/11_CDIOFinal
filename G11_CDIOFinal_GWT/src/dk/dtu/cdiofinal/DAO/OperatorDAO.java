package dk.dtu.cdiofinal.DAO;

import java.util.List;

import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.shared.OperatorDTO;

public interface OperatorDAO {
	OperatorDTO getOperator(int oprId) throws DALException;
	List<OperatorDTO> getOperatorList() throws DALException;
	void createOperator(OperatorDTO opr) throws DALException;
	void updateOperator(OperatorDTO opr, int oldID) throws DALException;
}
