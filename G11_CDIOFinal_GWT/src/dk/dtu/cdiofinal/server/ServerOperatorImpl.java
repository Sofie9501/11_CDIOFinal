package dk.dtu.cdiofinal.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.dtu.cdiofinal.DAO.OperatorDAO;
import dk.dtu.cdiofinal.client.serverconnection.operator.OperatorService;
import dk.dtu.cdiofinal.server.DAL.DALException;
import dk.dtu.cdiofinal.server.DAL.MySQL.OperatorDAOMySql;
import dk.dtu.cdiofinal.shared.DTOVerifier;
import dk.dtu.cdiofinal.shared.OperatorDTO;

@SuppressWarnings("serial")
public class ServerOperatorImpl extends RemoteServiceServlet implements OperatorService {
	private OperatorDAO dao = new OperatorDAOMySql();

	//used to get list of all operators
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

	//used to update opr
	@Override
	public boolean updateOperator(OperatorDTO opr, int oldID) {
		if (DTOVerifier.VerifyOperatorDTO(opr)){
			try {
				dao.updateOperator(opr, oldID);
				return true;
			} catch (DALException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	//used to create opr
	@Override
	public boolean createOperator(OperatorDTO opr) {
		if (DTOVerifier.VerifyOperatorDTO(opr)){
			try {
				dao.createOperator(opr);
				return true;
			} catch (DALException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	//get logged in opr
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

