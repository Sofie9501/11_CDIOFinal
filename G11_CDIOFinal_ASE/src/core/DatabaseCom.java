package core;

import java.sql.SQLException;

public interface DatabaseCom {
	public String getOperator(int operatorId);
	public String getProductRecipeName(int pb_id) throws SQLException;
	
}
