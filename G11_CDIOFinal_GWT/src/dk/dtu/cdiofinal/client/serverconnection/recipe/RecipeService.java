package dk.dtu.cdiofinal.client.serverconnection.recipe;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dk.dtu.cdiofinal.shared.RecipeDTO;

@RemoteServiceRelativePath("RecipeService")
public interface RecipeService extends RemoteService {
	
	List<RecipeDTO> getRecipies();
	RecipeDTO getRecipe();
	boolean updateRecipe(RecipeDTO recipe);
	boolean createRecipe(RecipeDTO recipe);


}
