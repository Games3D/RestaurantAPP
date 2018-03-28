package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

class DB_test1Test4 {

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
			String USERNAME="";
			String FOODTYPE="";
			String FAVPLACES="";
			String BLACKLIST="";
			String FAVNAME="";
			
			DBupdate("INSERT INTO Favorites "
					+ "(USERNAME, FOODTYPE, FAVPLACES, BLACKLIST, FAVNAME)"
					+ " VALUES('"
					+USERNAME+"','"
					+FOODTYPE+"','"
					+FAVPLACES+"','"
					+BLACKLIST+"','"
					+FAVNAME);
			ResultSet RS=null;
			RS=DBqueryRS("select * from Favorites where USERNAME='"+USERNAME+"'", RS);
			
			assertEquals(RS.getString("USERNAME").trim(),USERNAME);
			assertEquals(RS.getString("FOODTYPE").trim(),FOODTYPE);
			assertEquals(RS.getString("FAVPLACES").trim(),FAVPLACES);
			assertEquals(RS.getString("BLACKLIST").trim(),BLACKLIST);
			assertEquals(RS.getString("FAVNAME").trim(),FAVNAME);
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
