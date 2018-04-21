package tests_Jared;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import testingLIB.DBconn;

class DB_test1Test2 {


	@Test
	void test() {
		DBconn DB = new DBconn();

		try {
			String userName="hjjkj";
			String firstName="";
			String lastName="h";
			String password="j";
			String email="j";
			String gender="M";
			String cellPhone="9";
			String DOB="1990-02-02";

			DB.DBupdate("INSERT INTO User "
					+ "(userName, firstName, lastName, password,"
					+ " email, gender, cellPhone, DOB)"
					+ " VALUES('"
					+userName+"','"
					+firstName+"','"
					+lastName+"','"
					+password+"','"
					+email+"','"
					+gender+"','"
					+cellPhone+"','"
					+DOB+"')");
			ResultSet RS=null;
			RS=DB.DBqueryRS("select * from User where UserName='"+userName+"'", RS);
			RS.next();

			assertEquals(RS.getString("userName").trim(),userName);
			assertEquals(RS.getString("firstName").trim(),firstName);
			assertEquals(RS.getString("lastName").trim(),lastName);
			assertEquals(RS.getString("password").trim(),password);
			assertEquals(RS.getString("email").trim(),email);
			assertEquals(RS.getString("gender").trim(),gender);
			assertEquals(RS.getString("cellPhone").trim(),cellPhone);
			assertEquals(RS.getString("DOB").trim(),DOB);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
