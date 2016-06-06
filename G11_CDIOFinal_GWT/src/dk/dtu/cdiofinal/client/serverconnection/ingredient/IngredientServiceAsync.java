package dk.dtu.cdiofinal.client.serverconnection.ingredient;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.dtu.cdiofinal.shared.IngredientDTO;

public interface IngredientServiceAsync {

	void getIngredient(AsyncCallback<List<IngredientDTO>> callback);
	
}
