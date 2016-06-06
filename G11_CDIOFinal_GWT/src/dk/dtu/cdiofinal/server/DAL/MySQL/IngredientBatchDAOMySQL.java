package dk.dtu.cdiofinal.server.DAL.MySQL;

import java.util.List;

import dk.dtu.cdiofinal.DAO.IngredientBatchDAO;
import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.shared.IngredientBatchDTO;

public class IngredientBatchDAOMySQL implements IngredientBatchDAO{

	@Override
	public IngredientBatchDTO getIngredientBatch(int ID) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IngredientBatchDTO> getIngredientBatchList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IngredientBatchDTO> getIngredientBatchList(int ID) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isActive(IngredientBatchDTO ingredientBatch) throws DALException {
		// TODO Auto-generated method stub
		return false;
	}

}
