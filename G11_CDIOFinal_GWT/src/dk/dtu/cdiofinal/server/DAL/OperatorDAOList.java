package dk.dtu.cdiofinal.server.DAL;

import java.util.ArrayList;
import java.util.List;

import dk.dtu.cdiofinal.DAO.OperatorDAO;
import dk.dtu.cdiofinal.shared.OperatorDTO;


public class OperatorDAOList implements OperatorDAO{

	private List<OperatorDTO> oprs = new ArrayList<OperatorDTO>();

	public OperatorDAOList(){
		oprs.add(new OperatoerDTO(1, "Morten Due", 1, "260184xxxx", "qwer1234"));
		oprs.add(new OperatoerDTO(2, "Casper Danielsen", 2, "010885xxxx", "qwer1234"));
		oprs.add(new OperatoerDTO(3, "Sofie Larsen", 3, "241299xxxx", "qwer1234"));
		oprs.add(new OperatoerDTO(4, "Brain Christensen", 4, "100685xxxx", "qwer1234"));

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
	public void createOperatoer(OperatorDTO opr) throws DALException {
		oprs.add(opr);
	}


	@Override
	public void updateOperatoer(OperatorDTO opr) throws DALException {
		OperatorDTO oprOld = getOperator(opr.getOprID());
		oprs.remove(oprOld);
		oprs.add(opr);
	}


}
