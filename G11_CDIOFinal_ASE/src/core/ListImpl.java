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
	public void createProductBatchComp(int pbId, int rbId, float tare, double net, int oprId) throws DALException {
	}
	
	@Override
	public void checkIbId(int ibId, int pbId) throws DALException {
	}

	@Override
	public void setPbStatus(int pbId) throws DALException {
		
	}

	@Override
	public RecipeCompDTO checkWeight(int pbId, int ibId) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}


}
