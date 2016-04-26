package DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import DTO.RaavareBatchDTO;
import DTO.ReceptKompDTO;
import interfaces.DALException;
import interfaces.ReceptKompDAO;

public class ReceptKompContext implements ReceptKompDAO{
	Connector c = new Connector();
	String query;

	@Override
	public ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws DALException {
		query = "Select * From recept_administration where recept_id = " + receptId +" and raavare_id = " + raavareId;
		ResultSet result = c.doQuery(query);
		
		if(result==null){
			throw new DALException("No receptkomp found");
		}
		
		ReceptKompDTO reKomp = null;
		try{
			if(result.next()){
				reKomp = new ReceptKompDTO(result.getInt(2),result.getString(3), result.getInt(4), result.getDouble(5), result.getDouble(6));

			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return reKomp;
	}

	@Override
	public List<ReceptKompDTO> getReceptKompList(int receptId) throws DALException {
		query = "select * from recept_administration where recept_id ="+receptId;
		ResultSet result = c.doQuery(query);
		
		if (result==null){
			throw new DALException("No receptkomp found");
		}
		
		List<ReceptKompDTO> list = null;
		try {
			while(result.next()){
				list.add(new ReceptKompDTO(result.getInt(2),result.getString(3), result.getInt(4), result.getDouble(5), result.getDouble(6)));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public List<ReceptKompDTO> getReceptKompList() throws DALException {
		query = "select * from recept_administration"; 
		ResultSet result = c.doQuery(query);
		
		if (result==null){
			throw new DALException("No receptkomp found");
		}
		
		List<ReceptKompDTO> list = null;
		try {
			while(result.next()){
				list.add(new ReceptKompDTO(result.getInt(2), result.getString(3), result.getInt(4), result.getDouble(5), result.getDouble(6)));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return list;
		
	}

	@Override
	public void createReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
		query = "call opret_receptkomponent(" + receptkomponent.getReceptID() + ", " + receptkomponent.getRaavareID() + 
				", " + receptkomponent.getNom_netto() + ",  " + receptkomponent.getTolerence() + ")";
		c.doQuery(query);
		
	}

}
