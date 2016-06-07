package dk.dtu.cdiofinal.server.DAL;

import java.util.ArrayList;
import java.util.List;

import dk.dtu.cdiofinal.DAO.OperatoerDAO;
import dk.dtu.cdiofinal.shared.OperatorDTO;


public class OperatorDAOList implements OperatoerDAO{

	private List<OperatorDTO> oprs = new ArrayList<OperatorDTO>();

	public OperatorDAOList(){
		oprs.add(new OperatorDTO(1, "Morten Due", 1, "260184xxxx", "qwer1234"));
		oprs.add(new OperatorDTO(2, "Casper Danielsen", 2, "010885xxxx", "qwer1234"));
		oprs.add(new OperatorDTO(3, "Sofie Larsen", 2, "241299xxxx", "qwer1234"));
		oprs.add(new OperatorDTO(4, "Brain Christensen", 3, "100685xxxx", "qwer1234"));
	}
	@Override
	public OperatorDTO getOperatoer(int oprId) throws DALException {
		for(OperatorDTO d: oprs){
			if(d.getOprID() == oprId){
				return d;
			}
		}
		return null;
	}

	@Override
	public List<OperatorDTO> getOperatoerList() throws DALException {
		return oprs;
	}

	@Override
	public void createOperatoer(OperatorDTO opr) throws DALException {
		oprs.add(opr);
	}


	@Override
	public void updateOperatoer(OperatorDTO opr) throws DALException {
		OperatorDTO oprOld = getOperatoer(opr.getOprID());
		oprs.remove(oprOld);
		oprs.add(opr);
	}


}
