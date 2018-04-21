package tests_Jared;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import testingLIB.DBconn;
import utils.Finder;

public class DB_test1Test13 {

	@SuppressWarnings("unused")
	@Test
	public void testFinderReturnWhite() {
		String place="Grand Vin";
		//connect to the DB
		DBconn DB = new DBconn();
		
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
			Statement st=DB.conn.createStatement();	
			System.out.println("insert into Favorites values('jared','Italian','500 Grand St, Hoboken, NJ 07030','B','"+place+"')");
			int status=st.executeUpdate("insert into Favorites values('jared','Italian','500 Grand St, Hoboken, NJ 07030','B','"+place+"')");
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
		
		assertTrue("The rating is not null or 1", !(end_rating.equals("") || end_rating.equals("0")));
	}
}
