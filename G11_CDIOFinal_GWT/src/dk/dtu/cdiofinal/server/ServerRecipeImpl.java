package dk.dtu.cdiofinal.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.cdiofinal.DAO.RecipeDAO;
import dk.dtu.cdiofinal.client.serverconnection.recipe.RecipeService;
import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.server.DAL.MySQL.RecipeDAOMySQL;
import dk.dtu.cdiofinal.shared.DTOVerifier;
import dk.dtu.cdiofinal.shared.RecipeComponentDTO;
import dk.dtu.cdiofinal.shared.RecipeDTO;

@SuppressWarnings("serial")
public class ServerRecipeImpl extends RemoteServiceServlet implements RecipeService {
	RecipeDAO dao = new RecipeDAOMySQL();

	//get list of all recipes
	@Override
	public List<RecipeDTO> getRecipies() {
		List<RecipeDTO> list = new ArrayList<RecipeDTO>();

		try {
			list = dao.getRecipes();
		} catch (DALException e) {
			e.printStackTrace();
		}
		return list;
	}

	//get certain recipe
	@Override
	public RecipeDTO getRecipe(int ID) {
		RecipeDTO dto = null;
		try {
			dto =	dao.getRecipe(ID);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}

	//used to update recipe
	@Override
	public boolean updateRecipe(RecipeDTO recipe, int oldID) {
		if (DTOVerifier.VerifyRecipeDTO(recipe)){
			try {
				dao.updateRecipe(recipe, oldID);
				return true;
			} catch (DALException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	//used to create recipe
	@Override
	public boolean createRecipe(RecipeDTO recipe, ArrayList<RecipeComponentDTO> list) {
		if (DTOVerifier.VerifyRecipeDTO(recipe)){
			try {
				dao.createRecipe(recipe, list);
				return true;
			} catch (DALException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	//used to update recipecomponent
	@Override
	public boolean updateRecipeComponent(RecipeComponentDTO comp, int oldRecipeID, int oldIngredientID) {
		if (DTOVerifier.VerifyRecipeComponentDTO(comp)){
			try {
				dao.updateRecipeComponent(comp, oldRecipeID, oldIngredientID);
				return true;
			} catch (DALException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}


	//get list of recipecomponent with certain recipe id
	@Override
	public List<RecipeComponentDTO> getRecipeComponentList(int ID) {
		List<RecipeComponentDTO> list = new ArrayList<RecipeComponentDTO>();

		try {
			list = dao.getRecipeComponentList(ID);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return list;
	}

	//used to create recipecomponent
	@Override
	public boolean createRecipeComponent(RecipeComponentDTO comp) {
		if (DTOVerifier.VerifyRecipeComponentDTO(comp)){
			try {
				dao.createRecipeComponent(comp);
				return true;
			} catch (DALException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
}
