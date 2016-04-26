package test;

import org.junit.Test;

import DAL.RaavareContext;
import DTO.RaavareDTO;
import interfaces.DALException;
import interfaces.RaavareDAO;

public class RaavareContextTest {

	@Test
	public void test() {
		RaavareDAO dao = new RaavareContext();
		try {
			for(RaavareDTO r: dao.getRaavareList()){
				System.out.println(r.toString());
			}
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

}
