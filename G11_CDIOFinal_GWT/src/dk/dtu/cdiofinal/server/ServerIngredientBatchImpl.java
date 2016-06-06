package dk.dtu.cdiofinal.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.cdiofinal.client.serverconnection.ingredientbatch.IngredientBatchService;
import dk.dtu.cdiofinal.shared.IngredientBatchDTO;

@SuppressWarnings("serial")
public class ServerIngredientBatchImpl extends RemoteServiceServlet implements IngredientBatchService {

	@Override
	public List<IngredientBatchDTO> getIngredientBatches() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IngredientBatchDTO getIngredientBatch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateIngredientBatch(IngredientBatchDTO opr) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createIngredientBatch(IngredientBatchDTO opr) {
		// TODO Auto-generated method stub
		return false;
	}

}
