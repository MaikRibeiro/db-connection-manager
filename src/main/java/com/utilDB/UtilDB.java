package com.utilDB;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class UtilDB {
	public static Connection open() {
		Properties props = new Properties();

		String url = null;
		String user = null;
		String password = null;

		Connection conn = null;
		InputStream input = null; 

		try {

			input = UtilDB.class.getClassLoader().getResourceAsStream("application.properties");

			if (input == null) {
				System.err.println("Unable to find application.properties");
				return null;
			}

			props.load(input);

			url = props.getProperty("DB.URL");
			user = props.getProperty("DB.USER");
			password = props.getProperty("DB.PASSWORD");

			conn = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			System.err.println("Error while starting the connection to the database" + e);

		} finally {

			if (input != null) {
				 try {
					 input.close();
				 } catch (Exception e) {
					 System.err.println("Error closing inputStream" + e); 
				 }
			 }
		}
		return conn;
	}
		// Forma 1 de liberar recursos
//	public static void close(Object... resources) {
//		
//		for (Object obj : resources) {
//
//			if (obj == null) {
//				Log.e("Cannot close a null object. Skipping.");
//				continue;
//			}
//
//			try {
//				
//				if (obj instanceof ResultSet) {
//					((ResultSet) obj).close();
//					continue;
//					
//				} else if (obj instanceof PreparedStatement) {
//					((PreparedStatement) obj).close();
//					continue;
//					
//				} else if (obj instanceof Connection) {
//					((Connection) obj).close();
//
//				} else {
//          Log.e("Unknown resource type: " + obj.getClass().getName());
//				}
//
//			} catch (Exception e) { 
//				Log.e("Error while closing resource: " + obj.getClass().getName(), e);
//			}
//		}
//	}
	
	// Forma 2 de liberar recursos
	public static void close(AutoCloseable ...resources) {
		if (resources == null || resources.length == 0) {
			System.out.println("Resource must be greather 0");
			return;
		}

		for (int i = resources.length - 1; i >= 0; i--) {
      AutoCloseable resource = resources[i];
      try {
          if (resource != null) {
              resource.close();
          }
      } catch (Exception e) {
          System.out.println("Error while closing resource: " + e.getMessage());
      }
		}
	}

	// USAGE EXAMPLE
	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = UtilDB.open();

			ps = conn.prepareStatement("select * from tbl_test");
			System.out.println(ps);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt("tbl_test_id"));
				System.out.println(rs.getString("tbl_test_name"));
			}
			
		} catch (Exception e) {
			System.err.println("Error on select" + e);

		} finally {
			UtilDB.close(rs, ps, conn);
		}
	}
}