package dk.dtu.cdiofinal.server.DAL;

import java.util.ArrayList;
import java.util.List;

import dk.dtu.cdiofinal.DAO.OperatoerDAO;
import dk.dtu.cdiofinal.shared.OperatoerDTO;


public class OperatorDAOList implements OperatoerDAO{

	private List<OperatoerDTO> oprs = new ArrayList<OperatoerDTO>();

	public OperatorDAOList(){
		oprs.add(new OperatoerDTO(1, "Morten Due", 1, "260184xxxx", "qwer1234"));
		oprs.add(new OperatoerDTO(2, "Casper Danielsen", 2, "010885xxxx", "qwer1234"));
		oprs.add(new OperatoerDTO(3, "Sofie Larsen", 3, "241299xxxx", "qwer1234"));
		oprs.add(new OperatoerDTO(4, "Brain Christensen", 4, "100685xxxx", "qwer1234"));
	}
	@Override
	public OperatoerDTO getOperatoer(int oprId) throws DALException {
		for(OperatoerDTO d: oprs){
			if(d.getOprID() == oprId){
				return d;
			}
		}
		return null;
	}

	@Override
	public List<OperatoerDTO> getOperatoerList() throws DALException {
		return oprs;
	}

	@Override
	public void createOperatoer(OperatoerDTO opr) throws DALException {
		oprs.add(opr);
	}


	@Override
	public void updateOperatoer(OperatoerDTO opr) throws DALException {
		OperatoerDTO oprOld = getOperatoer(opr.getOprID());
		oprs.remove(oprOld);
		oprs.add(opr);
	}


}
