package test;

import static org.junit.Assert.*;

import org.junit.Test;

import DAL.ProduktBatchAdminContext;
import DAL.ReceptContext;
import DTO.ProduktBatchAdminDTO;
import DTO.ReceptDTO;
import interfaces.DALException;
import interfaces.ProduktBatchAdminDAO;
import interfaces.ReceptDAO;

public class ReceptContextTest {

	@Test
	public void test() {
		ReceptDAO dao = new ReceptContext();
		try {
			System.out.println(dao.getReceptList().size());
			for(ReceptDTO r: dao.getReceptList()){
				System.out.println(r.toString());
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
