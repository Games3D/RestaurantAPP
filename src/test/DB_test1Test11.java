package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import utils.Finder;

public class DB_test1Test11 {

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
	public void testJavaBlackList() {
		//connect to the DB
		connect();

		//make a java object
		Finder find = new Finder("1200 Grand St, Hoboken, NJ|100 1st St, Jersey City, NJ|Jared");

		final ArrayList<String> black=find.getBlackList("jared");

		try {
			Statement st=conn.createStatement();	
			ResultSet RS=st.executeQuery("select count(*) from Favorites where username='jared' and BLACKLIST='B'");

			RS.next();
			assertTrue("Black List results are not the same as what are in the DB.", black.size() == RS.getInt(1));

			RS.close();
			st.close();				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
