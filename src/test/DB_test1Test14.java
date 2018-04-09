package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import utils.Finder;

public class DB_test1Test14 {

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
	public void testFinderReturnWhite() {
		String place="Grand Vin";
		//connect to the DB
		connect();

		//make a java object
		Finder find = new Finder("1200 Grand St, Hoboken, NJ|100 1st St, Jersey City, NJ|jared");
		
		//search for the first data set
		String start_rating="";
		String[] Start_DATA=find.getDATA().split("~");
		 
		for (String curLineBIG:Start_DATA) {
			String[] curLine=curLineBIG.split("`");
			if (curLine[0].equals(place))
				start_rating=curLine[3];
		}
		
		try {
			Statement st=conn.createStatement();	
			System.out.println("insert into Favorites values('jared','Italian','500 Grand St, Hoboken, NJ 07030','W','"+place+"')");
			int status=st.executeUpdate("insert into Favorites values('jared','Italian','500 Grand St, Hoboken, NJ 07030','W','"+place+"')");
			st.close();							
		} catch (SQLException e) {
			e.printStackTrace();
			fail("insert/update issue");
		}
		
		String end_rating="";
		String[] End_DATA=find.getDATA().split("~");
		 
		for (String curLineBIG:End_DATA) {
			String[] curLine=curLineBIG.split("`");
			if (curLine[0].equals(place))
				end_rating=curLine[3];
		}
		
		assertTrue("The rating is not 5", !end_rating.equals("5"));
	}

}
