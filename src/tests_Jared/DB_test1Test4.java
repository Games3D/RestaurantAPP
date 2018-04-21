package tests_Jared;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import testingLIB.DBconn;

class DB_test1Test4 {

	@Test
	void test() {
		DBconn DB = new DBconn();
		
		try {
			String USERNAME="jared";
			String FOODTYPE="jhklk";
			String FAVPLACES="jh";
			String BLACKLIST="W";
			String FAVNAME="hjk";
			
			DB.DBupdate("INSERT INTO Favorites "
					+ "(USERNAME, FOODTYPE, FAVPLACES, BLACKLIST, FAVNAME)"
					+ " VALUES('"
					+USERNAME+"','"
					+FOODTYPE+"','"
					+FAVPLACES+"','"
					+BLACKLIST+"','"
					+FAVNAME+"')");
			ResultSet RS=null;
			RS=DB.DBqueryRS("select * from Favorites where USERNAME='"+USERNAME+"'", RS);
			RS.next();
			
			assertEquals(RS.getString("USERNAME").trim(),USERNAME);
			assertEquals(RS.getString("FOODTYPE").trim(),FOODTYPE);
			assertEquals(RS.getString("FAVPLACES").trim(),FAVPLACES);
			assertEquals(RS.getString("BLACKLIST").trim(),BLACKLIST);
			assertEquals(RS.getString("FAVNAME").trim(),FAVNAME);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
