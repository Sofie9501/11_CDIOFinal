package DAL;

import java.sql.ResultSet;

import DTO.AfvejningDTO;
import DTO.ProduktBatchKomponentDTO;
import interfaces.AfvejningDAO;
import interfaces.DALException;

public class AfvejningContext implements AfvejningDAO {
	Connector c = new Connector();
	@Override
	public AfvejningDTO getAfvejning(int pb_id) throws DALException{

		AfvejningDTO dto = null;

		try {

			String query = "SELECT * from Afvejning WHERE pb_id = " + pb_id + " LIMIT 1";
			ResultSet result = c.doQuery(query);

			// is there a next row
			if(result.next()){
				dto = new AfvejningDTO(result.getInt(1), result.getInt(2), result.getString(3), result.getDouble(4), result.getDouble(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}
		
		return dto;
	}
	@Override
	public void createProduktbatchKomponent(ProduktBatchKomponentDTO pbDTO) throws DALException {
		
		String query = "call afvejning(" + pbDTO.getPbId() + ", " +pbDTO.getRbId() + ","
				+ " " +  pbDTO.getTara() + ", " + pbDTO.getNetto() + ", " + pbDTO.getOpr_id() + " );";
		c.doQuery(query);
	}
	

}