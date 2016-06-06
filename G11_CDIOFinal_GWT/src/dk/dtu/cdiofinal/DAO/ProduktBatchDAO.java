package dk.dtu.cdiofinal.DAO;

import java.util.List;

import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.shared.ProductBatchDTO;

public interface ProduktBatchDAO {

	ProductBatchDTO getProductBatch(int ID) throws DALException;
	List<ProductBatchDTO> getProductBatchList() throws DALException;
	void createProductBatch(ProductBatchDTO productBatch) throws DALException;
	boolean isActive(ProductBatchDTO productBatch) throws DALException;
	
}
