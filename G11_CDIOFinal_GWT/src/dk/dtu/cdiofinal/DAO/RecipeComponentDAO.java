package dk.dtu.cdiofinal.DAO;

import java.util.List;

import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.shared.IngredientDTO;
import dk.dtu.cdiofinal.shared.RecipeComponentDTO;

public interface RecipeComponentDAO {
	
	RecipeComponentDTO getRecipeComp(int recipeComp_ID, int ingredient_ID) throws DALException;
	List<RecipeComponentDTO> getRecipeCompList(int recipe_ID) throws DALException;
	void createRecipeComp(RecipeComponentDTO recipeComp) throws DALException;
	void updateRecipeComp(RecipeComponentDTO recipeComp, int oldRecipeID, int oldIngredientID) throws DALException;
	boolean isActive(RecipeComponentDTO recipeComp) throws DALException;

}
