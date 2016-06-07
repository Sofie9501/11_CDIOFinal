package dk.dtu.cdiofinal.DAO;

import java.util.List;

import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.shared.OperatorDTO;
import dk.dtu.cdiofinal.shared.ProductBatchDTO;

public interface ProductBatchDAO {

	ProductBatchDTO getProductBatch(int ID) throws DALException;
	List<ProductBatchDTO> getProductBatchList() throws DALException;
	void createProductBatch(ProductBatchDTO productBatch) throws DALException;
	void updateProductBatch(ProductBatchDTO pb, int oldID) throws DALException;
	boolean isActive(ProductBatchDTO productBatch) throws DALException;
	
}
