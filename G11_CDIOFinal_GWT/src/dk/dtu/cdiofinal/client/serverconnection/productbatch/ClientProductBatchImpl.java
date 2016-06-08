package dk.dtu.cdiofinal.client.serverconnection.productbatch;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.dtu.cdiofinal.shared.ProductBatchDTO;

public class ClientProductBatchImpl implements ProductBatchServiceAsync{
	
	private ProductBatchServiceAsync service;
	String url = GWT.getModuleBaseURL() + "ProductBatchservice";
	
	public ClientProductBatchImpl(){
		this.service = GWT.create(ProductBatchService.class);
	}
	
	@Override
	public void getProductBatches(AsyncCallback<List<ProductBatchDTO>> callback) {
		this.service.getProductBatches(callback);
		
	}

	@Override
	public void getProductBatch(int pbID,  AsyncCallback<ProductBatchDTO> callback) {
		this.service.getProductBatch(pbID, callback);
		
	}

	@Override
	public void updateProductBatch(ProductBatchDTO proBatch,int pbID, AsyncCallback<Boolean> callback) {
		this.service.updateProductBatch(proBatch, pbID, callback);
		
	}

	@Override
	public void createProductBatch(ProductBatchDTO proBatch, AsyncCallback<Boolean> callback) {
		this.service.createProductBatch(proBatch, callback);
		
	}

}
