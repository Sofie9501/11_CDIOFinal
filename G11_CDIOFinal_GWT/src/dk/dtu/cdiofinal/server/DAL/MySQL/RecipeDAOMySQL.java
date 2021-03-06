package dk.dtu.cdiofinal.server.DAL.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dk.dtu.cdiofinal.DAO.RecipeDAO;
import dk.dtu.cdiofinal.server.DAL.Connector;
import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.shared.RecipeComponentDTO;
import dk.dtu.cdiofinal.shared.RecipeDTO;

public class RecipeDAOMySQL implements RecipeDAO{

	Connector c = new Connector();
	String query;

	// Get a DTO of the recipe with a specific ID
	@Override
	public RecipeDTO getRecipe(int ID) throws DALException {
		query = "select recipe_name, active from recipe where recipe_id = "
				+ ID + " group by recipe_name";
		ResultSet result = c.doQuery(query);

		// Convert to DTO
		RecipeDTO recipe = null;
		try {
			if(result.next()){
				recipe = new RecipeDTO(ID, result.getString(1), result.getBoolean(2) );
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recipe;
	}

	// Get a list of all Recipes
	@Override
	public List<RecipeDTO> getRecipes() throws DALException {
		query = "select * from recipe";
		ResultSet result = c.doQuery(query);

		if (result == null){
			throw new DALException("No recipes found");
		}
		List<RecipeDTO> list = new ArrayList<RecipeDTO>();
		try {
			while(result.next()){
				list.add(new RecipeDTO(result.getInt(1), result.getString(2), result.getBoolean(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	//get components of certain recipe
	@Override
	public ArrayList<RecipeComponentDTO> getRecipeComponentList(int ID) throws DALException {
		query = "select * from recipe_administration where recipe_id = " + ID;
		ResultSet result = c.doQuery(query);

		if (result == null){
			throw new DALException("No components found");
		}
		ArrayList<RecipeComponentDTO> list = new ArrayList<RecipeComponentDTO>();
		try {
			while(result.next()){
				list.add(new RecipeComponentDTO(result.getInt(1), result.getInt(3),
						result.getString(4), result.getDouble(5), result.getDouble(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	//create recipe and components in DB
	@Override
	public void createRecipe(RecipeDTO recipe, ArrayList<RecipeComponentDTO> komp) throws DALException {
		query = "call create_recipe(" + recipe.getID() + ", '" + recipe.getName()+ "')";
		c.doQuery(query);
		for (int i = 0; i < komp.size(); i++) {
			query = "call create_recipeComponent(" + komp.get(i).getRecipe_ID() + ",  " + komp.get(i).getIngredient_ID()
					+ ",  " + komp.get(i).getNom_netto()+ ",  " + komp.get(i).getTolerance() + ");";
			c.doQuery(query);
		}
	}

	//update the recipe in DB
	@Override
	public void updateRecipe(RecipeDTO recipe, int oldRecipeID) throws DALException {
		query = "call update_recipe(" + oldRecipeID + ", " + recipe.getID() + ", '" +recipe.getName()
		+ "', " + recipe.isActive() + ");";
		c.doQuery(query);
	}

	// create recipe comp in DB
	@Override
	public void createRecipeComponent(RecipeComponentDTO comp) throws DALException {
		query = "call create_recipeComponent(" + comp.getRecipe_ID()+ ",  " + comp.getIngredient_ID()+ ",  " +
				comp.getNom_netto()+ ",  " +comp.getTolerance()+");";
		c.doQuery(query);

	}

	//update recipecomponent in DB
	@Override
	public void updateRecipeComponent(RecipeComponentDTO comp, int oldRecipeID, int oldIngredientID) throws DALException {
		query = "call update_recipeComponent(" + oldRecipeID + ", " + comp.getRecipe_ID() + ", " + oldIngredientID + ", " + 
				comp.getIngredient_ID() + ", " + comp.getNom_netto() + ", " + comp.getTolerance() + ");";
		c.doQuery(query);
	}






}
