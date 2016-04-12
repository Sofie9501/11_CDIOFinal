package DAL;

import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.OperatoerDTO;
import interfaces.DALException;
import interfaces.OperatoerDAO;

public class OperatoerContext implements OperatoerDAO{
	Connector c = new Connector();
	@Override
	public OperatoerDTO getOperatoer(int oprId) throws DALException {
		// Query and get result
		String query = "Select * From operatoer where opr_id = " + oprId;
		ResultSet result = c.doQuery(query);
		
		// Throw exception if no results found
		if(result == null){
			throw new DALException("No operators found");
		}
		
		// Convert to Data Transfer Object
		OperatoerDTO opr = null;
		try {
			// is there a next row
			if(result.next()){
				opr = new OperatoerDTO(result.getInt(1), result.getString(2),result.getString(4), result.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return operator.
		return opr;
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
