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
	// throws exception if amount is not valid
	@Override
	public void checkIbId(int ibId, int pbId) throws DALException {
		float amount;
		float net;
		// check component is not created already
		String query = "select * from productBatchComponent where pb_id= " + pbId  + " and ib_id= " + ibId + ";";
		
		try {
			ResultSet qResult = c.doQuery(query);
			if(qResult.next())
				throw new DALException("component already created");
			// Queries. First gets the amount that is available in the ingredient batch, 
			// the next gets the needed amount for the recipe
			String queryAmount = "select amount from ingredientBatch_administration where ib_id = " + ibId + ";";
			String queryNet = "select nom_net from recipecomponent where recipe_id in (select recipe_id from productbatch where pb_id = " + pbId + ") and ingredient_id in (select ingredient_id from ingredientbatch where ib_id = " + ibId + ");";
		
		
			ResultSet result = c.doQuery(queryAmount);
			
			// Throw exception if no result is found
			if(!result.next()){
				throw new DALException("Ingredient batch not found");
			}else{
			// If there's a result the ingredient batch exist and we get the amount
				amount = Float.parseFloat(result.getString(1));
			}
			
			
			// Next we get the net
			result = c.doQuery(queryNet);
			
						
			if(!result.next()){
				throw new DALException("Recept Component not found");
			}else{
			// If there's a result the ingredient batch exist and we get the amount
				net = Float.parseFloat(result.getString(1));
			}
			
			
			
			if(net >= amount){
				throw new DALException("Ingredient batch does not have the required amount");
			}
			
			
		} catch (DALException e){
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Creates a product batch component using a stored procedure
	@Override
	public void createProductBatchComp(int pbId, int ibId, float tare, double net, int oprId) throws DALException {
		// Calls a stored procedure in our database
		query = "call create_productbatchcomponent(" + pbId + ", " + ibId + ", " + tare + ", " + net + ", " + oprId + ");";
		c.doQuery(query);

	}

	// Sets the status of the product batch to "in progress" 
	@Override
	public void setPbStatus(int pbId) throws DALException {
		// Calls a stored procedure in our database that updates certain parameters.
		// The status is now "1".
		query = "call update_productbatchstatus(" + pbId + ", 1);";
		c.doQuery(query);
	}

	@Override
	public RecipeCompDTO checkWeight(int pbId, int ibId) throws DALException{
		query = "select * from ase_info where pb_id = " + pbId + " and ib_id = " + ibId + ";";
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
