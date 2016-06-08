package dk.dtu.cdiofinal.client.serverconnection.productbatch;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dk.dtu.cdiofinal.shared.ProductBatchDTO;


@RemoteServiceRelativePath("ProductBatchService")
public interface ProductBatchService extends RemoteService{
	
	List<ProductBatchDTO> getProductBatches();
	ProductBatchDTO getProductBatch(int pbID);
	boolean updateProductBatch(ProductBatchDTO proBatch, int oldID);
	boolean createProductBatch(ProductBatchDTO proBatch);

}
