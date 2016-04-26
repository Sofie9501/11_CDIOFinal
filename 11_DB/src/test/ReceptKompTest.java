package test;

import java.util.List;

import DAL.ReceptKompContext;
import DTO.RaavareBatchDTO;
import DTO.ReceptKompDTO;
import interfaces.DALException;
import interfaces.ReceptKompDAO;

public class ReceptKompTest {

	public static void main(String[] args) {
		ReceptKompDAO rkd = new ReceptKompContext();
		
		

		try {
			List<ReceptKompDTO> list = rkd.getReceptKompList(3);
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getRavareName());
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ReceptKompDTO dto = new ReceptKompDTO()
		
		
		

	}

}
