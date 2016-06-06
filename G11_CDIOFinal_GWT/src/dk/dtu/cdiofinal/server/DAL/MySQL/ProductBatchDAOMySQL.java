package dk.dtu.cdiofinal.server.DAL.MySQL;

import java.util.List;

import dk.dtu.cdiofinal.DAO.ProductBatchDAO;
import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.shared.ProductBatchDTO;

public class ProductBatchDAOMySQL implements ProductBatchDAO{

	@Override
	public ProductBatchDTO getProductBatch(int ID) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductBatchDTO> getProductBatchList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createProductBatch(ProductBatchDTO productBatch) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isActive(ProductBatchDTO productBatch) throws DALException {
		// TODO Auto-generated method stub
		return false;
	}

}
