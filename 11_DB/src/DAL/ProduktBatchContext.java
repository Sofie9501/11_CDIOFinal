package DAL;

import java.sql.ResultSet;
import java.util.List;

import DTO.ProduktBatchDTO;
import interfaces.DALException;
import interfaces.ProduktBatchDAO;

public class ProduktBatchContext implements ProduktBatchDAO{
	Connector c = new Connector();

	@Override
	public ProduktBatchDTO getProduktBatch(int pbID) throws DALException{
		String query = "select * from produktbatch where pb_id = " + pbID;
		ResultSet res = c.doQuery(query);
		ProduktBatchDTO pb = null;

		if(res == null)
			throw new DALException("No operators found");
		
		try {
			if(res.next()){
				pb = new ProduktBatchDTO(res.getInt(1), res.getInt(2), res.getInt(3));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pb;
	}

	@Override
	public List<ProduktBatchDTO> getProduktBatchList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createProduktBatch(ProduktBatchDTO produktbatch) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException {
		// TODO Auto-generated method stub

	}


}
