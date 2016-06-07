package core;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ListImpl implements DatabaseCom{

	Connector c = new Connector();
	
	@Override
	public String getOperator(int operatorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProductRecipeName(int pb_id) throws SQLException {
		
		String query = "select * from productbatch where " + pb_id + " = pb_id;";
		try {
			ResultSet responseSet = c.doQuery(query); 
			if(responseSet.next()){
				return responseSet.getString(2);
			}		
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Der var ikke nogen recipe";
	}

}
