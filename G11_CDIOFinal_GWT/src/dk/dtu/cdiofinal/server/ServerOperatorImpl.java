package dk.dtu.cdiofinal.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.cdiofinal.DAO.OperatorDAO;
import dk.dtu.cdiofinal.client.serverconnection.operator.OperatorService;
import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.server.DAL.MySQL.OperatorDAOMySql;
import dk.dtu.cdiofinal.shared.OperatorDTO;

@SuppressWarnings("serial")
public class ServerOperatorImpl extends RemoteServiceServlet implements OperatorService {
	private OperatorDAO dao = new OperatorDAOMySql();

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

	
}

