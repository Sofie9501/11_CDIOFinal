package DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.ReceptDTO;
import interfaces.DALException;
import interfaces.ReceptDAO;

public class ReceptContext implements ReceptDAO{
	Connector c = new Connector();

	@Override
	public ReceptDTO getRecept(int receptID) throws DALException {
		// Convert to Data Transfer Object
		ReceptDTO recept = null;

		try {
			String query = "select recept_navn from recept_administration where recept_id ="+ 
					receptID+ " group by recept_navn";
			ResultSet result = c.doQuery(query);

			// is there a next row
			if(result.next()){
				recept = new ReceptDTO(result.getString(1), receptID);
			}
		} catch (Exception e) {
			System.out.println("Error in receiving table. ");
		}
		// return operator.
		return recept;

	}

	@Override
	public List<ReceptDTO> getReceptList() throws DALException {
		String query = "select recept_navn, recept_id from recept_administration group by recept_id";
		ResultSet result = c.doQuery(query);

		List<ReceptDTO> list = new ArrayList<ReceptDTO>();
		try{
			while(result.next()) {
				list.add(new ReceptDTO(result.getString(1), result.getInt(2)));
			}	
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void createRecept(ReceptDTO recept) throws DALException {
		String query = "call opret_recept(" + recept.getReceptID() + ", " + recept.getReceptNavn() + ");";
		c.doQuery(query);
	}

	@Override
	public void updateRecept(ReceptDTO recept) throws DALException {
		int ID = getRecept(recept.getReceptID()).getReceptID();
		String query = "call aendre_recept(" + ID + ", " + recept.getReceptNavn() + ");";
		c.doQuery(query);
	}


}
