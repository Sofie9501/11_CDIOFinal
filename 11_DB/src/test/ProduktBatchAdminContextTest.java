package test;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import DAL.ProduktBatchAdminContext;
import DTO.ProduktBatchAdminDTO;
import interfaces.DALException;
import interfaces.ProduktBatchAdminDAO;

public class ProduktBatchAdminContextTest {

	@Test
	public void test() {
		System.out.println("Get produktBatchAdmin med pbID = 4: ");
		ProduktBatchAdminDAO dao = new ProduktBatchAdminContext();
		try {
			ProduktBatchAdminDTO dto = dao.getProduktBatchAdmin(4);
			System.out.println(dto.toString());
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		System.out.println("\nOpretter ny produktbatch med pbID = 6: ");
		try {
			dao.createProduktBatch(new ProduktBatchAdminDTO(6, 2, "", 3, 0, null, 1));
			System.out.println(dao.getProduktBatchAdmin(6));
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		System.out.println("\nList: ");
		List list = new ArrayList<ProduktBatchAdminDTO>();
		try {
			list = dao.getProduktBatchAdminList();
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		} catch (DALException e) {
			e.printStackTrace();
		}
		
	}

}
