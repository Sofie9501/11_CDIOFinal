package dk.dtu.cdiofinal.DAO;

import java.util.List;

import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.shared.OperatoerDTO;

public interface OperatoerDAO {
	OperatoerDTO getOperatoer(int oprId) throws DALException;
	List<OperatoerDTO> getOperatoerList() throws DALException;
	void createOperatoer(OperatoerDTO opr) throws DALException;
	void updateOperatoer(OperatoerDTO opr) throws DALException;
}
