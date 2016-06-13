     package dk.dtu.cdiofinal.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.cdiofinal.DAO.IngredientBatchDAO;
import dk.dtu.cdiofinal.client.serverconnection.ingredientbatch.IngredientBatchService;
import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.server.DAL.MySQL.IngredientBatchDAOMySQL;
import dk.dtu.cdiofinal.shared.DTOVerifier;
import dk.dtu.cdiofinal.shared.IngredientBatchDTO;

@SuppressWarnings("serial")
public class ServerIngredientBatchImpl extends RemoteServiceServlet implements IngredientBatchService {
	private IngredientBatchDAO dao = new IngredientBatchDAOMySQL();

	//get list of ingredientbatches
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

	//get ingredient batch??
	@Override
	public IngredientBatchDTO getIngredientBatch() {
		return null;
	}

	//update ingredientbatch
	@Override
	public boolean updateIngredientBatch(IngredientBatchDTO inba, int oldID) {
		if (DTOVerifier.VerifyIngredientBatchDTO(inba)){
			try {
				dao.updateIngredientBatch(inba, oldID);
				return true;
			} catch (DALException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	//used to create ingredientbatch
	@Override
	public boolean createIngredientBatch(IngredientBatchDTO inba) {
		if (DTOVerifier.VerifyIngredientBatchDTO(inba)){
			try {
				dao.createIngredientBatch(inba);
				return true;
			} catch (DALException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
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
