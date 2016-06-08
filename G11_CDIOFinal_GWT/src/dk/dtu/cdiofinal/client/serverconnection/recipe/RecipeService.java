package dk.dtu.cdiofinal.client.serverconnection.recipe;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dk.dtu.cdiofinal.shared.RecipeComponentDTO;
import dk.dtu.cdiofinal.shared.RecipeDTO;

@RemoteServiceRelativePath("RecipeService")
public interface RecipeService extends RemoteService {
	
	List<RecipeDTO> getRecipies();
	List<RecipeComponentDTO> getRecipiesComp(ArrayList<RecipeComponentDTO> list);
	RecipeDTO getRecipe(int ID);
	boolean updateRecipe(RecipeDTO recipe, int oldID);
	boolean createRecipe(RecipeDTO recipe, ArrayList<RecipeComponentDTO> list);


}
