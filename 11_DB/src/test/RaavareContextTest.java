package test;

import org.junit.Test;

import DAL.RaavareContext;
import DTO.RaavareDTO;
import interfaces.DALException;
import interfaces.RaavareDAO;

public class RaavareContextTest {

	RaavareDAO dao = new RaavareContext();
	@Test
	public void getRaavareListTest() {
		System.out.println("\ngetRaavareList Test");
		try {
			for(RaavareDTO r: dao.getRaavareList()){
				System.out.println(r.toString());
			}
		} catch (DALException e) {
			e.printStackTrace();
		}
	

		System.out.println("\nCreate RaavareTest");
		RaavareDTO dto = new RaavareDTO(120, "æblekage", "æblekagefabrikken");
		try {
			dao.createRaavare(dto);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(dao.getRaavare(120));
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("\nUpdate RaavareTest");
		dto = new RaavareDTO(120, "jordbærtærte", "jordbærkagefabrikken");
		try {
			dao.updateRaavare(dto);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(dao.getRaavare(120));
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
