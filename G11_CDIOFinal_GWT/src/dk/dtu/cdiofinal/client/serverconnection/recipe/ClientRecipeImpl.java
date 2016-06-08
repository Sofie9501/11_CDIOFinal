package dk.dtu.cdiofinal.client.serverconnection.recipe;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.dtu.cdiofinal.shared.RecipeComponentDTO;
import dk.dtu.cdiofinal.shared.RecipeDTO;

public class ClientRecipeImpl implements RecipeServiceAsync{
	
	private RecipeServiceAsync service;
	String url = GWT.getModuleBaseURL() + "RecipeService";
	
	public ClientRecipeImpl(){
		this.service = GWT.create(RecipeService.class);
	}

	@Override
	public void getRecipies(AsyncCallback<List<RecipeDTO>> callback) {
		this.service.getRecipies(callback);
		
	}

	@Override
	public void getRecipe(int ID, AsyncCallback<RecipeDTO> callback) {
		this.service.getRecipe(ID, callback);
		
	}

	@Override
	public void updateRecipe(RecipeDTO recipe, int oldID, AsyncCallback<Boolean> callback) {
		this.service.updateRecipe(recipe, oldID, callback);
		
	}

	@Override
	public void createRecipe(RecipeDTO recipe, ArrayList<RecipeComponentDTO> list, AsyncCallback<Boolean> callback) {
		this.service.createRecipe(recipe, list, callback);
		
	}

}
