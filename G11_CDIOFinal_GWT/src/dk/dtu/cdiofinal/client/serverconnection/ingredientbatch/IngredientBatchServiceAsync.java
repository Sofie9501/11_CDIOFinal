package dk.dtu.cdiofinal.client.serverconnection.ingredientbatch;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.dtu.cdiofinal.shared.IngredientBatchDTO;


public interface IngredientBatchServiceAsync {
	
	void getIngredientBatches(AsyncCallback<List<IngredientBatchDTO>> callback);
	void updateIngredientBatch(IngredientBatchDTO opr, int oldID, AsyncCallback<Boolean> callback);
	void createIngredientBatch(IngredientBatchDTO opr, AsyncCallback<Boolean> callback);
	void getIngredientBatchesList(int ID, AsyncCallback<List<IngredientBatchDTO>> callback);

}
