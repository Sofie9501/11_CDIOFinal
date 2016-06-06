package dk.dtu.cdiofinal.DAO;

import java.util.List;


public interface IngredientBatchDAO {
	
	ingredientBatchDTO getIngredientBatch(int ID) throws DALException;
	List<ingredientBatchDTO> getIngredientBatchList() throws DALException;
	List<ingredientBatchDTO> getIngredientBatchList(int ID) throws DALException;
	void createIngredientBatch(ingredientBatchDTO ingredientBatch) throws DALException;
	boolean isActive(ingredientBatchDTO ingredientBatch) throws DALException;

}
