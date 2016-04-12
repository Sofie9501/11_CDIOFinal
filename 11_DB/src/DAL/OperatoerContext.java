package DAL;

import java.awt.List;

import DTO.OperatoerDTO;
import interfaces.DALException;
import interfaces.OperatoerDAO;

public class OperatoerContext implements OperatoerDAO{
	Connector c = new Connector();
	@Override
	public OperatoerDTO getOperatoer(int oprId) throws DALException {
		String query = "Select * From Operatoer where oprid = " + oprId;
		c.doQuery(query);
		return null;
	}

	@Override
	public List getOperatoerList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createOperatoer(OperatoerDTO opr) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOperatoer(OperatoerDTO opr) throws DALException {
		// TODO Auto-generated method stub
		
	}

}
