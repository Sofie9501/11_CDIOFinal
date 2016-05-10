package test;

import java.util.ArrayList;

import org.junit.Test;

import DAL.RaavareContext;
import DAL.ReceptContext;
import DTO.ReceptDTO;
import DTO.ReceptKompDTO;
import interfaces.DALException;
import interfaces.RaavareDAO;
import interfaces.ReceptDAO;

public class ReceptQueryTest {

	@Test
	public void test() {
		ReceptDAO receptDAO = new ReceptContext();
		RaavareDAO raavareDAO = new RaavareContext();
		
		ReceptDTO recept = new ReceptDTO(7, "erza");
		
		ArrayList<ReceptKompDTO> komp = new ArrayList<ReceptKompDTO>();
		try {
			System.out.println("Opretter komponenter... ");
			ReceptKompDTO komp1 = new ReceptKompDTO(recept.getReceptID(), raavareDAO.getRaavare(1).getRaavareNavn(), 1, 0.1, 3);
			ReceptKompDTO komp2 = new ReceptKompDTO(recept.getReceptID(), raavareDAO.getRaavare(5).getRaavareNavn(), 5, 0.5, 2);
			
			komp.add(komp1);
			komp.add(komp2);
		} catch (DALException e2) {
			e2.printStackTrace();
		}

		
		try {
			System.out.println("\nCreate Recept... ");
			receptDAO.createRecept(recept, komp);
		} catch (DALException e1) {
			e1.printStackTrace();
		}

		try {
			System.out.println("\nGet Recept List: ");
			for(ReceptDTO r: receptDAO.getReceptList()){
				System.out.println(r.toString());
			}
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("\nUpdate Recept 7: ");
			receptDAO.updateRecept(new ReceptDTO(7, "olaf"));
			System.out.println(receptDAO.getRecept(7));
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

}
