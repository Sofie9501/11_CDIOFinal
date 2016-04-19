package test;


import org.junit.Test;
import DAL.ProduktBatchAdminContext;
import DTO.ProduktBatchAdminDTO;
import interfaces.DALException;
import interfaces.ProduktBatchAdminDAO;

public class ProduktBatchAdminContextTest {

	@Test
	public void test() {
		ProduktBatchAdminDAO dao = new ProduktBatchAdminContext();
		try {
			ProduktBatchAdminDTO dto = dao.getProduktBatchAdmin(4);
			System.out.println(dto.toString());
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
