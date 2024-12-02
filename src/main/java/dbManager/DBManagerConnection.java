package dbManager;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class DBManagerConnection {
	public Connection getConnection() {
		Connection conn = null;
		InputStream input = null;

		try {

			input = DBManagerConnection.class.getClassLoader().getResourceAsStream("application.properties");

			if (input == null) {
				System.out.println("Error: application.properties file not found");
				return null;
			}

			Properties props = new Properties();
			props.load(input);

			String url = props.getProperty("DB_URL");   // The key to the database URL 
			String user = props.getProperty("DB_USER"); // The key to the database USER
			String pass = props.getProperty("DB_PASS"); // The key to the database PASS

			if (url == null || user == null || pass == null) {
				System.out.println("Error: Please, check if the application.properties file contains the DB_URL, DB_USER and DB_PASS");
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

	public void close(Object... resources) {

		if (resources == null || resources.length == 0) {
	        System.out.println("Error: No resources provided to close.");
	        return;
	    }

		for (Object resource : resources) {
			try {
				if (resource instanceof ResultSet)
					((ResultSet) resource).close();

				else if (resource instanceof PreparedStatement)
					((PreparedStatement) resource).close();

				else if (resource instanceof Connection)
					((Connection) resource).close();
				
				else
					System.out.println("Unknow resource type: " + resource.getClass().getName());
				
			} catch (Exception e) {
				System.out.println("Error closing resource of type " + resource.getClass().getName() + ": " + e.getMessage());
	            e.printStackTrace();
			}
		}
	}
}