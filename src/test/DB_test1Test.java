package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class DB_test1Test {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	/*Database credentials for AFS */
	static final String DB_URL ="jdbc:mysql://sql.njit.edu/jp834";
	static final String USER = "jp834";
	static final String PASS = "despot77";

	@Test
	void test() {
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
		
		try {
			assertTrue(!conn.isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//fail("Not yet implemented");
	}

}
