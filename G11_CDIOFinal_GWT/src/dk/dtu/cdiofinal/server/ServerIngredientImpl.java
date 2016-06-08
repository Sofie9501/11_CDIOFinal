package dk.dtu.cdiofinal.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.cdiofinal.DAO.IngredientDAO;
import dk.dtu.cdiofinal.client.serverconnection.ingredient.IngredientService;
import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.server.DAL.MySQL.IngredientsDAOMySQL;
import dk.dtu.cdiofinal.shared.IngredientDTO;

@SuppressWarnings("serial")
public class ServerIngredientImpl extends RemoteServiceServlet implements IngredientService{
	IngredientDAO dao = new IngredientsDAOMySQL();
	
	
	@Override
	public List<IngredientDTO> getIngredients() {
		List<IngredientDTO> list = new ArrayList<IngredientDTO>();

		try {
			list = dao.getIngredientList();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public IngredientDTO getIngredient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateIngredient(IngredientDTO ingredient, int oldID) {
		try {
			dao.updateIngredient(ingredient, oldID);
			return true;
		} catch (DALException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean createIngredient(IngredientDTO ingredient) {
		try {
			dao.createIngredient(ingredient);
			return true;
		} catch (DALException e) {
			e.printStackTrace();
			return false;
		}
	}

}
