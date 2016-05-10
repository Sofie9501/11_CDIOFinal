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
		System.out.println("Get produktBtachAdmin med pbId = 4: ");
		ProduktBatchAdminDAO dao = new ProduktBatchAdminContext();
		try {
			ProduktBatchAdminDTO dto = dao.getProduktBatchAdmin(4);
			System.out.println(dto.toString());
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("List: ");
		List list = new ArrayList<ProduktBatchAdminDTO>();
		try {
			list=dao.getProduktBatchAdminList();
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
