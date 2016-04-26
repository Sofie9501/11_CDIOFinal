package test;

import static org.junit.Assert.*;

import org.junit.Test;

import DAL.AfvejningContext;
import interfaces.AfvejningDAO;
import interfaces.DALException;

public class AfvejningTest {

	@Test
	public void test() {
		AfvejningDAO dao= new AfvejningContext();
		
		try {
			System.out.println(dao.getAfvejning(5));
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
