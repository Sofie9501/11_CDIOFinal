package core;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Context implements DatabaseCom{

	// The connector for our database - and the query that will be send
	Connector c = new Connector(); 
	String query;

	
	// Returns a name from an operator ID
	@Override
	public String getOperator(int operatorId) throws DALException {
		// Query
		query = "Select opr_name from operator where opr_id = " + operatorId + ";";
		ResultSet result = c.doQuery(query);

		// Throw exception if no result is found
		if(result == null){
			throw new DALException("Operator not found");
		}
		
		String name = null;
		try {
			// is there a next row
			if(result.next()){
				name = result.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// return the name
		return name;
	}

	// Returns the name of a recipe given the product batch ID
	@Override
	public String getProductRecipeName(int pb_id) throws DALException {
		// Query
		query = "Select recipe_name from productBatch_administration where pb_id = " + pb_id + ";";
		ResultSet result = c.doQuery(query);

		// Throw exception if no result is found
		if(result == null){
			throw new DALException("Recipe not found");
		}
		
		String name = null;
		try {
			// is there a next row
			if(result.next()){
				name = result.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// return the name
		return name;
	}

	// Checks if the ingredient batch exists
	@Override
	public boolean checkIbId(int ib_id) throws DALException {
		// Query 
		query = "select * from ingredientBatch_administration where ib_id = " + ib_id + ";";
		ResultSet result = c.doQuery(query);
		
		// Throw exception if no result is found
		if(result == null){
			throw new DALException("Ingredient batch not found");
		}
		
		boolean exsist = false;
		try {
			// If there's a result the ingredient batch exsist
			if(result.next()){
				exsist = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Return the boolean
		return exsist;
	}

	// Creates a product batch component using a stored procedure
	@Override
	public void createProductBatchComp(int pbID, int rbID, float tare, float net, int oprID) throws DALException {
		query = "";
		ResultSet result = c.doQuery(query);
	}

	@Override
	public void setPbStatus() throws DALException {
	}



}
