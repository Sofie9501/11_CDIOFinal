package test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

import DAL.RaavareBatchContext;
import DTO.RaavareBatchDTO;
import interfaces.DALException;
import interfaces.RaavareBatchDAO;

public class RaavareBatchContextTest {

	@Test
	public void test() {
		// TODO Auto-generated method stub
		RaavareBatchDAO rbd = new RaavareBatchContext();

		System.out.println("\nget raavarebatch list test");
		try {
			List<RaavareBatchDTO> list = rbd.getRaavareBatchList();
			for (RaavareBatchDTO dto: list){
				System.out.println(dto);
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\ncreate raavarebatch test");
		RaavareBatchDTO raavare = new RaavareBatchDTO(103,"pest" , 1500,5 );

		try {
			rbd.createRaavareBatch(raavare);

		} catch (DALException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		raavare = new RaavareBatchDTO(10291,"mere pest" , 2050,5 );

		try {
			rbd.createRaavareBatch(raavare);

		} catch (DALException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		System.out.println("\nget raavarebatch list for raavare id");
		try {
			
			for(RaavareBatchDTO dto: rbd.getRaavareBatchList(5)){
				System.out.println(dto);
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
