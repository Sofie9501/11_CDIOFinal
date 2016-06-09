package dk.dtu.cdiofinal.server.DAL.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import dk.dtu.cdiofinal.DAO.RecipeComponentDAO;
import dk.dtu.cdiofinal.server.DAL.Connector;
import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.shared.RecipeComponentDTO;

public class RecipeCompDAOMySQL implements RecipeComponentDAO{
	
	Connector c = new Connector();
	String query;

	@Override
	public RecipeComponentDTO getRecipeComp(int recipe_ID, int ingredient_ID) throws DALException {
		query = "select * from recipe_administration where recipe_id = " + 
				recipe_ID + " and ingredient_id = " + ingredient_ID;
		ResultSet result = c.doQuery(query);
		// if there's no receptkomp with the given IDs, throw exception
				if(result==null){
					throw new DALException("No receptkomp found");
				}

				// create an object of receptkomp
				RecipeComponentDTO reKomp = null;
				try{
					// give the data to the empty object above
					if(result.next()){
						reKomp = new RecipeComponentDTO(result.getInt(1), result.getInt(3), 
								result.getString(4), result.getDouble(5), result.getDouble(6));

					}
				} catch(SQLException e){
					e.printStackTrace();
				}
				return reKomp;
	}
	// Get a list of recipeComponents with a specific recipe ID
	@Override
	public List<RecipeComponentDTO> getRecipeCompList(int recipe_ID) throws DALException {
		query = "select * from recipe_administration where recipe_id = " + recipe_ID;
		ResultSet result = c.doQuery(query);
		if (result==null){
			throw new DALException("No receptkomp found");
		}

		List<RecipeComponentDTO> list =  new ArrayList<RecipeComponentDTO>();
		try {
			while(result.next()){
				list.add(new RecipeComponentDTO(result.getInt(1), result.getInt(3), 
						result.getString(4), result.getDouble(5), result.getDouble(6)));
			}
		} catch (SQLException e) {
		}
		return list;
	}

	@Override
	public void createRecipeComp(RecipeComponentDTO recipeComp) throws DALException {
		query = "call create_recipeComponent(" + recipeComp.getRecipe_ID() + ",  " + recipeComp.getIngredient_ID()
				+ ",  " + recipeComp.getNom_netto()+ ",  " + recipeComp.getTolerance() + ");";
		c.doQuery(query);
		
	}
	@Override
	public void updateRecipeComp(RecipeComponentDTO recipeComp, int oldRecipeID, int oldIngredientID)
			throws DALException {
		query = "call update_recipeComponent(" + oldRecipeID + ",  " + recipeComp.getRecipe_ID()+ ",  " +
					oldIngredientID+ ",  " + recipeComp.getIngredient_ID()+ ",  " +recipeComp.getNom_netto()
					+ ",  " + recipeComp.getTolerance()  + ");";
		c.doQuery(query);
		
	}

	@Override
	public boolean isActive(RecipeComponentDTO recipeComp) throws DALException {
		// TODO Auto-generated method stub
		return false;
	}
	

}
