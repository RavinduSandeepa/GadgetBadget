/**
 * 
 */
package model;

/**
 * @author Hasantha
 *
 */
//
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 


public class Feedback 
{ 
	private Connection connect() //method to connect to the DB
	 { 
		 Connection con = null; 
		 try
		 { 
		 Class.forName("com.mysql.jdbc.Driver"); 
		 
		 
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/newdb", "root", ""); //create database connection 
		 } 
		 catch (Exception e) 
		 {e.printStackTrace();} 
		 return con; 
	 } 
	
	public String insertItem(String code,String customerID,String itemID, String name) 
	 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for inserting."; } 
		 
		 String query = " insert into feedback (`FID`,`CustomerID`,`ItemID`,`FeedbackID`,`FeedBack`)"+ " values (?, ?, ?, ?, ?)"; // create a prepared statement
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 preparedStmt.setInt(1, 0); // binding values
		 preparedStmt.setString(2, code); 
		 preparedStmt.setString(3, customerID); 
		 preparedStmt.setString(4, itemID); 
		 preparedStmt.setString(5, name); 
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Inserted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while inserting the item."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	 } 
	
	public String readItems() 
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for reading."; } 
		
		 output = "<table border='1'><tr><th>FID</th>" + // Prepare the html table to be displayed
				 "<th>CustomerID</th>" + 
				  "<th>ItemID</th>"+"<th>FeedbackID</th>"+"<th>FeedBack</th>" +
		 "<th>Update</th><th>Remove</th></tr>"; 
		 
		 String query = "select * from feedback"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 
		 while (rs.next()) // iterate through the rows in the result set
		 { 
		 String FID = Integer.toString(rs.getInt("FID")); 
		 String CustomerID = rs.getString("CustomerID"); 
		 String ItemID = rs.getString("ItemID"); 
		 String FeedbackID = rs.getString("FeedbackID"); 
		 String FeedBack = rs.getString("FeedBack"); 
		
		 
		 // Add into the html table
		 output += "<tr><td>" + FID + "</td>"; 
		 output += "<td>" + CustomerID + "</td>"; 
		 output += "<td>" + ItemID + "</td>"; 
		 output += "<td>" + FeedbackID+ "</td>"; 
		 output += "<td>" + FeedBack + "</td>"; 
	
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"//designing buttons
		 + "<td><form method='post' action='items.jsp'>"
		+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
		 + "<input name='FID' type='hidden' value='" + FID 
		 + "'>" + "</form></td></tr>"; 
		 } 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while reading the items."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	 }
	
	public String updateItem(String ID,String name)
	 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 
		 String query = "UPDATE feedback SET FeedBack=? WHERE FID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); // create a prepared statement
		 
		// 
	
		 preparedStmt.setString(1, name);// binding values 
		 preparedStmt.setInt(2, Integer.parseInt(ID)); 
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while updating the item."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	 } 
	
	public String deleteItem(String FID) 
	 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for deleting."; } 
		 
		 String query = "delete from feedback where FID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); // create a prepared statement
		 
		 preparedStmt.setInt(1, Integer.parseInt(FID)); // binding values
		
		 preparedStmt.execute();  // execute the statement
		 con.close(); 
		 output = "Deleted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while deleting the item."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
 } 
	
} 
