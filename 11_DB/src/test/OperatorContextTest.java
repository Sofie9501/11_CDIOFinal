package test;

import org.junit.Test;

import DAL.OperatoerContext;
import DTO.OperatoerDTO;
import interfaces.DALException;
import interfaces.OperatoerDAO;

public class OperatorContextTest {

	@Test
	public void test() {
		OperatoerDAO dao = new OperatoerContext();
		System.out.println("Get operatør med oprID = 1: ");
		try{
			OperatoerDTO dto = dao.getOperatoer(1);
			System.out.println(dto.toString());

		}catch(DALException e){
			e.printStackTrace();
		}
		System.out.println("Create new opr: ");
		try {
			dao.createOperatoer(new OperatoerDTO(120, "Morten Due", 1, "260184-xxxx", "mypassword"));
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Print new opr: ");
		try{
			OperatoerDTO dto = dao.getOperatoer(120);
			System.out.println(dto.toString());

		}catch(DALException e){
			e.printStackTrace();
		}
		System.out.println("Update opr med id 120: ");
		try {
			dao.updateOperatoer(new OperatoerDTO(120, "Morten Christiansenn", 2, "xxxxxx-xxxx", "sdælds"));
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Print opdateret opr: ");
		try{
			OperatoerDTO dto = dao.getOperatoer(120);
			System.out.println(dto.toString());

		}catch(DALException e){
			e.printStackTrace();
		}
	}

}
