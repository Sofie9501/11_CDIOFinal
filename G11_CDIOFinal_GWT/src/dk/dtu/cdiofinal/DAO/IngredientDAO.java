package dk.dtu.cdiofinal.DAO;

import java.util.List;

import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.shared.IngredientBatchDTO;
import dk.dtu.cdiofinal.shared.IngredientDTO;

public interface IngredientDAO {
	
	IngredientBatchDTO getIngredient(int ID) throws DALException;
	List<IngredientBatchDTO> getIngredientList() throws DALException;
	void createIngredient(IngredientDTO ingredient) throws DALException;
	void updateIngredient(IngredientDTO ingredient) throws DALException;
	boolean isActive(IngredientDTO ingredient) throws DALException;

}
