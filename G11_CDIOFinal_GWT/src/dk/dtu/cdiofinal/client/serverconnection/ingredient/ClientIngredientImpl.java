package dk.dtu.cdiofinal.client.serverconnection.ingredient;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.dtu.cdiofinal.client.layout.ingredient.CreateIngredientView.MyCallback;
import dk.dtu.cdiofinal.client.serverconnection.MenuServiceAsync;
import dk.dtu.cdiofinal.client.serverconnection.operator.OperatorService;
import dk.dtu.cdiofinal.shared.IngredientDTO;
import dk.dtu.cdiofinal.shared.OperatoerDTO;

public class ClientIngredientImpl implements IngredientServiceAsync {

	private IngredientServiceAsync service;
	String url = GWT.getModuleBaseURL() + "service";
	
	public ClientIngredientImpl(){
		this.service = GWT.create(OperatorService.class);
	}

	public void getIngredient(AsyncCallback<List<IngredientDTO>> callback) {
		this.service.getIngredient(callback);
	}
	
	@Override
	public void createIngredient(IngredientDTO ingre, AsyncCallback<Boolean> callback) {
		this.service.createIngredient(ingre,callback);
	}

}
