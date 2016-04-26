package DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import DTO.RaavareDTO;
import interfaces.DALException;
import interfaces.RaavareDAO;

public class RaavareContext implements RaavareDAO{
	Connector c = new Connector();

	@Override
	public RaavareDTO getRaavare(int raavareId) throws DALException {
		String query = "select raavare_navn from raavare where raavare_id ="+ 
				raavareId + " group by raavare_navn";
		ResultSet result = c.doQuery(query);

		if(result == null){
			throw new DALException("Invalid ID");
		}

		RaavareDTO raavare = null;
		try {
			if(result.next()){
				raavare = new RaavareDTO(raavareId, result.getString(1), result.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return raavare;
	}

	@Override
	public List<RaavareDTO> getRaavareList() throws DALException {
		String query = "select raavare_navn, raavare_id from raavare group by raavare_navn";
		ResultSet result = c.doQuery(query);
		if(result == null){
			throw new DALException("No results. ");
		}
		List<RaavareDTO> list = null;
		try{
			while(result.next()){
				list.add(new RaavareDTO(result.getInt(1), result.getString(2), result.getString(3)));
			}	
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public void createRaavare(RaavareDTO raavare) throws DALException {
		String query = "call opret_raavare(" + raavare.getRaavareID() + ", " + raavare.getRaavareNavn() + ", " + raavare.getLeverandoer() + ");";
		c.doQuery(query);
	}

	@Override
	public void updateRaavare(RaavareDTO raavare) throws DALException {
		int ID = getRaavare(raavare.getRaavareID()).getRaavareID();
		String query = "call aendre_raavare(" + ID + ", " + raavare.getRaavareNavn() + ");";
		c.doQuery(query);
	}

}
