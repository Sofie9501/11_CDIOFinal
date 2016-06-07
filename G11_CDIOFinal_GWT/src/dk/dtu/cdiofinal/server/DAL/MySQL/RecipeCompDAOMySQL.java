package dk.dtu.cdiofinal.server.DAL.MySQL;

import java.util.List;

import dk.dtu.cdiofinal.DAO.RecipeComponentDAO;
import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.shared.RecipeComponentDTO;

public class RecipeCompDAOMySQL implements RecipeComponentDAO{

	@Override
	public RecipeComponentDTO getRecipeComp(int recipeComp_ID, int ingredient_ID) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecipeComponentDTO> getRecipeCompList(int recipe_ID) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecipeComponentDTO> getRecipeCompList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createRecipeComp(RecipeComponentDTO recipeComp) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isActive(RecipeComponentDTO recipeComp) throws DALException {
		// TODO Auto-generated method stub
		return false;
	}

}
