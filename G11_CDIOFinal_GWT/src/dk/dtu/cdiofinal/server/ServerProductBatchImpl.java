package dk.dtu.cdiofinal.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.cdiofinal.DAO.ProductBatchDAO;
import dk.dtu.cdiofinal.client.serverconnection.productbatch.ProductBatchService;
import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.server.DAL.MySQL.ProductBatchDAOMySQL;
import dk.dtu.cdiofinal.shared.DTOVerifier;
import dk.dtu.cdiofinal.shared.ProductBatchDTO;

@SuppressWarnings("serial")
public class ServerProductBatchImpl extends RemoteServiceServlet implements ProductBatchService{

	private ProductBatchDAO dao = new ProductBatchDAOMySQL();

	//get list of productbatches
	@Override
	public List<ProductBatchDTO> getProductBatches() {
		List<ProductBatchDTO> list = new ArrayList<ProductBatchDTO>();
		try {
			list = dao.getProductBatchList();
		} catch (DALException e) {
			e.printStackTrace();
		}
		return list;
	}

	//get certain batch
	@Override
	public ProductBatchDTO getProductBatch(int pbID) {
		ProductBatchDTO dto = null;
		try {
			dto = dao.getProductBatch(pbID);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return dto;
	}

	//used to update batch
	@Override
	public boolean updateProductBatch(ProductBatchDTO proBatch, int oldID) {
		if (DTOVerifier.VerifyProductBatchDTO(proBatch)){
			try {
				dao.updateProductBatch(proBatch, oldID);
				return true;
			} catch (DALException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	//used to create batch
	@Override
	public boolean createProductBatch(ProductBatchDTO proBatch) {
		if (DTOVerifier.VerifyProductBatchDTO(proBatch)){
			try {
				dao.createProductBatch(proBatch);
				return true;
			} catch (DALException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
}