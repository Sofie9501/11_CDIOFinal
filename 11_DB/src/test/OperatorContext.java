package test;

import static org.junit.Assert.*;

import org.junit.Test;

import DAL.OperatoerContext;
import DTO.OperatoerDTO;
import interfaces.DALException;
import interfaces.OperatoerDAO;

public class OperatorContext {

	@Test
	public void test() {
		OperatoerDAO dao = new OperatoerContext();
		try{
			OperatoerDTO dto = dao.getOperatoer(1);
			System.out.println(dto.toString());
			
		}catch(DALException e){
			e.printStackTrace();
		}
	}

}
