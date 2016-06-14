package dk.dtu.cdiofinal.client.serverconnection.ingredientbatch;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dk.dtu.cdiofinal.shared.IngredientBatchDTO;

@RemoteServiceRelativePath("IngredientBatchService")
public interface IngredientBatchService extends RemoteService {
	
	List<IngredientBatchDTO> getIngredientBatches();
	List<IngredientBatchDTO> getIngredientBatchesList(int ID);
	boolean updateIngredientBatch(IngredientBatchDTO opr, int oldID);
	boolean createIngredientBatch(IngredientBatchDTO opr);

}
