package dbManager;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBManagerConnection {
	public Connection getConnection() {
		Connection conn = null;
		InputStream input = null;

		try {

			input = DBManagerConnection.class.getClassLoader().getResourceAsStream("Your .properties file name");

			if (input == null) {
				System.out.println("Error: .properties file not found");
				return null;
			}

			Properties props = new Properties();
			props.load(input);

			String url = props.getProperty("DB_URL");   // The key to the database URL 
			String user = props.getProperty("DB_USER"); // The key to the database USER
			String pass = props.getProperty("DB_PASS"); // The key to the database PASS

			if (url == null || user == null || pass == null) {
				System.out.println("Error: Please, check if the .properties file contains the keys for url, user and pass");
				return null;
			}

			conn = DriverManager.getConnection(url, user, pass);
			return conn;
			
		} catch (Exception e) {
			System.out.println("Error starting connection: " + e.getMessage());
            e.printStackTrace();

		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (Exception e2) {
					System.out.println("Error closing InputStream " + e2);
				}
			}
		}
		return null;
	}
}