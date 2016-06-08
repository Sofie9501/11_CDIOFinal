package dk.dtu.cdiofinal.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.cdiofinal.DAO.IngredientBatchDAO;
import dk.dtu.cdiofinal.client.serverconnection.ingredientbatch.IngredientBatchService;
import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.server.DAL.MySQL.IngredientBatchDAOMySQL;
import dk.dtu.cdiofinal.shared.IngredientBatchDTO;

@SuppressWarnings("serial")
public class ServerIngredientBatchImpl extends RemoteServiceServlet implements IngredientBatchService {
	private IngredientBatchDAO dao = new IngredientBatchDAOMySQL();
	
	@Override
	public List<IngredientBatchDTO> getIngredientBatches() {
		List<IngredientBatchDTO> list = new ArrayList<IngredientBatchDTO>();

		try {
			list = dao.getIngredientBatchList();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public IngredientBatchDTO getIngredientBatch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateIngredientBatch(IngredientBatchDTO inba, int oldID) {
		try {
			dao.updateIngredientBatch(inba, oldID);
			return true;
		} catch (DALException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean createIngredientBatch(IngredientBatchDTO inba) {
		try {
			dao.createIngredientBatch(inba);
			return true;
		} catch (DALException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<IngredientBatchDTO> getIngredientBatchesList(int ID) {
		List<IngredientBatchDTO> list = new ArrayList<IngredientBatchDTO>();

		try {
			list = dao.getIngredientBatchList(ID);
		} catch (DALException e) {
			e.printStackTrace();
		}

		return list;
	}

}
