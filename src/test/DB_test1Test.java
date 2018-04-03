package test;

//import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import NN_neuroph_studio.myNN;

//import org.junit.jupiter.api.Test;

class DB_test1Test {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	/*Database credentials for AFS */
	static final String DB_URL ="jdbc:mysql://games3dcreations.ddns.net:3306/NJIT_CS684";
	static final String USER = "NJIT_CS684";
	static final String PASS = "NJIT_CS684";

	public static void main(String[] args) {
		test();
	}
	//@Test
	public static void test() {
		Connection conn = null;
		try {
			//Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			//Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Error while connecting to database");
		}
		System.out.println("Connected");
		/*try {
			assertTrue(!conn.isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}*/

		//fail("Not yet implemented");
	}

}
