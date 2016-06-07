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
				addContainer(); // 8 og 8
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String recieveData(){
		String data = null;
		try {
			data = inFromServer.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	// This method sends the message it has been called with and awaits for the second reply (RM20 A)
	private String waitForReply(String message){
		sendData(message);
		long time = System.currentTimeMillis();
		String replyB = null;	
		String replyA = null;
		
		while(System.currentTimeMillis() - time < 5000){
			replyB = recieveData();
			
			if(replyB.toUpperCase().startsWith("RM20 B")){
				break;
			}
		}
		
//		while(true){
//			replyA = recieveData();
//			
//			if(replyA.toUpperCase().startsWith("RM20 A")){
//				return replyA;
//			}
//		}
	}
	
	
	private void operatorLogin(){
		// Request operator id
		String msg = "RM20 8 \"Enter OPR ID\" \"\" \"&3\"";
		sendData(msg);
		String msgReceived = waitForReply(5);
		
		// Check messaged recieved if not correct answer return and try again
		if(!msgReceived.equals("RM20 B"))
			return;
		
		while(true){
			try {
				sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			msgReceived = recieveData();
			int oprId;
			// tester det er et tal der er modtaget
			try{
				Integer.parseInt(msgReceived);
			}catch(Exception e){
				msg = "RM20 8 \"WRONG INPUT, PRESS ANY KEY\" \"\" \"&3\"";
				sendData(msg);
			}
			
		}
	}
	
	private void productBatchSelection(){
		try {
			String msgToDisplay = "RM20 8 \"Enter pb-id\"";
			String msgFromDisplay;

			sendData(msgToDisplay);
			msgFromDisplay = recieveData();
			int ID = Integer.parseInt(msgFromDisplay);
			
			String query = "select * from productbatch where " + ID + " = pb_id;";
			
			
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	private void prepareWeight(){
		
		
	}
	
	private void addContainer(){
		
	}
	
	private void weighing(){
		
	}
	

}
