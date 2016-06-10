package core;

import DTO.RecipeCompDTO;

public class ListImpl implements DatabaseCom{

	//Connector c = new Connector();
	
	@Override
	public String getOperator(int operatorId) {
		return "Sofie";
	}

	@Override
	public String getProductRecipeName(int pb_id) throws DALException {
		return "Citronvand";
	}

	@Override
	public void createProductBatchComp(int pbID, int rbID, float tare, double net, int oprID) throws DALException {
	}
	
	@Override
	public boolean checkIbId(int ib_id, int pb_id) throws DALException {
		return true;
	}

	@Override
	public void setPbStatus(int pbID) throws DALException {
		
	}

	@Override
	public RecipeCompDTO checkWeight(int pb_id, int ib_id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}


}
