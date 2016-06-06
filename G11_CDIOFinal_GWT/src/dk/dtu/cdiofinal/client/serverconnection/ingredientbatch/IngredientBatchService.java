package dk.dtu.cdiofinal.client.serverconnection.ingredientbatch;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dk.dtu.cdiofinal.shared.IngredientBatchDTO;

@RemoteServiceRelativePath("service")
public interface IngredientBatchService extends RemoteService {
	
	List<IngredientBatchDTO> getIngredientBatches();
	IngredientBatchDTO getIngredientBatch();
	boolean updateIngredientBatch(IngredientBatchDTO opr);
	boolean createIngredientBatch(IngredientBatchDTO opr);

}
