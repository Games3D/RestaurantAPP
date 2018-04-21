package tests_Jared;

import static org.junit.Assert.assertTrue;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import testingLIB.DBconn;

class DB_test1Test {

	@Test
	void testConnection() {
		DBconn DB = new DBconn();
		
		int table_count=0;
		try {
			DatabaseMetaData md = DB.conn.getMetaData();
			ResultSet rs = md.getTables(null, null, "%", null);
			while (rs.next()) {
			  System.out.println(rs.getString(3));
			  table_count++;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		assertTrue(table_count==3);
		
		/*try {
			Statement stmt = conn.createStatement();
			try {
				ResultSet result = stmt.executeQuery("SELECT name FROM databaseName.sys.Tables");
					result.next();
				System.out.println(result.getString(0));
				
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		fail("Error while connecting to database");*/
	}

}
