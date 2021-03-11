package bok.util;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	Logger log = Logger.getRootLogger();
	public static String URL;

	public static String USERNAME;

	public static String PASSWORD;

	public static String DB_NAME;

	private static ConnectionFactory connectionFactory = null;
	
	private ConnectionFactory() {
		
		DB_NAME = "BankOfKevin";
		
		URL = "jdbc:postgresql://" + "localhost" + ":5432/" + DB_NAME + "?currentSchema=\"Accounts\"";

		USERNAME = "postgres";

		PASSWORD = "l0lInternet!";
	}
	
	public Connection createConnection() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Failed to load Driver");
		}

		log.info("URL : " + URL);

		try {
			System.out.println();
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("Failed to connect to DB", e);
		}
		return null;
	}
	
	public static synchronized Connection getConnection() {
		
		if (connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
		}

		return connectionFactory.createConnection();

	}
}
