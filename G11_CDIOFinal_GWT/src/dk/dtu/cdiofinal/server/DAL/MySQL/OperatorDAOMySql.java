package dk.dtu.cdiofinal.server.DAL.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dk.dtu.cdiofinal.DAO.OperatoerDAO;
import dk.dtu.cdiofinal.server.DAL.Connector;
import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.shared.OperatorDTO;


public class OperatorDAOMySql implements OperatoerDAO {

	Connector c = new Connector();
	String query;

	public OperatorDTO getOperatoer(int oprId) throws DALException {
		// Query for Object
		OperatorDTO opr = null;
		query = "Select * From operator where opr_id = " + oprId;
  		ResultSet result = c.doQuery(query);		  		
		try {
			if(result.next()){
				opr = new OperatorDTO(result.getInt(1), result.getString(2),result.getString(3),
						result.getString(4), result.getInt(5), result.getBoolean(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// return operator.
		return opr;
	}

	@Override
	public List<OperatorDTO> getOperatoerList() throws DALException {
		query = "Select * From operator";
		ResultSet result = c.doQuery(query);
		
		// Throw exception if no results found
		if(result == null){
			throw new DALException("No operators found");
		}
		
		List<OperatorDTO> operatoers = new ArrayList<OperatorDTO>();
		try {
			// is there a next row
			while(result.next()){
				operatoers.add(new OperatorDTO(result.getInt(1), result.getString(2),result.getString(3),
						result.getString(4), result.getInt(5), result.getBoolean(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return operatoers;
	}
	
	@Override
	public void createOperator(OperatorDTO opr) throws DALException {
		String query = "call create_opr(" + opr.getOprID()+ ", " +opr.getRole() + ", '"+ opr.getName()+ "', '"+
						opr.getCpr() + "', '" + opr.getPassword() +"' );";
		c.doQuery(query);
	}

	@Override
	public void updateOperator(OperatorDTO opr, int oldID) throws DALException {
		String query = "call update_opr(" + oldID + ", " + opr.getOprID() + ", " + opr.getRole() + ", '" + opr.getName() + "', '" +
						opr.getCpr() + "', '" + opr.getPassword() + "' );";
		c.doQuery(query);
	}
	
}
