package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exception.SystemException;

//import exception.SystemException;

public class DBUtil {

	
	
static Connection conn;
	
	static {
		//step 1  - Load the driver into the memory
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static Connection obtainConnection()throws SystemException {
		// step 2 - establish connection with DB
		// specify ip address, port number, protocol, credentials
		// connection URL has protocol//ipaddress:portnumber/dbname

	String connectionUrl = "jdbc:postgresql://localhost:5432/bank_management";
	String userName = "postgres";
	String password = "Samia@123";
	
	if(conn == null) {
		
			try {
				conn = DriverManager.getConnection(connectionUrl, userName, password);
			} catch (SQLException e) {
				throw new SystemException();
				
			}
		
	}
	
	return conn;
		
	}
	
	
	static void closeConnection()throws SystemException {
		try {
			conn.close();
		} catch (SQLException e) {
			throw new SystemException();
		}
	}
	
	
}
