package dk.dtu.cdiofinal.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


import dk.dtu.cdiofinal.client.serverconnection.recipe.RecipeService;
import dk.dtu.cdiofinal.shared.RecipeDTO;

@SuppressWarnings("serial")
public class ServerRecipeImpl extends RemoteServiceServlet implements RecipeService {

	@Override
	public List<RecipeDTO> getRecipies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecipeDTO getRecipe() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateRecipe(RecipeDTO recipe) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createRecipe(RecipeDTO recipe) {
		// TODO Auto-generated method stub
		return false;
	}

}
