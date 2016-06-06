package dk.dtu.cdiofinal.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.cdiofinal.client.serverconnection.operator.OperatorService;
import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.server.DAL.OperatoerDAO;
import dk.dtu.cdiofinal.server.DAL.OperatorDAOList;
import dk.dtu.cdiofinal.shared.OperatoerDTO;

@SuppressWarnings("serial")
public class OperatorServiceImpl extends RemoteServiceServlet implements OperatorService {
	OperatoerDAO dao = new OperatorDAOList();
	@Override
	public int isLoggedIn() {
		HttpSession session = this.getThreadLocalRequest().getSession();

		// return 0 if not loggedIn
		if(session.getAttribute("loggedIn") == null)
			return 0;
		else // return role number if loggedIn
			return (int)session.getAttribute("loggedIn");
	}

	@Override
	public boolean login(int oprId, String password) {
		OperatoerDTO opr;
		try {
			opr = dao.getOperatoer(oprId);
		} catch (DALException e) {
			return false; 
		}
		if(opr != null)
			if(password.equals(opr.getPassword())){
				HttpSession session = this.getThreadLocalRequest().getSession();
				session.setAttribute("loggedIn", opr.getRolle());
				return true;
			}
		return false;
	}

	@Override
	public List<OperatoerDTO> getOperators() {
		List<OperatoerDTO> list = new ArrayList<OperatoerDTO>();

		try {
			list = dao.getOperatoerList();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public boolean updateOperator(OperatoerDTO opr) {
		try {
			dao.updateOperatoer(opr);
			return true;
		} catch (DALException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean createOperator(OperatoerDTO opr) {
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

		List<OperatoerDTO> oprs;
		try {
			oprs = dao.getOperatoerList();
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
			dao.createOperatoer(opr);
			return true;
		} catch (DALException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public OperatoerDTO getOperator() {
		OperatoerDTO opr = null;
		HttpSession session = this.getThreadLocalRequest().getSession();
		Integer oprId = (Integer)session.getAttribute("loggedIn");
		if(oprId != null){
			try {
				opr = new OperatoerDTO(dao.getOperatoer(oprId));
			} catch (DALException e) {
				e.printStackTrace();
			}
		}
		return opr;
	}

	private int randInt(int min, int max) {
		return ((int)(Math.random()*(max-min)+min));
	}

	@Override
	public boolean logout() {
		HttpSession session = this.getThreadLocalRequest().getSession();
		if(session.getAttribute("loggedIn") == null)
			return true;
		
		session.setAttribute("loggedIn", 0);
		return true;
		
	}
}

