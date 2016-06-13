package core;

import DTO.RecipeCompDTO;

public interface DatabaseCom {
	public String getOperator(int operatorId) throws DALException;
	public String getProductRecipeName(int pb_id) throws DALException;
	public void checkIbId(int ib_id, int pb_id) throws DALException;
	public void createProductBatchComp(int pbID, int rbID, float tare, double net, int oprID) throws DALException;
	public void setPbStatus(int pbID) throws DALException;
	public RecipeCompDTO checkWeight(int pb_id, int ib_id) throws DALException;
	
}
