package DAL;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		query = "Select * From raavarebatch_administration";
		ResultSet result = c.doQuery(query);

		if(result == null){
			throw new DALException("No Raavarebatch found");

		}
		List<RaavareBatchDTO> rbd = new ArrayList<RaavareBatchDTO>();
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
		query = "select * from raavarebatch where rb_id ="+ raavarebatch.getRbID();
		ResultSet result = c.doQuery(query);
		if (result != null){
			System.out.println("RB_ID already exsists");
		}
		else{
		query = "Call opret_raavarebatch(" + raavarebatch.getRbID()+ ", " + raavarebatch.getRaavareID() + ", " + raavarebatch.getMaengde() + ")";
		c.doQuery(query);
		}

	}

	@Override
	public void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {
		query = "Call aendre_raavarebatch(" + raavarebatch.getRbID()+ ", " + raavarebatch.getRaavareID() + ", " + raavarebatch.getMaengde() + ")";
		c.doQuery(query);

	}


}
