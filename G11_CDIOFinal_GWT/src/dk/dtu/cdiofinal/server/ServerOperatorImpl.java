package dk.dtu.cdiofinal.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.cdiofinal.DAO.OperatorDAO;
import dk.dtu.cdiofinal.client.serverconnection.operator.OperatorService;
import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.server.DAL.OperatorDAOList;
import dk.dtu.cdiofinal.shared.OperatorDTO;

@SuppressWarnings("serial")
public class ServerOperatorImpl extends RemoteServiceServlet implements OperatorService {
	OperatorDAO dao = new OperatorDAOList();

	@Override
	public List<OperatorDTO> getOperators() {
		List<OperatorDTO> list = new ArrayList<OperatorDTO>();

		try {
			list = dao.getOperatorList();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public boolean updateOperator(OperatorDTO opr, int oldID) {
		try {
			dao.updateOperator(opr, oldID);
			return true;
		} catch (DALException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean createOperator(OperatorDTO opr) {
		int passLength = 7;
		String password = ""; // hold the password to be generated
		String chars = ".-_+!?="; // String of allowed chars.
		password += (char)randInt(48, 57); //chooses a random number between 0 and 9
		password += (char)randInt(65, 90); //chooses a random capital letter between A and Z
		password += (char)randInt(97,122); //chooses a random letter between A and Z
		password += chars.charAt(randInt(0,6)); ////chooses a random char in the string "chars" 
		//Generates random chars after the 4. char. Between numbers, capital letters, letter, and symbols has in shown "chars"
		//It runs ,4 - password length, times.
		for(int i =4; i<passLength; i++){
			switch((int)(Math.random()*4)){
			case 0:	password += (char)randInt(48, 58); break;
			case 1:	password += (char)randInt(65, 91); break;
			case 2:	password += (char)randInt(97,123); break;
			case 3:	password += chars.charAt(randInt(0,7)); break;
			}
		}

		List<OperatorDTO> oprs;
		try {
			oprs = dao.getOperatorList();
			int oprId = oprs.size()+1;
			while (true){
				int same = 0;
				for (int i = 0; i < oprs.size(); i++) {
					if(oprId==oprs.get(i).getOprID()){
						same++;
					}
				}
				if (same==0){
					break;
				}
				else{
					oprId++;
				}
			}
			opr.setOprID(oprId);
			opr.setPassword(password);
		} catch (DALException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dao.createOperator(opr);
			return true;
		} catch (DALException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public OperatorDTO getOperator() {
		OperatorDTO opr = null;
		HttpSession session = this.getThreadLocalRequest().getSession();
		Integer oprId = (Integer)session.getAttribute("loggedIn");
		if(oprId != null){
			try {
				opr = dao.getOperator(oprId);
			} catch (DALException e) {
				e.printStackTrace();
			}
		}
		return opr;
	}

	private int randInt(int min, int max) {
		return ((int)(Math.random()*(max-min)+min));
	}

	
}

