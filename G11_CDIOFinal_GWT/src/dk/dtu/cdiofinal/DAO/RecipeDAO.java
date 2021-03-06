package dk.dtu.cdiofinal.DAO;

import java.util.ArrayList;
import java.util.List;

import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.shared.RecipeComponentDTO;
import dk.dtu.cdiofinal.shared.RecipeDTO;

public interface RecipeDAO {
	
	RecipeDTO getRecipe(int ID) throws DALException;
	List<RecipeDTO> getRecipes() throws DALException;
	List<RecipeComponentDTO> getRecipeComponentList (int ID) throws DALException; 
	void createRecipe(RecipeDTO recipe, ArrayList<RecipeComponentDTO> komp) throws DALException;
	void updateRecipe(RecipeDTO recipe, int oldRecipeID) throws DALException;
	void createRecipeComponent(RecipeComponentDTO comp) throws DALException;
	void updateRecipeComponent(RecipeComponentDTO comp, int oldRecipeID, int oldIngredientID) throws DALException;
	

}
