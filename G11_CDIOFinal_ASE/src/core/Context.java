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
		try {
			ResultSet result = c.doQuery(query);
			// is there a next row
			if(result.next() && result.getString(1)!=null){
				return result.getString(1);
			} else {
				throw new DALException("No operators was found");
			}
		} catch(DALException e) {
			throw e;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		// return the name
		return "mega fejl";
	}

	// Returns the name of a recipe given the product batch ID.
	@Override
	public String getProductRecipeName(int pb_id) throws DALException {
		// Query
		query = "Select * from productBatch_administration where pb_id = " + pb_id + ";";
		try {
			ResultSet result = c.doQuery(query);
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

	// Checks if the ingredient batch exists and contains the required amount for the recipe
	@Override
	public boolean checkIbId(int ib_id, int pb_id) throws DALException {
		int amount;
		double net;
		
		// Queries. First gets the amount that is available in the ingredient batch, 
		// the next gets the needed amount for the recipe
		String queryAmount = "select amount from ingredientBatch_administration where ib_id = " + ib_id + ";";
		String queryNet = "select nom_net from recipecomponent where recipe_id in (select recipe_id from productbatch where pb_id =" + pb_id + ");";
		
		try {
			ResultSet result = c.doQuery(queryAmount);
			
			// Throw exception if no result is found
			if(!result.next()){
				throw new DALException("Ingredient batch not found");
			}else{
			// If there's a result the ingredient batch exist and we get the amount
				amount = Integer.parseInt(result.getString(1));
			}
			
			if(!result.next()){
				throw new DALException("No ");
			}else{
			// If there's a result the ingredient batch exist and we get the amount
				amount = Integer.parseInt(result.getString(1));
			}
			
			// Next we get the net
			result = c.doQuery(queryNet);
			net = Double.parseDouble(result.getString(1));
			
			if(net <= amount){
				throw new DALException("Ingredient batch does not have the required amount");
			}
			else
				return true;
			
		} catch (DALException e){
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Return the boolean
		return false;
	}

	// Creates a product batch component using a stored procedure
	@Override
	public void createProductBatchComp(int pbID, int ibID, float tare, double net, int oprID) throws DALException {
		// Calls a stored procedure in our database
		query = "call create_productbatchcomponent(" + pbID + ", " + ibID + ", " + tare + ", " + net + ", " + oprID + ");";
		c.doQuery(query);

	}

	// Sets the status of the product batch to "in progress" 
	@Override
	public void setPbStatus(int pbID) throws DALException {
		// Calls a stored procedure in our database that updates certain parameters.
		// The status is now "1".
		query = "call update_productbatchstatus(" + pbID + ", 1);";
		c.doQuery(query);
	}

	@Override
	public RecipeCompDTO checkWeight(int pb_id, int ib_id) throws DALException{
		query = "select * from ase_info where pb_id = " + pb_id + " and ib_id = " + ib_id + ";";
		ResultSet result = c.doQuery(query);

		// Convert to Data Transfer Object
		RecipeCompDTO recipeComp = null;
		try {
			// Throw exception if no results found
			if(!result.next()){
				throw new DALException("No result found");
			}else{
				return (new RecipeCompDTO(result.getFloat(1), result.getFloat(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// return the recipeComp
		return recipeComp;
	}



}
