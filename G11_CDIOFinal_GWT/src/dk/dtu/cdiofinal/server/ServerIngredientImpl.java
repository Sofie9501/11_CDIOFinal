package dk.dtu.cdiofinal.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.cdiofinal.client.serverconnection.ingredient.IngredientService;
import dk.dtu.cdiofinal.shared.IngredientDTO;

@SuppressWarnings("serial")
public class ServerIngredientImpl extends RemoteServiceServlet implements IngredientService{

	@Override
	public List<IngredientDTO> getIngredients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IngredientDTO getIngredient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateIngredient(IngredientDTO ingredient) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createIngredient(IngredientDTO ingredient) {
		// TODO Auto-generated method stub
		return false;
	}

}
