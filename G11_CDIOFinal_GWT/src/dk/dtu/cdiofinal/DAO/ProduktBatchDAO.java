package dk.dtu.cdiofinal.DAO;

import java.util.List;

public interface ProduktBatchDAO {

	ProductBatchDTO getProductBatch(int ID) throws DALException;
	List<ProductBatchDTO> getProductBatchList() throws DALException;
	void createProductBatch(ProductBatch productBatch) throws DALException;
	boolean isActive(ProductBatch productBatch) throws DALException;
	
}
