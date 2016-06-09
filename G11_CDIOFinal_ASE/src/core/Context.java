package core;

import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.RecipeCompDTO;

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
		query = "Select * from productBatch_administration where pb_id = " + pb_id + ";";
		ResultSet result = c.doQuery(query);

		try {
			// 
			if(result.next() && result.getInt(8) != 2 && result.getInt(9) == 1){
				return result.getString(3);
			} else
				throw new DALException("No result was found");
		} 
		catch (DALException e) {
			throw e;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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

		boolean exist = false;
		try {
			// If there's a result the ingredient batch exist
			return result.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Return the boolean
		return exist;
	}

	// Creates a product batch component using a stored procedure
	@Override
	public void createProductBatchComp(int pbID, int rbID, float tare, float net, int oprID) throws DALException {
		// Calls a stored procedure in our database
		query = "call create_productbatchcomponent(" + pbID + ", " + rbID + ", " + tare + ", " + net + ", " + oprID + ");";
		c.doQuery(query);
	}

	// Sets the status of the product batch to "in progress" 
	@Override
	public void setPbStatus(int pbID) throws DALException {
		// Calls a stored procedure in our database that updates certain parameters.
		// The status is now "1".
		//		query = "call update_productbatc(" + pbID + ", " + pbID + ", 1, " + rbID + ", 1);";
		//		c.doQuery(query);
	}

	@Override
	public RecipeCompDTO checkWeight(int pb_id, int ib_id) throws DALException{
		query = "select * from ase_info where pb_id = " + pb_id + " and ib_id = " + ib_id + ";";
		ResultSet result = c.doQuery(query);

		// Throw exception if no results found
		if(result == null){
			throw new DALException("No result found");
		}

		// Convert to Data Transfer Object
		RecipeCompDTO recipeComp = null;
		try {
			if(result.next()){
				recipeComp = new RecipeCompDTO(result.getFloat(1), result.getFloat(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// return the recipeComp
		return recipeComp;
	}



}
