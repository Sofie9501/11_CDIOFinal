package DAL;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;

import DTO.RaavareBatchDTO;
import interfaces.DALException;
import interfaces.RaavareBatchDAO;

public class RaavareBatchContext implements RaavareBatchDAO {
	Connector c = new Connector();
	String query;

	@Override
	public RaavareBatchDTO getRaavareBatch(int rbID) throws DALException {
		// TODO Auto-generated method stub
		query = "Select * From raavarebatch_administration where rb_id =" + rbID;
		ResultSet result = c.doQuery(query);

		if(result == null){
			throw new DALException("No Raavarebatch found");

		}
		RaavareBatchDTO rbd = null;
		try{
			if(result.next()){
				rbd = new RaavareBatchDTO(result.getInt(1),result.getString(2), result.getDouble(3), result.getInt(4));

			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return rbd;
	}

	@Override
	public List<RaavareBatchDTO> getRaavareBatchList() throws DALException {
		// TODO Auto-generated method stub
		query = "Select * From raavarebatch_administration";
		ResultSet result = c.doQuery(query);

		if(result == null){
			throw new DALException("No Raavarebatch found");

		}
		List<RaavareBatchDTO> rbd = null;
		try{
			while(result.next()){
				rbd.add(new RaavareBatchDTO(result.getInt(1),result.getString(2), result.getDouble(3), result.getInt(4)));

			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return rbd;
	}

	@Override
	public List<RaavareBatchDTO> getRaavareBatchList(int raavareID) throws DALException {
		// TODO Auto-generated method stub
		query = "Select * From raavarebatch_administration where raavare_id =" + raavareID;
		ResultSet result = c.doQuery(query);

		if(result == null){
			throw new DALException("No Raavarebatch found");

		}
		List<RaavareBatchDTO> rbd = null;
		try{
			while(result.next()){
				rbd.add(new RaavareBatchDTO(result.getInt(1),result.getString(2), result.getDouble(3), result.getInt(4)));

			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return rbd;
	}

	@Override
	public void createRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {
		// TODO Auto-generated method stub
		query = "Call opret_raavarebatch(" + raavarebatch.getRbID()+ ", " + raavarebatch.getRaavareID() + ", " + raavarebatch.getMaengde() + ")";

		c.doQuery(query);

	}

	@Override
	public void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {
		// TODO Auto-generated method stub
		query = "Call aendre_raavarebatch(" + raavarebatch.getRbID()+ ", " + raavarebatch.getRaavareID() + ", " + raavarebatch.getMaengde() + ")";

		c.doQuery(query);

	}


}
