package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {

	private static Connection conn;
	private static Statement stm;

	public static Connection connectToDatabase(String url, String username, String password) 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// call the driver class' no argument constructor
		Class.forName("com.mysql.jdbc.Driver").newInstance();

		// get Connection-object via DriverManager
		return (Connection) DriverManager.getConnection(url, username, password);
	}

	public Connector(String server, int port, String database, String username, String password)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		conn	= connectToDatabase("jdbc:mysql://"+server+":"+port+"/"+database,
				username, password);
		stm		= conn.createStatement();
	}

}

