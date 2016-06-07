package dk.dtu.cdiofinal.server;

import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.cdiofinal.DAO.OperatoerDAO;
import dk.dtu.cdiofinal.client.serverconnection.MenuService;
import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.server.DAL.OperatorDAOList;
import dk.dtu.cdiofinal.shared.OperatorDTO;

@SuppressWarnings("serial")
public class ServerMenuImpl extends RemoteServiceServlet implements MenuService{
	
	// FOR TEST ONLY!!
	OperatoerDAO dao = new OperatorDAOList();
	// END OF TEST
	
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
		OperatorDTO opr;
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
	public boolean logout() {
		HttpSession session = this.getThreadLocalRequest().getSession();
		if(session.getAttribute("loggedIn") == null)
			return true;
		
		session.setAttribute("loggedIn", 0);
		return true;
		
	}

}
