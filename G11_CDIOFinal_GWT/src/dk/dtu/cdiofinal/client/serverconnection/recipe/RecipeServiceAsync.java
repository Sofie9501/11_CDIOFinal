package dk.dtu.cdiofinal.client.serverconnection.recipe;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.dtu.cdiofinal.shared.RecipeDTO;

public interface RecipeServiceAsync {
	
	void getRecipies(AsyncCallback<List<RecipeDTO>> callback);
	void getRecipe(AsyncCallback<RecipeDTO> callback);
	void updateRecipe(RecipeDTO recipe, AsyncCallback<Boolean> callback);
	void createRecipe(RecipeDTO recipe, AsyncCallback<Boolean> callback);


}
