package test;


import java.sql.SQLException;
import java.util.List;



import DAL.RaavareBatchContext;
import DTO.RaavareBatchDTO;
import interfaces.DALException;
import interfaces.RaavareBatchDAO;

public class RaavareBatchContextTest {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		RaavareBatchDAO rbd = new RaavareBatchContext();
	
			try {
				List<RaavareBatchDTO> list = rbd.getRaavareBatchList();
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i).getRaavareNavn());
				}
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RaavareBatchDTO raavare = new RaavareBatchDTO(100,"ost" , 1500,5 );
			
			try {
				rbd.createRaavareBatch(raavare);
			
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		}
	}


