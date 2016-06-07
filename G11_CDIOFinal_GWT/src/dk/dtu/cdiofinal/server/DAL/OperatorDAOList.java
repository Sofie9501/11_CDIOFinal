package dk.dtu.cdiofinal.server.DAL;

import java.util.ArrayList;
import java.util.List;

import dk.dtu.cdiofinal.DAO.OperatorDAO;
import dk.dtu.cdiofinal.shared.OperatorDTO;


public class OperatorDAOList implements OperatorDAO{

	private List<OperatorDTO> oprs = new ArrayList<OperatorDTO>();

	public OperatorDAOList(){
		oprs.add(new OperatorDTO(1, "Morten Due", "260184xxxx", "qwer1234", 1, true));
		oprs.add(new OperatorDTO(2, "Casper Danielsen", "010885xxxx", "qwer1234", 2, true));
		oprs.add(new OperatorDTO(3, "Sofie Larsen", "241299xxxx", "qwer1234", 3, true));
		oprs.add(new OperatorDTO(4, "Brain Christensen", "100685xxxx", "qwer1234", 4, true));

	}
	@Override
	public OperatorDTO getOperator(int oprId) throws DALException {
		for(OperatorDTO d: oprs){
			if(d.getOprID() == oprId){
				return d;
			}
		}
		return null;
	}

	@Override
	public List<OperatorDTO> getOperatorList() throws DALException {
		return oprs;
	}

	@Override
	public void createOperator(OperatorDTO opr) throws DALException {
		oprs.add(opr);
	}


	@Override
	public void updateOperator(OperatorDTO opr, int oldID) throws DALException {
		OperatorDTO oprOld = getOperator(opr.getOprID());
		oprs.remove(oprOld);
		oprs.add(opr);
	}


}
