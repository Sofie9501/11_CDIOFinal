package core;

import DTO.RecipeCompDTO;

public class ListImpl implements DatabaseCom{

	//Connector c = new Connector();
	
	@Override
	public String getOperator(int operatorId) {
		return "Sofie";
	}

	@Override
	public String getProductRecipeName(int pbId) throws DALException {
		return "Citronvand";
	}

	@Override
	public void createProductBatchComp(int pbID, int rbID, float tare, double net, int oprID) throws DALException {
	}
	
	@Override
	public void checkIbId(int ib_id, int pb_id) throws DALException {
	}

	@Override
	public void setPbStatus(int pbID) throws DALException {
		
	}

	@Override
	public RecipeCompDTO checkWeight(int pbId, int ibId) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}


}
