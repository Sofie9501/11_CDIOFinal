package dk.dtu.cdiofinal.DAO;

import java.util.List;

public interface IngredientDAO {
	
	ingredientDTO getIngredient(int ID) throws DALException;
	List<ingredientDTO> getIngredientList() throws DALException;
	void createIngredient(IngredientDTO ingredient) throws DALException;
	void updateIngredient(IngredientDTO ingredient) throws DALException;
	boolean isActive(IngredientDTO ingredient) throws DALException;

}
