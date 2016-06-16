package core;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import DTO.RecipeCompDTO;

public class TerminalController extends Thread{
	// Class should open a connection to a terminal to a terminal.
	// if connection is lost it should try reconnecting and continue where it left off

	final String EXIT_CHAR = "X";

	enum State {OPERATOR_LOGIN,PRODUCTBATCH_SELECTION,PREPARE_WEIGHT, ADD_CONTAINER, WEIGHING, REGISTER_WEIGHT}
	String hostAddress;
	int port;
	Socket sock;
	DatabaseCom db = new Context();
	DataOutputStream outToServer;
	BufferedReader inFromServer;
	RecipeCompDTO recipeComp;
	int oprID;
	int pbID;
	float tare;
	float net;
	int ibID;


	State state = State.OPERATOR_LOGIN;

	public TerminalController(String hostAddress, int port) throws UnknownHostException, IOException{

		this.hostAddress = hostAddress;
		this.port = port;

		start();
	}

	@Override
	public void run(){

		// Create connection socket
		try {
			sock = new Socket(hostAddress, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// saving dataoutput stream
		try {
			outToServer = new DataOutputStream(sock.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		// getting referecrence to incomming data stream
		try {
			inFromServer = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// state loop
		// runs through constantly
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
			case REGISTER_WEIGHT:
				registerWeight();
				break;
			}	
		}

	}


	// used to send data to weight terminal
	private void sendData(String data){
		try {
			System.out.println("Terminal: " + hostAddress + ", sending to: " + data);
			outToServer.writeBytes(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// method for recieving data from terminal
	private String recieveData(){
		String data = null;

		// expecting a delay on recieved data
		long startTime = System.currentTimeMillis();
		try {
			while(System.currentTimeMillis()-startTime < 5000){
				data = inFromServer.readLine();
				if(data != null)
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Terminal: " + hostAddress + ", recieving from: " + data);
		return data;
	}

	// This method sends the message it has been called with and awaits for the second reply (RM20 A)
	@SuppressWarnings("deprecation")
	private String waitForReply(String message){
		//sendData("DW\n");
		String reply = null;
		long time = System.currentTimeMillis();

		String sData = "RM20 8 \"" + message + "\" \"\" \"&3\"\n";
		sendData(sData);

		while(System.currentTimeMillis() - time < 5000){

			reply = recieveData();

			// If the message has been received, it breaks out of the loop
			if(reply != null && reply.toUpperCase().startsWith("RM20 B")){
				// Waits eternally for the second response "RM20 A"
				while(true){
					reply = recieveData();

					// If the message has been received, it returns it
					if(reply != null && reply.toUpperCase().startsWith("RM20 A")){
						//Sorts "RM20 A" and the quotation marks away from the String
						reply = reply.substring(8, (reply.length()-1));
						System.out.println(reply);
						return reply;
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
		String reply = null;
		sendData("T\n");
		// waiting for reply

		reply = recieveData();

		reply = reply.substring(8, reply.length()-3);
		System.out.println(reply);
		return reply;
	}

	// This method allows the operator to weight while text is showing on the display
	private String showWeightOnDisplay(){
		// The command
		sendData("K 3\n");

		try {

			// If the first reply is "K A" it means that the command is understood
			if(recieveData().startsWith("K A")){
				
				// If the user presses the right button the weight returns "K C 4"
				if(recieveData().startsWith("K C 4")){
					
					// We now send a new command: stable weighing
					return sendS();
					
					// If the user presses the wrong button
				}else{	
					throw new DALException("WRONG INPUT, PRESS ENTER");
				}
				
				// If the command isn't received by the weight
			}else{	
				throw new DALException("An error occured");
			}
		} catch (DALException e) {
			waitForReply(e.getMessage());
		}
		return "Critical error";
	}

	// Sends a stable weighing
	private String sendS(){
		String reply = null;
		
		// Using the command "S"
		sendData("S\n");
		reply = recieveData();
		
		// The reply is sorted so we only get numbers
		reply = reply.substring(8,reply.length()-3);
		System.out.println("reply s: " + reply);
		return reply;
	}


	// The state where the operator has to log in
	private void operatorLogin(){
		
		while(true){
			
			// The operator is asked for an ID
			String msgReceived = waitForReply("Enter OPR ID");

			try{
				// The returned value is parsed
				oprID = Integer.parseInt(msgReceived);

				// We now look in our database for the name
				String oprName = db.getOperator(Integer.parseInt(msgReceived));
				
				// The name is returned. If the next response equals the exit button, the state resets
				if((waitForReply(oprName)).equals(EXIT_CHAR))
					return;
				else{
					// Next state
					state = State.PRODUCTBATCH_SELECTION;
					return;
				}

			}catch(Exception e){
				waitForReply("No operator found");
				return;
			}

		}
	}

	// The state where the product batch is decided
	private void productBatchSelection(){
		while(true){
			try {
				// The operator is asked for an ID
				String recieve = waitForReply("Enter PB ID");

				// If the response equals the exit button the state will go back to Operator_login
				if(recieve.equalsIgnoreCase(EXIT_CHAR)){
					state = State.OPERATOR_LOGIN;
					return;
				}
				
				// The reply is parsed
				pbID = Integer.parseInt(recieve);

				// The recipe name is looked up in the database
				String dbReply = db.getProductRecipeName(pbID);

				// If the name is accepted (by pressing enter) we continue to the next state
				String accept = waitForReply(dbReply);
				if(accept.equals(EXIT_CHAR)){
					state = State.OPERATOR_LOGIN;
					return;
				}
				state = State.PREPARE_WEIGHT;
				return;
				
			}  catch (DALException e){
				waitForReply("An error occured");
			} catch (NumberFormatException e){
				waitForReply("WRONG INPUT, PRESS ENTER");
			}
		}
	}

	// This state takes care of preparing the scale for weighing
	private void prepareWeight(){
		
		// The operator is asked to clear the scale
		String recieve = waitForReply("Empty scale");

		// If he/she doesn't reply with the exit button we continue
		if(recieve.equalsIgnoreCase(EXIT_CHAR)){
			state = State.OPERATOR_LOGIN;
			return;
		}
		
		// The status of the product batch is set to 1
		try {
			db.setPbStatus(pbID);
		} catch (DALException e) {
			waitForReply("An error occured");
			System.out.println(e.getMessage());
			waitForReply("Contact supervisor");
			state = State.OPERATOR_LOGIN;
		}
		
		// If successful, we send T to tare
		sendTare();
		
		// Next state
		state = State.ADD_CONTAINER;
	}

	// The operator is asked to place the first container so the weight can save the next tare
	private void addContainer(){
		try {
			//sendB("2.0");
			// The reply means the operator giving consent
			waitForReply("place container");

			// The tare is saved
			tare = Float.parseFloat(sendS());

			// weight is tared
			sendTare();

			state = State.WEIGHING;			
		}catch(Exception e){
			System.out.println(e.getMessage());
			waitForReply("WRONG INPUT, PRESS ENTER");
			return;
		}
	}

	// 
	private void weighing(){
		// The ID is checked that it exists
		try {
			// The operator is asked to enter an ID for the ingredient batch (raavarebatch)
			String reply = waitForReply("Enter ingredientbatch");
			
			// The reply is parsed
			ibID = Integer.parseInt(reply);
			
			// We check in our database that the ID's exist, have the right amount and so on
			db.checkIbId(ibID, pbID); 
			
			// We create a new DTO with the given information
			recipeComp = db.checkWeight(pbID, ibID);

			// The ID is accepted and we move onto "Register Weight"
			waitForReply("Id accepted");
			state = State.REGISTER_WEIGHT;
			
		} catch (DALException e) {
			waitForReply(e.getMessage());
		} catch (NumberFormatException e){
			waitForReply("WRONG INPUT");
		}
	}

	// The actual weighing here
	private void registerWeight(){
		waitForReply("Press ok for weighing");

		// By using the commands "K 3" and "S" we get the weight
		net = Float.parseFloat(showWeightOnDisplay());

		// Checks if the net weight meets the tolerance requirements
		float tolMax = recipeComp.getNet() + recipeComp.getTolerance() * recipeComp.getNet()/100;
		float tolMin = recipeComp.getNet() - recipeComp.getTolerance() * (recipeComp.getNet()/100);
		System.out.println("tolmax:" + tolMax);
		System.out.println("tolmin:" + tolMin);

		if(net <= tolMax && net >= tolMin){
			try {
				// Create new product batch component
				db.createProductBatchComp(pbID, ibID, tare, net, oprID);

				// The product batch have been made and the state returns to "Prepare weight"
				waitForReply("Componenet created");
				state = State.PREPARE_WEIGHT;
			} catch (DALException e) {
				e.printStackTrace();
			}

		}
		else
			waitForReply("Error, Re-weigh amount");
	}
}


