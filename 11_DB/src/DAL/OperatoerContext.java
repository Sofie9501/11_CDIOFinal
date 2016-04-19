package DAL;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.OperatoerDTO;
import interfaces.DALException;
import interfaces.OperatoerDAO;

public class OperatoerContext implements OperatoerDAO{
	Connector c = new Connector();
	String query;
	@Override
	public OperatoerDTO getOperatoer(int oprId) throws DALException {
		// Query and get result
		query = "Select * From operatoer where opr_id = " + oprId;
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
				opr = new OperatoerDTO(result.getInt(1), result.getString(2),result.getInt(3), result.getString(4),result.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// return operator.
		return opr;
	}

	@Override
	public List<OperatoerDTO> getOperatoerList() throws DALException {
		query = "Select * From operatoer";
		ResultSet result = c.doQuery(query);
		
		// Throw exception if no results found
		if(result == null){
			throw new DALException("No operators found");
		}
		
		List<OperatoerDTO> operatoers = new ArrayList<OperatoerDTO>();
		try {
			// is there a next row
			while(result.next()){
				operatoers.add(new OperatoerDTO(result.getInt(1), result.getString(2),result.getInt(3), result.getString(4),result.getString(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return operatoers;
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
