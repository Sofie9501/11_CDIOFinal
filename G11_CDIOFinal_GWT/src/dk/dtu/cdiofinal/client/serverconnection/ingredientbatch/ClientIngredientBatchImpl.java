package dk.dtu.cdiofinal.client.serverconnection.ingredientbatch;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.dtu.cdiofinal.shared.IngredientBatchDTO;

public class ClientIngredientBatchImpl implements IngredientBatchServiceAsync{
	
	private IngredientBatchServiceAsync service;
	String url = GWT.getModuleBaseURL() + "service";
	
	public ClientIngredientBatchImpl(){
		this.service = GWT.create(IngredientBatchService.class);
	}
	
	//get litd of all ingredient batches
	@Override
	public void getIngredientBatches(AsyncCallback<List<IngredientBatchDTO>> callback) {
		this.service.getIngredientBatches(callback);
	}

	//get certain ingreidentbatch
	@Override
	public void getIngredientBatch(AsyncCallback<IngredientBatchDTO> callback) {
		this.service.getIngredientBatch(callback);
	}

	//update ingredientbatch
	@Override
	public void updateIngredientBatch(IngredientBatchDTO IngredientBatch, int oldID, AsyncCallback<Boolean> callback) {
		this.service.updateIngredientBatch(IngredientBatch, oldID, callback);
	}

	//create ingredientbatch
	@Override
	public void createIngredientBatch(IngredientBatchDTO IngredientBatch, AsyncCallback<Boolean> callback) {
		this.service.createIngredientBatch(IngredientBatch, callback);
	}
	
	//get list of ingredient batches of certain ingredient
	@Override
	public void getIngredientBatchesList(int ID, AsyncCallback<List<IngredientBatchDTO>> callback) {
		this.service.getIngredientBatchesList(ID, callback);
	}

}
