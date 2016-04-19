package DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
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
		String query = "select recept_navn, recept_id from recept_administration group by recept_navn";
		ResultSet result = c.doQuery(query);
		
		List<ReceptDTO> list = null;
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
		// TODO Auto-generated method stub

	}

	@Override
	public void updateRecept(ReceptDTO recept) throws DALException {
		// TODO Auto-generated method stub

	}


}
