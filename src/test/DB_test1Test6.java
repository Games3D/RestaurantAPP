package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

public class DB_test1Test6 {

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
	public void testGoodInsert() {
		//connect to the DB
		connect();

		Random r= new Random(0);
		String username="test"+r.nextInt(100);
		System.out.println(username);

		//insert into the DB
		try {
			Statement st=conn.createStatement();			
			st.executeUpdate("insert into User values('test','test',"
					+ "'"+username+"','test','555555','1999-03-04','M','me@me.com')");
		} catch (SQLException e) {
			e.printStackTrace();
			fail("insert/update issue");
		}

		//query the DB to check what made it in
		ResultSet rs=null;
		try {
			Statement st=conn.createStatement();
			rs=st.executeQuery("select count(*) from User where USERNAME='"+username+"'");
			rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Query issue");
		}

		try {
			System.out.println(rs.getInt(1));
			assertEquals(rs.getInt(1), 1);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Results issue");
		}
	}

	@Test
	public void testBadInsert() {
		//connect to the DB
		connect();
		readFile(new File(""));

		Random r= new Random(0);
		String username="test"+r.nextInt(100);
		System.out.println(username);

		//insert into the DB
		try {
			Statement st=conn.createStatement();			
			st.executeUpdate("insert into User values('test','test',"
					+ "'"+username+"','test','555555','1999-03-04','M','me@me.com')");
		} catch (SQLException e) {
			e.printStackTrace();
			fail("insert/update issue");
		}
	}

	private ArrayList<String[]> readFile(File file) {		 
		ArrayList<String[]> results = new ArrayList<String[]>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			String st;
			while ((st = br.readLine()) != null)
				results.add(st.split(","));
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return results;
	}

}
