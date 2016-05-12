package test;

import static org.junit.Assert.*;

import org.junit.Test;

import DAL.AfvejningContext;
import DTO.ProduktBatchKomponentDTO;
import interfaces.AfvejningDAO;
import interfaces.DALException;

public class AfvejningTest {

	//Før denne test skal køres, skal ProduktBatchAdminContextTest køres først!!!
	//Databasen må ikke nulstillet, før denne test, da den arbejder sammen
	//med testen før!!!
	@Test
	public void test() {
		AfvejningDAO dao = new AfvejningContext();

		
		try {
			dao.createProduktbatchKomponent(new ProduktBatchKomponentDTO(4,2,1,50,2));
		} catch (DALException e) {
			e.printStackTrace();
		}

		try {
			System.out.println(dao.getAfvejning(4));
		} catch (DALException e) {
			e.printStackTrace();
		}

		try {
			dao.createProduktbatchKomponent(new ProduktBatchKomponentDTO(6,5,1,50,2));
		} catch (DALException e) {
			e.printStackTrace();
		}

		try {
			System.out.println(dao.getAfvejning(6));
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
}


