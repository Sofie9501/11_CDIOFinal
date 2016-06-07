package core;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class TerminalController extends Thread{
	// Class should open a connection to a terminal to a terminal.
	// if connection is lost it should try reconnecting and continue where it left off
	
	final String EXIT_CHAR = "x";
	
	enum State {OPERATOR_LOGIN,PRODUCTBATCH_SELECTION,PREPARE_WEIGHT, ADD_CONTAINER, WEIGHING}
	String hostAddress;
	int port;
	Socket sock;
	DatabaseCom db = new ListImpl();
	DataOutputStream outToServer;
	BufferedReader inFromServer;
	
	
	State state = State.OPERATOR_LOGIN;
	
	public TerminalController(String hostAddress, int port) throws UnknownHostException, IOException{
		this.hostAddress = hostAddress;
		this.port = port;
		sock = new Socket(hostAddress, port);
		outToServer = new DataOutputStream(sock.getOutputStream());
		inFromServer = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		start();
	}
	
	@Override
	public void run(){
		while(true){
			switch(state){
			case OPERATOR_LOGIN:
				operatorLogin(); // 1 og 2
				break;
			case PRODUCTBATCH_SELECTION: 
				productBatchSelection(); // 3 og 4
				break;
			case PREPARE_WEIGHT:
				prepareWeight(); // 5, 6 og 7
				break;
			case ADD_CONTAINER:
				addContainer(); // 8 og 9
				break;
			case WEIGHING:
				weighing(); // 10, 11 og 12
				break;
			}	
		}
		
	}
	
	private void sendData(String data){
		try {
			outToServer.writeBytes(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String recieveData(){
		String data = null;
		try {
			data = inFromServer.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	// This method sends the message it has been called with and awaits for the second reply (RM20 A)
	@SuppressWarnings("deprecation")
	private String waitForReply(String message){
		sendData("RM20 8 \"" + message + "\"");
		long time = System.currentTimeMillis();
		String reply = null;
		
		// Waits 5 seconds to receive "RM20 B"
		while(System.currentTimeMillis() - time < 5000){
			reply = recieveData();
			
			// If the message has been received, it breaks out of the loop
			if(reply.toUpperCase().startsWith("RM20 B")){
				// Waits eternally for the second response "RM20 A"
				while(true){
					reply = recieveData();
					
					// If the message has been received, it returns it
					if(reply.toUpperCase().startsWith("RM20 A")){
						
						//Sorts "RM20 A" and the quotation marks away from the String
						return reply.substring(8, (reply.length()-1));
					}
					try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		// If the message isn't received, the thread is killed.
		this.stop();
		
		return null;
	}
	
	
	private void operatorLogin(){
		while(true){
			String msgReceived = waitForReply("Enter OPR ID");
			int oprId;
			// tester det er et tal der er modtaget
			try{
				
				String oprName =db.getOperator(Integer.parseInt(msgReceived));
				if((waitForReply("OPR NAME:" + oprName)).equals(EXIT_CHAR))
					return;
				else{
					state = State.PRODUCTBATCH_SELECTION;
					return;
				}
				
			}catch(Exception e){
				waitForReply("WRONG INPUT, PRESS ENTER");
					return;
			}
			
		}
	}
	
	private void productBatchSelection(){
		
		while(true){
			try {
				String msgToDisplay = "Enter ProductBatch ID";
				 String reply = waitForReply(msgToDisplay);
				
				String dbReplay = "Recipe: " + db.getProductRecipeName(Integer.parseInt(reply)) + ",Press Enter";
				
				sendData(dbReplay);
				break;
			}  catch (DALException e){
				waitForReply(e.getMessage() + ", Press Enter");
			}
		}
	}

	private void prepareWeight(){
		
		
	}
	
	// The operator is asked to place the first container so the weight can tare
	private void addContainer(){
		try {
			// The reply means the operator giving consent
			String reply = waitForReply("Place first container");
			
			// The tare is saved
			float tare = Float.parseFloat(waitForReply("T"));
			
			state = State.WEIGHING;			
		}catch(Exception e){
			waitForReply("WRONG INPUT, PRESS ENTER");
				return;
		}
	}
	
	private void weighing(){
		try {
			// The operator is asked to enter an ID for the ingredientbatch (raavarebatch)
			int rbID = Integer.parseInt(waitForReply("Enter rb ID"));
			
			// The ID is checked. 
			
			// It is checked, 
			
			// Current date is added
			
			// 
			
		}catch(Exception e){
			waitForReply("WRONG INPUT, PRESS ENTER");
				return;
		}
		
	}
	

}
