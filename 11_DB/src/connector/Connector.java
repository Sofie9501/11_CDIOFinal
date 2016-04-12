package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {
	private final String HOST = "localhost";
	private final int PORT = 8000;
	private final String DATABASE = "lab";		//Skal eventuelt ændres
	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private Connection connection;
	
	public Connector(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
			connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
		}catch (ClassNotFoundException | SQLException e){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public Connection getConnection(){
		return connection;
	}
	
	

}

