package test;

import org.junit.Test;

import DAL.OperatoerContext;
import DTO.OperatoerDTO;
import interfaces.DALException;
import interfaces.OperatoerDAO;

public class OperatorContextTest {

	@Test
	public void test() {
		OperatoerDAO dao = new OperatoerContext();
		try{
			OperatoerDTO dto = dao.getOperatoer(1);
			System.out.println(dto.toString());
			System.out.println();
			
		}catch(DALException e){
			e.printStackTrace();
		}
		
//		try {
//			dao.createOperatoer(new OperatoerDTO(120, "Morten Due", 1, "260184-xxxx", "mypassword"));
//		} catch (DALException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
			try {
				dao.updateOperatoer(new OperatoerDTO(120, "Morten Christiansenn", 2, "xxxxxx-xxxx", "sdælds"));
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
