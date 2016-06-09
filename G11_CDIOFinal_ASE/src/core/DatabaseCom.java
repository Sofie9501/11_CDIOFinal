package core;

public interface DatabaseCom {
	public String getOperator(int operatorId) throws DALException;
	public String getProductRecipeName(int pb_id) throws DALException;
	public boolean checkIbId(int ib_id) throws DALException;
	public void createProductBatchComp(int pbID, int rbID, float tare, float net, int oprID) throws DALException;
	public void setPbStatus(int pbID, int rbID) throws DALException;
	
}
