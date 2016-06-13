package core;

import DTO.RecipeCompDTO;

public interface DatabaseCom {
	public String getOperator(int operatorId) throws DALException;
	public String getProductRecipeName(int pbId) throws DALException;
	public void checkIbId(int ibId, int pbId) throws DALException;
	public void createProductBatchComp(int pbID, int rbID, float tare, double net, int oprID) throws DALException;
	public void setPbStatus(int pbID) throws DALException;
	public RecipeCompDTO checkWeight(int pbId, int ibId) throws DALException;	
}
