package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class DB_test1Test15 {

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
	public void FavTrigger1() {
		//connect to the DB
		connect();

		//insert into the DB
		try {
			Statement st=conn.createStatement();			
			int results=st.executeUpdate("insert into User values('jared','Mexican',"
					+ "'23 fav rd','B','place a'");
			
			assertTrue("this didnt get inserted when it should of",results==1);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("insert/update issue");
		}
	}
	
	@Test
	public void FavTrigger2() {
		//connect to the DB
		connect();

		//insert into the DB
		try {
			Statement st=conn.createStatement();			
			int results=st.executeUpdate("insert into User values('jared','Mexican',"
					+ "'23 fav rd','W','place a'");
			
			assertTrue("this didnt get inserted when it should of",results==1);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("insert/update issue");
		}
	}
	
	@Test
	public void FavTrigger3() {
		//connect to the DB
		connect();

		//insert into the DB
		try {
			Statement st=conn.createStatement();			
			int results=st.executeUpdate("insert into User values('jared','Mexican',"
					+ "'23 fav rd','b','place a'");
			fail("the trigger didnt work");
			//assertTrue("this didnt get inserted when it should of",results==1);
		} catch (SQLException e) {
		}
	}
	
	@Test
	public void FavTrigger4() {
		//connect to the DB
		connect();

		//insert into the DB
		try {
			Statement st=conn.createStatement();			
			int results=st.executeUpdate("insert into User values('jared','Mexican',"
					+ "'23 fav rd','B','place a'");
			
			fail("the trigger didnt work");
			//assertTrue("this didnt get inserted when it should of",results==1);
		} catch (SQLException e) {
		}
	}

}
