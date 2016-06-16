package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ASE {
	public final static String FILEPATH = "connections";

	public void run(){
		// main program
		// reading a csv file containing ports and ip addresses to use to through and to.
		// creating new threads fore every connection.
		BufferedReader connectionReader = null;
		try {
			connectionReader = new BufferedReader(new FileReader(FILEPATH));


			while(true){
				String connectionString = null;

				connectionString = connectionReader.readLine();


				if(connectionString == null)
					break;

				String[] details = connectionString.split(",");
				new TerminalController(details[0],Integer.parseInt(details[1]));
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			try {
				if(connectionReader != null){
					connectionReader.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
