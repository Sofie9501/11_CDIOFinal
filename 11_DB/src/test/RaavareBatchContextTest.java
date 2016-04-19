package test;

import java.util.List;

import DAL.RaavareBatchContext;
import DTO.RaavareBatchDTO;
import interfaces.DALException;
import interfaces.RaavareBatchDAO;

public class RaavareBatchContextTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RaavareBatchDAO rbd = new RaavareBatchContext();
	
			try {
				List<RaavareBatchDTO> list = rbd.getRaavareBatchList();
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i));
				}
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		}
	}


