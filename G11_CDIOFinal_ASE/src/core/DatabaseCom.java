package core;

public interface DatabaseCom {
	public String getOperator(int operatorId);
	public String getProductRecipeName(int pb_id) throws DALException;
	public boolean checkRbId(int rb_id) throws DALException;
	public void createProductBatchComp(int pbID, int rbID, float tara, float net, int oprID) throws DALException;
	public void setPbStatus() throws DALException;
	
}
