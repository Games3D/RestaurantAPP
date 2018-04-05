package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class DB_test1Test10 {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	/*Database credentials for AFS */
	static final String DB_URL ="jdbc:mysql://games3dcreations.ddns.net:3306/NJIT_CS684";
	static final String USER = "NJIT_CS684";
	static final String PASS = "NJIT_CS684";
	Connection conn = null;

	public void connect() {
		try {
			//Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			//Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Error while connecting to database");
			fail("Connection issue");
		}
		System.out.println("Connected");
	}

	@Test
	public void testMassRouteInsert() {
		//connect to the DB
		connect();

		for (int i=1000; i<100000; i++) {
			String username="testmass"+i;
			//insert into the DB
			try {
				Statement st=conn.createStatement();			
				st.executeUpdate("insert into Favorites values('jared','test','sdsdf','B','"+username+"')");
			} catch (SQLException e) {
				e.printStackTrace();
				fail("insert/update issue");
			}
		}

		//query the DB to check what made it in
		ResultSet rs=null;
		try {
			Statement st=conn.createStatement();
			rs=st.executeQuery("select count(*) from Favorites where USERNAME='jared'");
			rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Query issue");
		}

		try {
			System.out.println(rs.getInt(1));
			assertTrue("not all entries were added or queried",rs.getInt(1)>= 99000);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Results issue");
		}
	}

}
