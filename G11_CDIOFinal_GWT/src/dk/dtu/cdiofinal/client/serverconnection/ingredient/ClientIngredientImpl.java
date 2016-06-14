package dk.dtu.cdiofinal.client.serverconnection.ingredient;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;


import dk.dtu.cdiofinal.shared.IngredientDTO;


public class ClientIngredientImpl implements IngredientServiceAsync {

	private IngredientServiceAsync service;
	String url = GWT.getModuleBaseURL() + "Ingredientservice";
	
	public ClientIngredientImpl(){
		this.service = GWT.create(IngredientService.class);
	}

	//get list of all ingredient
	@Override
	public void getIngredients(AsyncCallback<List<IngredientDTO>> callback) {
		this.service.getIngredients(callback);
	}

	//update the ingredient
	@Override
	public void updateIngredient(IngredientDTO ingredient, int oldID, AsyncCallback<Boolean> callback) {
		this.service.updateIngredient(ingredient, oldID, callback);
	}

	//create ingredient
	@Override
	public void createIngredient(IngredientDTO ingredient, AsyncCallback<Boolean> callback) {
		this.service.createIngredient(ingredient, callback);
	}

	//get certain ingredient
	@Override
	public void getIngredient(int ID, AsyncCallback<IngredientDTO> callback) {
		this.service.getIngredient(ID, callback);
	}
}
