package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	/*	//connect to the DB
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
		}*/
	}

	@Test
	public void testBatchInsertThenDelete() {
		//connect to the DB
		connect();
		ArrayList<String[]> DATA = readFile(new File("C:/Users/Jared/git/RestaurantAPP/testcases/DB_test6.txt"));

		for(int i=0; i<DATA.size(); i++) {
			String[] curTest = DATA.get(i);
			//insert into the DB
			int status=-1;
			try {
				Statement st=conn.createStatement();	
				System.out.println("insert into User values('"+curTest[1]+"','"+curTest[2]+"',"
						+ "'"+curTest[3]+"','"+curTest[4]+"','"+curTest[5]+"','"+curTest[6]+"','"+curTest[7]+"','"+curTest[8]+"')");
				status=st.executeUpdate("insert into User values('"+curTest[1]+"','"+curTest[2]+"',"
						+ "'"+curTest[3]+"','"+curTest[4]+"','"+curTest[5]+"','"+curTest[6]+"','"+curTest[7]+"','"+curTest[8]+"')");
				st.close();				
			} catch (SQLException e) {
				//e.printStackTrace();
				//fail("insert/update issue");
			}
			System.out.println(status);
			if (curTest[0].equals("TRUE")) 
				assertTrue("Line "+i+" should of inserted but didn't.", status > 0);
			else
				assertTrue("Line "+i+" should not of inserted but it did.", status == 0);
			
			//delete from the DB
			try {
				Statement st=conn.createStatement();	
				System.out.println("delete from User where username='"+curTest[3]+"'");
				status=st.executeUpdate("delete from User where username='"+curTest[3]+"'");
				st.close();				
			} catch (SQLException e) {
				//e.printStackTrace();
				//fail("insert/update issue");
			}
			System.out.println(status);
			assertTrue("Line "+i+" Delete didn't work", status != 0);
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