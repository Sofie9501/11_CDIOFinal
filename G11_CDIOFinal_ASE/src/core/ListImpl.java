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
	public String getProductRecipeName(int pb_id) throws DALException {
		
		String query = "select * from productbatch where " + pb_id + " = pb_id;";
		
		try {
			ResultSet responseSet1 = c.doQuery(query);
			if(responseSet1.next()){
				if(responseSet1.getInt(8) != 2){
					return responseSet1.getString(2);
				}
			} else {
				throw new DALException("No result was found");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (DALException e) {
		// TODO Auto-generated catch block
		throw e;
		}
		return "Der er sket en slem slem fejl i systemet";
	}

	@Override
	public boolean checkRbId(int rb_id) throws DALException {
		// TODO Auto-generated method stub
		return false;
	}

}
