package test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

class DB_test1Test2 {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	/*Database credentials for AFS */
	static final String DB_URL ="jdbc:mysql://sql.njit.edu/jp834";
	static final String USER = "jp834";
	static final String PASS = "despot77";
	static Connection conn = null;
	static int size = 50;

	@Test
	void test() {
		try {
			//Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			//Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Error while connecting to database");
		}
		
		try {
			String userName="";
			String firstName="";
			String lastName="";
			String password="";
			String email="";
			String gender="";
			String cellPhone="";
			String DOB="";
			String AccessLevel="";
			
			DBupdate("INSERT INTO User "
					+ "(userName, firstName, lastName, password,"
					+ " email, gender, cellPhone, DOB, AccessLevel)"
					+ " VALUES('"
					+userName+"','"
					+firstName+"','"
					+lastName+"','"
					+password+"','"
					+email+"','"
					+gender+"','"
					+cellPhone+"','"
					+DOB+"','"
					+AccessLevel);
			ResultSet RS=null;
			RS=DBqueryRS("select * from User where UserName='"+userName+"'", RS);
			
			assertEquals(RS.getString("userName").trim(),userName);
			assertEquals(RS.getString("firstName").trim(),firstName);
			assertEquals(RS.getString("lastName").trim(),lastName);
			assertEquals(RS.getString("password").trim(),password);
			assertEquals(RS.getString("email").trim(),email);
			assertEquals(RS.getString("gender").trim(),gender);
			assertEquals(RS.getString("cellPhone").trim(),cellPhone);
			assertEquals(RS.getString("DOB").trim(),DOB);
			assertEquals(RS.getString("AccessLevel").trim(),AccessLevel);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//fail("Not yet implemented");
	}
	
	public String DBquery(String query){
 		String result="";
 		try {
 			Statement stmt1 = conn.createStatement();
 			stmt1.setFetchSize(size);
 			ResultSet rs = stmt1.executeQuery(query);
 			
 			rs.next();
 			result = rs.getString(1);
 			  	        
 			rs.close();		
 			stmt1.close();
 		} catch (SQLException e) {e.printStackTrace();}
     	return result;
     }
 	
 	/**
	 * Queries the DB using the giving query string
	 * 
	 * @param query Query string such as "insert *" or "update *"
	 */
 	public int DBupdate(String query){	
	 	Statement stmt = null;
		int r=-1;
	 	
    	try {
			 stmt = conn.createStatement();
			 r=stmt.executeUpdate(query);
			 stmt.close();
		} catch (SQLException e) {e.printStackTrace();}
    	
    	return r;
    }
 	
 	/**
	 * Queries the DB using the giving query string, only returns a int not result set
	 * 
	 * @param query Query string such as "Select *"
	 * @return first search result integer
	 */
 	public Integer DBqueryINT(String query){
 		int result=-1;
 		try {
 			Statement stmt1 = conn.createStatement();
 			stmt1.setFetchSize(size);
 			ResultSet rs = stmt1.executeQuery(query);
 			
 			rs.next();
 			result = rs.getInt(1);
 			  	        
 			rs.close();		
 			stmt1.close();
 		} catch (SQLException e) {e.printStackTrace();}
     	return result;
     }
 	
 	/**
	 * Queries the DB using the giving query string, only returns a int not result set
	 * 
	 * @param query Query string such as "Select *"
	 * @return first search result integer
	 */
 	public double DBqueryDOUBLE(String query){
 		double result=-1;
 		try {
 			Statement stmt1 = conn.createStatement();
 			stmt1.setFetchSize(size);
 			ResultSet rs = stmt1.executeQuery(query);
 			
 			rs.next();
 			result = rs.getDouble(1);
 			  	        
 			rs.close();		
 			stmt1.close();
 		} catch (SQLException e) {e.printStackTrace();}
     	return result;
     }
 	
 	/**
 	 * Queries the DB using the giving query string, only returns a result set
 	 * 
 	 * @param query Query string such as "Select *"
 	 * @param rs a blank result set to be filled with data
 	 * @return the result set now has data and will be returned
 	 */
 	public ResultSet DBqueryRS(String query, ResultSet rs){
 		try {
 			Statement stmt1 = conn.createStatement();
 			stmt1.setFetchSize(size);
 			rs = stmt1.executeQuery(query);
 			
 			//stmt1.close();
 		} catch (SQLException e) {e.printStackTrace();}
     	return rs;
     }
}
