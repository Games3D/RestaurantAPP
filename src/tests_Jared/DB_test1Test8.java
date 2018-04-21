package tests_Jared;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Test;

import testingLIB.DBconn;
import testingLIB.FileUtils;

public class DB_test1Test8 {

	@Test
	public void testBatchInsertThenDelete() {
		//connect to the DB
		DBconn DB = new DBconn();
		ArrayList<String[]> DATA = FileUtils.readFile(new File("C:/Users/Jared/git/RestaurantAPP/TestCases - Jared/DB_test6.txt"));

		for(int i=0; i<DATA.size(); i++) {
			String[] curTest = DATA.get(i);
			//insert into the DB
			int status=-1;
			try {
				Statement st=DB.conn.createStatement();	
				System.out.println("insert into Favorites values('"+curTest[1]+"','"+curTest[2]+"',"
						+ "'"+curTest[3]+"','"+curTest[4]+"','"+curTest[5]+"')");
				status=st.executeUpdate("insert into Favorites values('"+curTest[1]+"','"+curTest[2]+"',"
						+ "'"+curTest[3]+"','"+curTest[4]+"','"+curTest[5]+"')");
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
				Statement st=DB.conn.createStatement();	
				System.out.println("delete from Favorites where username='"+curTest[1]+"'");
				status=st.executeUpdate("delete from Favorites where username='"+curTest[1]+"'");
				st.close();				
			} catch (SQLException e) {
				//e.printStackTrace();
				//fail("insert/update issue");
			}
			System.out.println(status);
			assertTrue("Line "+i+" Delete didn't work", status != 0);
		}
	}

}
