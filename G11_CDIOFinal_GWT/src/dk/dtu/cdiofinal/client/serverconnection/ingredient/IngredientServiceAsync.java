package dk.dtu.cdiofinal.client.serverconnection.ingredient;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.dtu.cdiofinal.shared.IngredientDTO;
import dk.dtu.cdiofinal.shared.ProductBatchDTO;

public interface IngredientServiceAsync {

	void getIngredients(AsyncCallback<List<IngredientDTO>> callback);
	void updateIngredient(IngredientDTO ingredient, int oldID, AsyncCallback<Boolean> callback);
	void createIngredient(IngredientDTO ingredient, AsyncCallback<Boolean> callback);
	
}
