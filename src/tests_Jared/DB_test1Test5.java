package tests_Jared;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import testingLIB.DBconn;

class DB_test1Test5 {

	
	@Test
	void test() {
		DBconn DB = new DBconn();
		
		String USERNAME="";
		
		DB.DBupdate("DELETE from Routes where USERNAME='"+USERNAME+"'");
		ResultSet RS=null;
		RS=DB.DBqueryRS("select count(*) from Routes where USERNAME='"+USERNAME+"'", RS);
		try {
			RS.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		assertEquals((int)DB.DBqueryINT("select count(*) from Routes where USERNAME='"+USERNAME+"'"),0);

		//fail("Not yet implemented");
	}

}
