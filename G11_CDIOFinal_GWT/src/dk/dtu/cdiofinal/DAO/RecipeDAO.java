package dk.dtu.cdiofinal.DAO;

import java.util.ArrayList;
import java.util.List;

public interface RecipeDAO {
	
	RecipeDTO getRecipe(int ID) throws DALException;
	List<RecipeDTO> getRecipeList() throws DALException;
	void createRecipe(RecipeDTO recipe, ArrayList<ReceptComponentDTO> komp) throws DALException;
	void updateRecipe(RecipeDTO recipe) throws DALException;
	boolean isActive(RecipeDTO recipe) throws DALException;

}
