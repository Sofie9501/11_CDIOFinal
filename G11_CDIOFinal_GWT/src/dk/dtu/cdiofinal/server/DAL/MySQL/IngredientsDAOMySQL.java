package dk.dtu.cdiofinal.server.DAL.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dk.dtu.cdiofinal.DAO.IngredientDAO;
import dk.dtu.cdiofinal.server.DAL.Connector;
import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.shared.IngredientDTO;

public class IngredientsDAOMySQL implements IngredientDAO{
	
	Connector c = new Connector();
	String query;

	@Override
	public IngredientDTO getIngredient(int ID) throws DALException {
		IngredientDTO ingredient = null;
		query = "Select * from ingredient where ingredient_id = " + ID;
		ResultSet result = c.doQuery(query);
		//makes a ingredient from the data found
		try{
		if(result.next()){
			ingredient = new IngredientDTO(result.getInt(1), result.getString(2),
							result.getString(3), result.getBoolean(4));
		}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return ingredient;
	}

	//get list of all ingredients
	@Override
	public List<IngredientDTO> getIngredientList() throws DALException {
		query = "Select * from ingredient";
		ResultSet result = c.doQuery(query);
		if(result == null){
			throw new DALException("No ingredients found");
		}
		//creates list of ingredients
		List<IngredientDTO> list = new ArrayList<IngredientDTO>();
		
		try{
			while(result.next()){
				list.add(new IngredientDTO(result.getInt(1), result.getString(2),
						result.getString(3), result.getBoolean(4)));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	//create ingredient in DB
	@Override
	public void createIngredient(IngredientDTO ingredient) throws DALException {
		String query = "call create_ingredient(" + ingredient.getID() + ", '" + ingredient.getName() +
				"', '" + ingredient.getSupplier() + "');";
		c.doQuery(query);
		
	}
	
	//update ingredient in DB
	@Override
	public void updateIngredient(IngredientDTO ingredient, int oldID) throws DALException {
		String query = "call update_ingredient(" + oldID + ", " + ingredient.getID() + ", '" + ingredient.getName() +
				"',' " + ingredient.getSupplier() +"', " + ingredient.isActive() +");";
		c.doQuery(query);
		
	}
	

}
