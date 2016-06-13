package dk.dtu.cdiofinal.server.DAL.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dk.dtu.cdiofinal.DAO.IngredientBatchDAO;
import dk.dtu.cdiofinal.DAO.RecipeComponentDAO;
import dk.dtu.cdiofinal.server.DAL.Connector;
import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.shared.IngredientBatchDTO;

public class IngredientBatchDAOMySQL implements IngredientBatchDAO{

	Connector c = new Connector();
	RecipeComponentDAO comp = new RecipeCompDAOMySQL();
	String query;

	@Override
	public IngredientBatchDTO getIngredientBatch(int ID) throws DALException {
		query = "select * from ingredientBatch_administration where ib_id = " + ID;
		ResultSet result = c.doQuery(query);
		//tell if no ingredientbatches if such id
		if (result == null){
			throw new DALException("No ingredientbatches found");
		}
		//creates ingredientbatchDTO
		IngredientBatchDTO dto = null;
		//if result is found, create the DTO
		try {
			while(result.next()){
				dto = new IngredientBatchDTO(result.getInt(1), result.getString(2), result.getInt(3), 
						result.getDouble(4), result.getBoolean(5), String.valueOf(result.getDate(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;

	}

	//get a list of all ingredientbatches
	@Override
	public List<IngredientBatchDTO> getIngredientBatchList() throws DALException {
		query = "select * from ingredientBatch_administration";
		ResultSet result = c.doQuery(query);
		
		if (result == null){
			throw new DALException("No ingredientbatches found");
		}
		//create list of all ingredientsbatches found
		List<IngredientBatchDTO> list = new ArrayList<IngredientBatchDTO>();
		try {
			while(result.next()){
				list.add(new IngredientBatchDTO(result.getInt(1), result.getString(2), result.getInt(3), 
						result.getDouble(4), result.getBoolean(5), String.valueOf(result.getDate(6))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	//get list of batches with certain id
	@Override
	public ArrayList<IngredientBatchDTO> getIngredientBatchList(int ID) throws DALException {
		query = "select * from ingredientBatch_administration where ingredient_id = " + ID;
		ResultSet result = c.doQuery(query);

		if (result == null){
			throw new DALException("No ingredientbatches found");
		}
		//creates list of batches
		ArrayList<IngredientBatchDTO> list = new ArrayList<IngredientBatchDTO>();
		try {
			while(result.next()){
				list.add(new IngredientBatchDTO(result.getInt(1), result.getString(2), result.getInt(3), 
						result.getInt(4), result.getBoolean(5), String.valueOf(result.getDate(6))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	//create ingredient in DB
	@Override
	public void createIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException {
		String query = "call create_ingredientbatch(" + ingredientBatch.getIngredientBatch_ID()+ ", " 
				+ ingredientBatch.getIngredient_ID() + ", "+ ingredientBatch.getAmount()+ " );";
		c.doQuery(query);
	}

	//update the batch in DB
	@Override
	public void updateIngredientBatch(IngredientBatchDTO ingredientBatch, int oldID) throws DALException {
		String query = "call update_ingredientbatch(" + oldID + ", " + ingredientBatch.getIngredientBatch_ID()+ ", " 
				+ ingredientBatch.getIngredient_ID() + ", "+ ingredientBatch.getAmount() + ", "+ ingredientBatch.isActive() + " );";
		c.doQuery(query);
	}

	

}
