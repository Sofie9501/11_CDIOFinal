package interfaces;



import java.util.List;

import DTO.OperatoerDTO;

public interface OperatoerDAO {
	OperatoerDTO getOperatoer(int oprId) throws DALException;
	List getOperatoerList() throws DALException;
	void createOperatoer(OperatoerDTO opr) throws DALException;
	void updateOperatoer(OperatoerDTO opr) throws DALException;
}
