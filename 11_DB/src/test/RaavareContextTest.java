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
		System.out.println("\nGet RaavareList: ");
		try {
			for(RaavareDTO r: dao.getRaavareList()){
				System.out.println(r.toString());
			}
		} catch (DALException e) {
			e.printStackTrace();
		}
	
		System.out.println("\nCreate Raavare: ");
		RaavareDTO dto = new RaavareDTO(120, "Roastbeef", "Mooh");
		try {
			dao.createRaavare(dto);
		} catch (DALException e) {
			e.printStackTrace();
		}
		try {
			System.out.println(dao.getRaavare(120));
		} catch (DALException e) {
			e.printStackTrace();
		}

		System.out.println("\nUpdate Raavare:");
		dto = new RaavareDTO(120, "Budding", "Jello Fabrikken");
		try {
			dao.updateRaavare(dto);
		} catch (DALException e) {
			e.printStackTrace();
		}
		try {
			System.out.println(dao.getRaavare(120));
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

}
