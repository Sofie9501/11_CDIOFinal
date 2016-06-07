package dk.dtu.cdiofinal.DAO;

import java.util.ArrayList;
import java.util.List;

import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.shared.RecipeComponentDTO;
import dk.dtu.cdiofinal.shared.RecipeDTO;

public interface RecipeDAO {
	
	RecipeDTO getRecipe(int ID) throws DALException;
	List<RecipeDTO> getRecipeList() throws DALException;
	List<RecipeComponentDTO> getRecipeComponentList () throws DALException; 
	void createRecipe(RecipeDTO recipe, ArrayList<RecipeComponentDTO> komp) throws DALException;
	void updateRecipe(RecipeDTO recipe) throws DALException;
	boolean isActive(RecipeDTO recipe) throws DALException;

}
