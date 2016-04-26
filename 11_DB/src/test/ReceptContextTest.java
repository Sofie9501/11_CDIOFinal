package test;

import org.junit.Test;

import DAL.ReceptContext;
import DTO.ReceptDTO;
import interfaces.DALException;
import interfaces.ReceptDAO;

public class ReceptContextTest {

	@Test
	public void test() {
		ReceptDAO dao = new ReceptContext();
		try {
			for(ReceptDTO r: dao.getReceptList()){
				System.out.println(r.toString());
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
