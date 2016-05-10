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
		RaavareBatchDAO rbd = new RaavareBatchContext();

		System.out.println("\nGet Raavarebatch List:");
		try {
			List<RaavareBatchDTO> list = rbd.getRaavareBatchList();
			for (RaavareBatchDTO dto: list){
				System.out.println(dto);
			}
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		System.out.println("\nCreate raavarebatch...");
		RaavareBatchDTO raavare = new RaavareBatchDTO(103, "Pest" , 1500,5 );

		try {
			rbd.createRaavareBatch(raavare);

		} catch (DALException e) {
			System.out.println(e.getMessage());
		}
		raavare = new RaavareBatchDTO(10291,"Mere pest" , 2050,5 );

		try {
			rbd.createRaavareBatch(raavare);

		} catch (DALException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("\nGet raavarebatch list for raavare ID: ");
		try {
			
			for(RaavareBatchDTO dto: rbd.getRaavareBatchList(5)){
				System.out.println(dto);
			}
		} catch (DALException e) {
			e.printStackTrace();
		}


	}

}
