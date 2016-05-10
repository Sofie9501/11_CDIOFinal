package test;

import static org.junit.Assert.*;

import org.junit.Test;

import DAL.AfvejningContext;
import DTO.ProduktBatchKomponentDTO;
import interfaces.AfvejningDAO;
import interfaces.DALException;

public class AfvejningTest {

	@Test
	public void test() {
		AfvejningDAO dao = new AfvejningContext();
		
		try {
			System.out.println(dao.getAfvejning(4));
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		try {
			dao.createProduktbatchKomponent(new ProduktBatchKomponentDTO(1,5,1,50,2));
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println(dao.getAfvejning(4));
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

}
