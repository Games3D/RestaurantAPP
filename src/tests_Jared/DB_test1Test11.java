package tests_Jared;

import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Test;

import testingLIB.DBconn;
import utils.Finder;

public class DB_test1Test11 {

	

	@Test
	public void testJavaBlackList() {
		//connect to the DB
		DBconn DB = new DBconn();

		//make a java object
		Finder find = new Finder("1200 Grand St, Hoboken, NJ|100 1st St, Jersey City, NJ|Jared");

		final ArrayList<String> black=find.getBlackList("jared");

		try {
			Statement st=DB.conn.createStatement();	
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
