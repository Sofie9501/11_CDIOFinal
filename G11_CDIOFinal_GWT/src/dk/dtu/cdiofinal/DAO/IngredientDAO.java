package dk.dtu.cdiofinal.DAO;

import java.util.List;

import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.shared.IngredientDTO;

public interface IngredientDAO {
	
	IngredientDTO getIngredient(int ID) throws DALException;
	List<IngredientDTO> getIngredientList() throws DALException;
	void createIngredient(IngredientDTO ingredient) throws DALException;
	void updateIngredient(IngredientDTO ingredient, int oldID) throws DALException;
	

}
