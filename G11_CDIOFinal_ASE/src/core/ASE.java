package core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ASE {
	public final static String FILEPATH = "connections";
	public static void main(String[] args) {
		// main program
		// reading a csv file containing ports and ip addresses to use to through and to.
		// creating new threads fore every connection.
		BufferedReader connectionReader = null;
		try {
			 connectionReader = new BufferedReader(new FileReader(FILEPATH));
		} catch (FileNotFoundException e) {
			System.err.println("FILE READING ERROR!");
			System.err.println(e.getMessage());
			return;
		}
		
		while(true){
			String connectionString = null;
			try {
				connectionString = connectionReader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(connectionString == null)
				return;
			
			String[] details = connectionString.split(",");
			System.out.println(details[0] + ", " + details[1]);
		}
			
	}
	
	

}
