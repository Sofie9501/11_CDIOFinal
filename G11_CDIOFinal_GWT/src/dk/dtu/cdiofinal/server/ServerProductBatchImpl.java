package dk.dtu.cdiofinal.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.cdiofinal.client.serverconnection.productbatch.ProductBatchService;

import dk.dtu.cdiofinal.shared.ProductBatchDTO;

@SuppressWarnings("serial")
public class ServerProductBatchImpl extends RemoteServiceServlet implements ProductBatchService{

	@Override
	public List<ProductBatchDTO> getProductBatches() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductBatchDTO getProductBatch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateProductBatch(ProductBatchDTO proBatch) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createProductBatch(ProductBatchDTO proBatch) {
		// TODO Auto-generated method stub
		return false;
	}



}
