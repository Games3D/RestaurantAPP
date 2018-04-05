package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.junit.Test;

public class DB_test1Test9 {

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
	public void testInsertsWithGoodForeignKey() {
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

		//insert into the DB
		try {
			Statement st=conn.createStatement();			
			st.executeUpdate("insert into Routes values('"+username+"','test, nj','testb, nj','','','','','','','','asdfadf, nj','','','','','','','','kjlk, nj','','','','asdf, ny')");
		} catch (SQLException e) {
			e.printStackTrace();
			fail("insert/update issue");
		}

		//insert into the DB
		try {
			Statement st=conn.createStatement();			
			st.executeUpdate("insert into Favorites values('"+username+"','test','sdsdf','B','fav name a')");
		} catch (SQLException e) {
			e.printStackTrace();
			fail("insert/update issue");
		}
	}
	
	@Test
	public void testInsertsWithBadForeignKey_favorites() {
		//connect to the DB
		connect();
		
		String username="aaaaaa";

		//insert into the DB
		int result=-1;
		try {
			Statement st=conn.createStatement();			
			result=st.executeUpdate("insert into Favorites values('"+username+"','test','sdsdf','B','fav name a')");
		} catch (SQLException e) {
		}
		assertTrue("Favorite got inserted with a bad foreign key", result>0);
	}
	
	@Test
	public void testInsertsWithBadForeignKey_Route() {
		//connect to the DB
		connect();

		String username="aaaaaa";
		
		//insert into the DB
		int result=-1;
		try {
			Statement st=conn.createStatement();			
			result=st.executeUpdate("insert into Routes values('"+username+"','test, nj','testb, nj','','','','','','','','asdfadf, nj','','','','','','','','kjlk, nj','','','','asdf, ny')");
		} catch (SQLException e) {
		}
		assertTrue("Route got inserted with a bad foreign key", result>0);
	}
}
