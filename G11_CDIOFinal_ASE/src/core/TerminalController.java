package core;

import java.io.IOException;
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
	State state = State.OPERATOR_LOGIN;
	
	public TerminalController(String hostAddress, int port) throws UnknownHostException, IOException{
		this.hostAddress = hostAddress;
		this.port = port;
		sock = new Socket(hostAddress, port);
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
	public operatorLogin(){
		
	}

}
