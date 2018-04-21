package tests_Jared;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import testingLIB.DBconn;

public class DB_test1Test10 {

	@Test
	public void testMassRouteInsert() {
		//connect to the DB
		DBconn DB = new DBconn();

		for (int i=1000; i<1000; i++) {
			String username="testmas"+i;
			//insert into the DB
			try {
				Statement st=DB.conn.createStatement();			
				st.executeUpdate("insert into Favorites values('jared','test','sdsdf','B','"+username+"')");
			} catch (SQLException e) {
				e.printStackTrace();
				fail("insert/update issue");
			}
		}

		//query the DB to check what made it in
		ResultSet rs=null;
		try {
			Statement st=DB.conn.createStatement();
			rs=st.executeQuery("select count(*) from Favorites where USERNAME='jared'");
			rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Query issue");
		}

		try {
			System.out.println(rs.getInt(1));
			assertTrue("not all entries were added or queried",rs.getInt(1)>= 990);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Results issue");
		}
	}

}
