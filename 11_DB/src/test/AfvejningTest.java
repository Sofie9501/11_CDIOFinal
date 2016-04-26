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
		AfvejningDAO dao= new AfvejningContext();
		
		try {
			System.out.println(dao.getAfvejning(4));
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			dao.createProduktbatchKomponent(new ProduktBatchKomponentDTO(4,20,0,1.6,1));
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println(dao.getAfvejning(4));
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
