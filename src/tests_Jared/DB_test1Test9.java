package tests_Jared;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.junit.Test;

import testingLIB.DBconn;

public class DB_test1Test9 {

	@Test
	public void testInsertsWithGoodForeignKey() {
		//connect to the DB
		DBconn DB = new DBconn();

		Random r= new Random(0);
		String username="test"+r.nextInt(100);
		System.out.println(username);

		//insert into the DB
		try {
			Statement st=DB.conn.createStatement();			
			st.executeUpdate("insert into User values('test','test',"
					+ "'"+username+"','test','555555','1999-03-04','M','me@me.com')");
		} catch (SQLException e) {
			e.printStackTrace();
			fail("insert/update issue");
		}

		//insert into the DB
		try {
			Statement st=DB.conn.createStatement();			
			st.executeUpdate("insert into Routes values('"+username+"','test, nj','testb, nj','','','','','','','','asdfadf, nj','','','','','','','','kjlk, nj','','','','asdf, ny')");
		} catch (SQLException e) {
			e.printStackTrace();
			fail("insert/update issue");
		}

		//insert into the DB
		try {
			Statement st=DB.conn.createStatement();			
			st.executeUpdate("insert into Favorites values('"+username+"','test','sdsdf','B','fav name a')");
		} catch (SQLException e) {
			e.printStackTrace();
			fail("insert/update issue");
		}
	}
	
	@Test
	public void testInsertsWithBadForeignKey_favorites() {
		//connect to the DB
		DBconn DB = new DBconn();
		
		String username="aaaaaa";

		//insert into the DB
		int result=-1;
		try {
			Statement st=DB.conn.createStatement();			
			result=st.executeUpdate("insert into Favorites values('"+username+"','test','sdsdf','B','fav name a')");
		} catch (SQLException e) {
		}
		assertTrue("Favorite got inserted with a bad foreign key", result>0);
	}
	
	@Test
	public void testInsertsWithBadForeignKey_Route() {
		//connect to the DB
		DBconn DB = new DBconn();

		String username="aaaaaa";
		
		//insert into the DB
		int result=-1;
		try {
			Statement st=DB.conn.createStatement();			
			result=st.executeUpdate("insert into Routes values('"+username+"','test, nj','testb, nj','','','','','','','','asdfadf, nj','','','','','','','','kjlk, nj','','','','asdf, ny')");
		} catch (SQLException e) {
		}
		assertTrue("Route got inserted with a bad foreign key", result>0);
	}
}
