package dk.dtu.cdiofinal.server.DAL.MySQL;

import java.util.ArrayList;
import java.util.List;

import dk.dtu.cdiofinal.DAO.RecipeDAO;
import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.shared.RecipeComponentDTO;
import dk.dtu.cdiofinal.shared.RecipeDTO;

public class RecipeDAOMySQL implements RecipeDAO{

	@Override
	public RecipeDTO getRecipe(int ID) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecipeDTO> getRecipeList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createRecipe(RecipeDTO recipe, ArrayList<RecipeComponentDTO> komp) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRecipe(RecipeDTO recipe) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isActive(RecipeDTO recipe) throws DALException {
		// TODO Auto-generated method stub
		return false;
	}

}
