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
	int oprID;
	int pbID;
	float tare;
	float net;
	int rbID;


	State state = State.OPERATOR_LOGIN;

	public TerminalController(String hostAddress, int port) throws UnknownHostException, IOException{
		
		this.hostAddress = hostAddress;
		this.port = port;

		start();
	}

	@Override
	public void run(){
		try {
			sock = new Socket(hostAddress, port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			outToServer = new DataOutputStream(sock.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			inFromServer = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		String reply = null;
		String sData = "RM20 8 \"" + message + "\" \"\" \"&3\"\n";
		System.out.println("Sent data " + sData);
		sendData(sData);
		
		long time = System.currentTimeMillis();

			// Waits 5 seconds to receive "RM20 B"
			while(System.currentTimeMillis() - time < 5000000){
				reply = recieveData();
				
			// If the message has been received, it breaks out of the loop
				if(reply != null && reply.toUpperCase().startsWith("RM20 B")){
				// Waits eternally for the second response "RM20 A"
				while(true){
					reply = recieveData();

					// If the message has been received, it returns it
					if(reply != null && reply.toUpperCase().startsWith("RM20 A")){
						//Sorts "RM20 A" and the quotation marks away from the String
						System.out.println(reply.substring(8, (reply.length()-2)));
						return reply.substring(8, (reply.length()-2));
					}
					try {
						sleep(1000);
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
	
	private String sendTare(){
		sendData("T");
		String reply = null;
		reply = recieveData();
		return reply;
		
//		if(reply.toUpperCase().equals("T")){
//			return reply;
//		}
		
		
		
//		String reply = null;
//		sendData(message);
//
//		while(true){
//			reply = recieveData();
//
//			System.out.println(reply);
//			if(reply.toUpperCase().startsWith("T")){
//				return reply.substring(9, reply.length()-5);
//			}
//		}
	}
	
	private String sendS(String message){
		String reply = null;
		sendData(message);

		while(true){
			reply = recieveData();

			if(reply.toUpperCase().startsWith("S")){
				return reply.substring(9);
			}
		}
	}


	private void operatorLogin(){
		while(true){
			String msgReceived = waitForReply("Enter OPR ID");
			int oprId;
			// tester det er et tal der er modtaget
			try{

				String oprName =db.getOperator(Integer.parseInt(msgReceived));
				if((waitForReply("opr name: " + oprName + ", press enter")).equals(EXIT_CHAR))
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
				String recieve = waitForReply("Enter ProductBatch ID");
				
				if(recieve.equalsIgnoreCase(EXIT_CHAR)){
					state = State.OPERATOR_LOGIN;
					return;
				}
				pbID = Integer.parseInt(recieve);

				String dbReplay = "Recipe: " + db.getProductRecipeName(pbID) + ", press enter";

				waitForReply(dbReplay);
				state = State.PREPARE_WEIGHT;
				return;
			}  catch (DALException e){
				waitForReply(e.getMessage() + ", press enter");
			}
		}
	}

	private void prepareWeight(){
		String recieve = waitForReply("Press enter when the weight is empty");

		if(recieve.equalsIgnoreCase(EXIT_CHAR)){
			state = State.OPERATOR_LOGIN;
			return;
		}
		
		try {
			db.setPbStatus();
		} catch (DALException e) {
			waitForReply("Error setting production status, press any key");
			waitForReply("contact supervisor, press any key");
			state = State.OPERATOR_LOGIN;
		}
		sendTare();
		state = State.ADD_CONTAINER;
	}

	// The operator is asked to place the first container so the weight can tare
	private void addContainer(){
		try {
			// The reply means the operator giving consent
			waitForReply("Place first container");

			// The tare is saved
			tare = Float.parseFloat(sendTare());

			state = State.WEIGHING;			
		}catch(Exception e){
			waitForReply("WRONG INPUT, PRESS ENTER");
			return;
		}
	}

	private void weighing(){
		try {
			// The operator is asked to enter an ID for the ingredientbatch (raavarebatch)
			rbID = Integer.parseInt(waitForReply("Enter rb ID"));

			// The ID is checked that it exsists
			if(db.checkRbId(rbID)){

				// Gets the net weight
				net = Float.parseFloat(sendS("S"));

				// Create new productbatch component
				db.createProductBatchComp(pbID, rbID, tare, net, oprID);

				sendData("Productbatch component was successfully made");
				state = State.PREPARE_WEIGHT;
			}
			else
				throw new DALException("ID does not exist.");

		}catch(Exception e){
			waitForReply("WRONG INPUT, PRESS ENTER");
			return;
		}

	}


}
