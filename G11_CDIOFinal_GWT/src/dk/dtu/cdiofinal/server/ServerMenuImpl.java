package dk.dtu.cdiofinal.server;

import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.cdiofinal.DAO.OperatorDAO;
import dk.dtu.cdiofinal.client.serverconnection.MenuService;
import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.server.DAL.MySQL.OperatorDAOMySql;
import dk.dtu.cdiofinal.shared.OperatorDTO;

@SuppressWarnings("serial")
public class ServerMenuImpl extends RemoteServiceServlet implements MenuService{
	
	OperatorDAO dao = new OperatorDAOMySql();

	
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
	public String loggedInName() {
		HttpSession session = this.getThreadLocalRequest().getSession();
		return (String) session.getAttribute("Name");
	}

	@Override
	public boolean login(int oprId, String password) {
		OperatorDTO opr;
		try {
			opr = dao.getOperator(oprId);
		} catch (DALException e) {
			return false; 
		}
		if(opr != null)
			if(password.equals(opr.getPassword()) && opr.isActive()){
				HttpSession session = this.getThreadLocalRequest().getSession();
				session.setAttribute("loggedIn", opr.getRole());
				session.setAttribute("Name", opr.getName());
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
