package test;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnect {
	public static String connect(String url, String user, String passwd) {

		// Load the Connector/J driver
		try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		// Establish connection to MySQL
			Connection conn = DriverManager.getConnection(url, user, passwd);
			return "Login success!";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Login failure";
		}
	}
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/syslog";
		String username = "sql";
		String password = "123";

		System.out.println("Connecting database...");

		String s = connect(url, username, password);
		System.out.println(s);
	}
}
