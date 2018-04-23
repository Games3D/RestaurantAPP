package tests_Jared;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import testingLIB.DBconn;

public class DB_test1Test15 {

	@Test
	public void FavTrigger1() {
		//connect to the DB
		DBconn DB = new DBconn();
		
		//insert into the DB
		try {
			Statement st=DB.conn.createStatement();			
			int results=st.executeUpdate("insert into Favorites values('jared','Mexican',"
					+ "'23 fav rd','B','place akk')");
			
			assertTrue("this didnt get inserted when it should of",results==1);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("insert/update issue");
		}
	}
	
	@Test
	public void FavTrigger2() {
		//connect to the DB
		DBconn DB = new DBconn();
		
		//insert into the DB
		try {
			Statement st=DB.conn.createStatement();			
			int results=st.executeUpdate("insert into Favorites values('jared','Mexican',"
					+ "'23 fav rd','W','place sssk')");
			
			assertTrue("this didnt get inserted when it should of",results==1);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("insert/update issue");
		}
	}
	
	@SuppressWarnings("unused")
	@Test
	public void FavTrigger3() {
		//connect to the DB
		DBconn DB = new DBconn();
		
		//insert into the DB
		try {
			Statement st=DB.conn.createStatement();			
			int results=st.executeUpdate("insert into Favorites values('jared','Mexican',"
					+ "'23 fav rd','b','place akk')");
			fail("the trigger didnt work");
			//assertTrue("this didnt get inserted when it should of",results==1);
		} catch (SQLException e) {
		}
	}
	
	@SuppressWarnings("unused")
	@Test
	public void FavTrigger4() {
		//connect to the DB
		DBconn DB = new DBconn();
		
		//insert into the DB
		try {
			Statement st=DB.conn.createStatement();			
			int results=st.executeUpdate("insert into Favorites values('jared','Mexican',"
					+ "'23 fav rd','B','place akk')");
			
			fail("the trigger didnt work");
			//assertTrue("this didnt get inserted when it should of",results==1);
		} catch (SQLException e) {
		}
	}

}
