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

	final String EXIT_CHAR = "x";

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
			case REGISTER_WEIGHT:
				registerWeight();
				break;
			}	
		}

	}
	private void sendB(String weight){
		sendData("b "+ weight + "\n");
		recieveData();
	}
	private void sendData(String data){
		try {
			System.out.println("Terminal IP: " + this.hostAddress + ", Sending to Terminal: " + data);
			outToServer.writeBytes(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String recieveData(){
		String data = null;
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
		System.out.println("Terminal IP: " + this.hostAddress + ", recieving from Terminal: " + data);

		return data;
	}

	// This method sends the message it has been called with and awaits for the second reply (RM20 A)
	@SuppressWarnings("deprecation")
	private String waitForReply(String message){
		sendData("DW");
		String reply = null;
		long time = System.currentTimeMillis();

		// Waits 5 seconds to receive "RM20 B"
		while(System.currentTimeMillis() - time < 5000000){
			String sData = "RM20 8 \"" + message + "\" \"\" \"&3\"\n";
			sendData(sData);

			reply = recieveData();
			
			// If the message has been received, it breaks out of the loop
			if(reply != null && reply.toUpperCase().startsWith("RM20 B")){
				// Waits eternally for the second response "RM20 A"
				while(true){
					reply = recieveData();

					// If the message has been received, it returns it
					if(reply != null && reply.toUpperCase().startsWith("RM20 A")){
						//Sorts "RM20 A" and the quotation marks away from the String
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
		String reply = null;
		sendData("t\n");
		// waiting for reply

		reply = recieveData();

		return reply.substring(9, reply.length()-5);
	}

	private String sendS(){
		String reply = null;
		sendData("S\n");
		reply = recieveData();

		return reply.substring(9,reply.length()-5);
	}


	private void operatorLogin(){
		while(true){
			String msgReceived = waitForReply("Enter OPR ID");
		
			// tester det er et tal der er modtaget
			try{
				oprID = Integer.parseInt(msgReceived);
				String oprName =db.getOperator(Integer.parseInt(msgReceived));
				if((waitForReply("opr name: " + oprName + ", press enter")).equals(EXIT_CHAR))
					return;
				else{
					state = State.PRODUCTBATCH_SELECTION;
					return;
				}

			}catch(Exception e){
				waitForReply("WRONG INPUT, " + e.getMessage() +", PRESS ENTER" );
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
			} catch (NumberFormatException e){
				waitForReply("No letters, press enter");
			}
		}
	}

	private void prepareWeight(){
		String recieve = waitForReply("Press enter when the weight is empty.");

		if(recieve.equalsIgnoreCase(EXIT_CHAR)){
			state = State.OPERATOR_LOGIN;
			return;
		}

		try {
			db.setPbStatus(pbID);
		} catch (DALException e) {
			
			waitForReply("Error setting production status, press any key");
			System.out.println(e.getMessage());
			waitForReply("contact supervisor, press any key");
			state = State.OPERATOR_LOGIN;
		}
		sendTare();
		state = State.ADD_CONTAINER;
	}

	// The operator is asked to place the first container so the weight can tare
	private void addContainer(){
		try {
			sendB("2.0");
			// The reply means the operator giving consent
			waitForReply("Press enter when the container is placed");

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

	private void weighing(){
		// The ID is checked that it exists
		try {
			// The operator is asked to enter an ID for the ingredient batch (raavarebatch)
			ibID = Integer.parseInt(waitForReply("Enter ingredient batch ID"));
			db.checkIbId(ibID); // Kan lave til en void ? den caster bare fejl ud i stedet.
			db.setPbStatus(pbID);
			recipeComp = db.checkWeight(pbID, ibID);

			// The ID is accepted and we move onto "Register Weight"
			waitForReply("ID accepted, press enter");
			state = State.REGISTER_WEIGHT;
		} catch (DALException e) {
			waitForReply(e.getMessage() + ", Press enter");
		} catch (NumberFormatException e){
			waitForReply("No letters, press enter");
		}
	}

	private void registerWeight(){
		sendB("0.54");
		waitForReply("Weigh amount, press enter");

		// Gets the net weight
		net = Float.parseFloat(sendS());

		// Checks if the net weight meets the tolerance requirements
		if(net < (recipeComp.getNet() + (recipeComp.getTolerance() * (recipeComp.getNet()/100))) ||
				net > (recipeComp.getNet() - (recipeComp.getTolerance() * (recipeComp.getNet()/100)))){
			try {
				// Create new product batch component
				System.out.println("pbID: " + pbID + "\t\tibID: " + ibID + "\tTare: " + tare + "\tNet: " + net);
				db.createProductBatchComp(pbID, ibID, tare, net, oprID);
				
				// The product batch have been made and the state returns to "Prepare weight"
				waitForReply("Productbatch component was successfully made, press enter");
				state = State.PREPARE_WEIGHT;
			} catch (DALException e) {
				e.printStackTrace();
			}
	
		}
		else
			waitForReply("Incorrect amount. Re-weigh ingredient, press enter");
	}
}


