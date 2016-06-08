package dk.dtu.cdiofinal.client.serverconnection.productbatch;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;


import dk.dtu.cdiofinal.shared.ProductBatchDTO;

public interface ProductBatchServiceAsync {
	
	void getProductBatches(AsyncCallback<List<ProductBatchDTO>> callback);
	void getProductBatch(int pbID, AsyncCallback<ProductBatchDTO> callback);
	void updateProductBatch(ProductBatchDTO proBatch, int oldID, AsyncCallback<Boolean> callback);
	void createProductBatch(ProductBatchDTO proBatch, AsyncCallback<Boolean> callback);

}
