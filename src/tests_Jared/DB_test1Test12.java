package tests_Jared;

import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Test;

import testingLIB.DBconn;
import utils.Finder;

public class DB_test1Test12 {

	@Test
	public void testJavaWhiteList() {
		//connect to the DB
		DBconn DB = new DBconn();
		
		//make a java object
		Finder find = new Finder("1200 Grand St, Hoboken, NJ|100 1st St, Jersey City, NJ|jared");

		final ArrayList<String> white=find.getWhiteList("jared");

		try {
			Statement st=DB.conn.createStatement();	
			ResultSet RS=st.executeQuery("select count(*) from Favorites where username='jared' and BLACKLIST='W'");

			RS.next();
			assertTrue("White List results are not the same as what are in the DB.", white.size() == RS.getInt(1));

			RS.close();
			st.close();				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
