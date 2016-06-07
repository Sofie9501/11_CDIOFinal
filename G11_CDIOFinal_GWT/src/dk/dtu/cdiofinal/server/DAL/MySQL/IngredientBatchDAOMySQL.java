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
import dk.dtu.cdiofinal.shared.RecipeDTO;

public class IngredientBatchDAOMySQL implements IngredientBatchDAO{

	Connector c = new Connector();
	RecipeComponentDAO comp = new RecipeCompDAOMySQL();
	String query;

	@Override
	public IngredientBatchDTO getIngredientBatch(int ID) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	//get a list of all ingredientbatches
	@Override
	public List<IngredientBatchDTO> getIngredientBatchList() throws DALException {
		query = "select * from ingredientBatch_administration";
		ResultSet result = c.doQuery(query);

		if (result == null){
			throw new DALException("No ingredientbatches found");
		}
		List<IngredientBatchDTO> list = new ArrayList<IngredientBatchDTO>();

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

	@Override
	public List<IngredientBatchDTO> getIngredientBatchList(int ID) throws DALException {
		query = "select * from ingredientBatch_administration where ib_id = " + ID;
		ResultSet result = c.doQuery(query);

		if (result == null){
			throw new DALException("No ingredientbatches found");
		}
		List<IngredientBatchDTO> list = new ArrayList<IngredientBatchDTO>();

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

	@Override
	public void createIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException {
		String query = "call create_ingredientbatch(" + ingredientBatch.getIngredientBatch_ID()+ ", " 
				+ ingredientBatch.getIngredient_ID() + ", "+ ingredientBatch.getAmount()+ " );";
		c.doQuery(query);
	}

	@Override
	public void updateIngredientBatch(IngredientBatchDTO ingredientBatch, int oldID) throws DALException {
		String query = "call update_ingredientbatch(" + oldID + ", " + ingredientBatch.getIngredientBatch_ID()+ ", " 
				+ ingredientBatch.getIngredient_ID() + ", "+ ingredientBatch.getAmount() + ", "+ ingredientBatch.isActive() + " );";
		c.doQuery(query);
	}

	@Override
	public boolean isActive(IngredientBatchDTO ingredientBatch) throws DALException {
		// TODO Auto-generated method stub
		return false;
	}


}
