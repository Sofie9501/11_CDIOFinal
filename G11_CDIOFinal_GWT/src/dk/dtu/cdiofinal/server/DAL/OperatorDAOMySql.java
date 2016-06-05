package dk.dtu.cdiofinal.server.DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dk.dtu.cdiofinal.shared.OperatoerDTO;


public class OperatorDAOMySql implements OperatoerDAO {

	Connector c = new Connector();
	String query;

	public OperatoerDTO getOperatoer(int oprId) throws DALException {
		// Query for Object
		OperatoerDTO opr = null;
		query = "Select * From operators where opr_id = " + oprId;
  		ResultSet result = c.doQuery(query);		  		
		try {
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
		query = "Select * From operators";
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
		String query = "call opret_opr(" + opr.getOprID()+ ", " + opr.getRolle() + ", '"+ opr.getOprNavn()+ "', '"+
						opr.getCpr() + "', '" + opr.getPassword() +"' );";
		c.doQuery(query);
	}

	@Override
	public void updateOperatoer(OperatoerDTO opr) throws DALException {
		String query = "call aendre_opr(" + opr.getOprID() + ", " + opr.getRolle() + ", '" + opr.getOprNavn() + "', '" +
						opr.getCpr() + "', '" + opr.getPassword() + "' );";
		c.doQuery(query);
	}
	
}
