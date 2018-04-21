package tests_Jared;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import testingLIB.DBconn;

class DB_test1Test3 {

	@Test
	void test() {
		DBconn DB = new DBconn();
		
		try {
			String username="jared";
			String Start="jhlkjjj";
			String End="jkh";
			
			DB.DBupdate("INSERT INTO Routes "
					+ "(username, Start, End)"
					+ " VALUES('"
					+username+"','"
					+Start+"','"
					+End+"')");
			ResultSet RS=null;
			RS=DB.DBqueryRS("select * from Routes where username='"+username+"' and Start='"+Start+"'", RS);
			RS.next();
			
			assertEquals(RS.getString("username").trim(),username);
			assertEquals(RS.getString("Start").trim(),Start);
			assertEquals(RS.getString("End").trim(),End);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
