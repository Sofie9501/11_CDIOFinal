package dk.dtu.cdiofinal.server.DAL.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.ProduktBatchAdminDTO;
import dk.dtu.cdiofinal.DAO.ProductBatchDAO;
import dk.dtu.cdiofinal.server.DAL.Connector;
import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.shared.ProductBatchDTO;

public class ProductBatchDAOMySQL implements ProductBatchDAO{
	
	Connector c = new Connector();
	String query;

	@Override
	public ProductBatchDTO getProductBatch(int ID) throws DALException {
		// Query and get result
		query = "Select * from  productbatch_administration where pb_id = " + ID;
		ResultSet result = c.doQuery(query);

		// Throw exception if no results found
		if(result == null){
			throw new DALException("No produktBatch found");
		}

		// Convert to Data Transfer Object
		ProductBatchDTO pb = null;
		try {
			// is there a next row
			if(result.next()){
				pb = new ProductBatchDTO(result.getInt(1), result.getInt(2),result.getString(3),
						result.getInt(4), result.getInt(5), result.getDate(6), result.getDate(7),
						result.getInt(8), result.getBoolean(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// return produktbatch.
		return pb;

	}

	@Override
	public List<ProductBatchDTO> getProductBatchList() throws DALException {
		query = "select * from productbatch_administration";
		ResultSet result = c.doQuery(query);
		
		// Throw exception if no results found
		if(result == null){
			throw new DALException("No produktbatches found");
		}
		
		List<ProductBatchDTO> pb = new ArrayList<ProductBatchDTO>();
		try {
			// is there a next row
			while(result.next()){
				pb.add(new ProductBatchDTO(result.getInt(1), result.getInt(2),result.getString(3),
						result.getInt(4), result.getInt(5), result.getDate(6), result.getDate(7),
						result.getInt(8), result.getBoolean(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return pb;
	}

	@Override
	public void createProductBatch(ProductBatchDTO productBatch) throws DALException {
		query = 
		
	}

	@Override
	public boolean isActive(ProductBatchDTO productBatch) throws DALException {
		// TODO Auto-generated method stub
		return false;
	}

}
