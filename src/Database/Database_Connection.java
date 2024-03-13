package Database;

import java.sql.*;

public class Database_Connection {
	private static Connection connect;
//	localhost Postgres database connection
//	private static final String DB_DRIVER = "org.postgresql.Driver";
//	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/academic_inventory";
//	private static final String DB_USERNAME = "postgres";
//	private static final String DB_PASSWORD = "system";

//	Heroku Postgres database connection
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://ec2-52-86-193-24.compute-1.amazonaws.com:5432/d9q372luvfuuqi";
	private static final String DB_USERNAME = "jsxhfbghudcfuz";
	private static final String DB_PASSWORD = "d9504a2cb662cb309520c4b80c122dac55b61ca942a1d752a5e48ea0223260c6";
	
	public static Connection getConnection() {
		try {
			Class.forName(DB_DRIVER);
			try {
				connect = DriverManager.getConnection(DB_CONNECTION, DB_USERNAME, DB_PASSWORD);
				System.out.println("Connection Success");
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connect;
	}
}