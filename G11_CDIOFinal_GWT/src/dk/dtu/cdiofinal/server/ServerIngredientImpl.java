package dk.dtu.cdiofinal.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.cdiofinal.DAO.IngredientDAO;
import dk.dtu.cdiofinal.client.serverconnection.ingredient.IngredientService;
import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.server.DAL.MySQL.IngredientsDAOMySQL;
import dk.dtu.cdiofinal.shared.DTOVerifier;
import dk.dtu.cdiofinal.shared.FieldVerifier;
import dk.dtu.cdiofinal.shared.IngredientDTO;

@SuppressWarnings("serial")
public class ServerIngredientImpl extends RemoteServiceServlet implements IngredientService{
	IngredientDAO dao = new IngredientsDAOMySQL();
	
	// Gets a list of all ingredients in the database
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
	
	// Used to update an Ingredient DTO
	@Override
	public boolean updateIngredient(IngredientDTO ingredient, int oldID) {
		if (DTOVerifier.VerifyIngredientDTO(ingredient)){
			try {
				dao.updateIngredient(ingredient, oldID);
				return true;
			} catch (DALException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	// Used when creating a DTO
	@Override
	public boolean createIngredient(IngredientDTO ingredient) {
		if (DTOVerifier.VerifyIngredientDTO(ingredient)){
			try {
				dao.createIngredient(ingredient);
				return true;
			} catch (DALException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	// Used to get at DTO with specific ID
	@Override
	public IngredientDTO getIngredient(int ID) {
		IngredientDTO dto = null;
		if (FieldVerifier.numberValid(ID)){
			try {
				dto = dao.getIngredient(ID);
			} catch (DALException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

}
