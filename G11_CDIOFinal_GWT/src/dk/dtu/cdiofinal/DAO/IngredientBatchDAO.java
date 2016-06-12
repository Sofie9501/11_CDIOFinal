package dk.dtu.cdiofinal.DAO;

import java.util.List;

import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.shared.IngredientBatchDTO;

public interface IngredientBatchDAO {
	
	IngredientBatchDTO getIngredientBatch(int ID) throws DALException;
	List<IngredientBatchDTO> getIngredientBatchList() throws DALException;
	List<IngredientBatchDTO> getIngredientBatchList(int ID) throws DALException;
	void createIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException;
	void updateIngredientBatch(IngredientBatchDTO ingredientBatch, int oldID) throws DALException;
	

}
