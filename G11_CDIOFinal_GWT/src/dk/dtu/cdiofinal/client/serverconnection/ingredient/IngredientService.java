package dk.dtu.cdiofinal.client.serverconnection.ingredient;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dk.dtu.cdiofinal.shared.IngredientDTO;


@RemoteServiceRelativePath("IngredientService")
public interface IngredientService extends RemoteService{
	
	List<IngredientDTO> getIngredients();
	IngredientDTO getIngredient();
	boolean updateIngredient(IngredientDTO ingredient);
	boolean createIngredient(IngredientDTO ingredient);

}
