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
	
	enum State {OPERATOR_LOGIN}
	String hostAddress;
	int port;
	Socket sock;
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
			
				break;
		
		
			}	
		}
		// Do all logic here
	}
	private void operatorLogin(){
		
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
	
}
