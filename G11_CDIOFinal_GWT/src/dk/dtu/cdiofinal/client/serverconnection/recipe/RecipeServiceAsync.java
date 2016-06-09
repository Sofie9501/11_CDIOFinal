package dk.dtu.cdiofinal.client.serverconnection.recipe;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.dtu.cdiofinal.shared.RecipeComponentDTO;
import dk.dtu.cdiofinal.shared.RecipeDTO;

public interface RecipeServiceAsync {
	
	void getRecipies(AsyncCallback<List<RecipeDTO>> callback);
	void getRecipe(int ID, AsyncCallback<RecipeDTO> callback);
	void getRecipeComponentList (int ID, AsyncCallback<ArrayList<RecipeComponentDTO>> callback);
	void updateRecipe(RecipeDTO recipe, int oldID, AsyncCallback<Boolean> callback);
	void createRecipe(RecipeDTO recipe, ArrayList<RecipeComponentDTO> list, AsyncCallback<Boolean> callback);


}
