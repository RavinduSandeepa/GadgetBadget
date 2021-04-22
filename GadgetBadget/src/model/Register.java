package model;

import java.sql.*;

public class Register {
	
	//A common method to connect to the DB
	private Connection connect() 
		{ 
	
				Connection con = null; 
	
				try
				{ 
					//Class.forName("com.mysql.jdbc.Driver"); 
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					//Provide the correct details: DBServer/DBName, username, password 
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accountsdb","root","ravinduc3303");
					
					//con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				} 
	
				return con; 
		}
	
	public String insertItem(String fname, String lname, String emailadd, String pass) 
	{ 

		String output = ""; 

		try
		{ 

			Connection con = connect(); 

			if (con == null) 

			{return "Error while connecting to the database for inserting."; } 

			// create a prepared statement
			String query = " insert into accounts (`accountID`,`firstName`,`lastName`,`email`,`password`)"
						+ " values (?, ?, ?, ?, ?)"; 

			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, fname); 
			 preparedStmt.setString(3, lname); 
			 preparedStmt.setString(4, emailadd); 
			 preparedStmt.setString(5, pass); 

			 // execute the statement
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
	 
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>AccountID</th><th>First Name</th>" +
						"<th>Last Name</th>" + 
						"<th>Email</th>" +
						"<th>Update</th><th>Remove</th></tr>"; 
	 
				String query = "select * from accounts"; 
				Statement stmt = con.createStatement(); 
				ResultSet rs = stmt.executeQuery(query); 
	 
				// iterate through the rows in the result set
				while (rs.next()) 
				{ 
						String accountID = Integer.toString(rs.getInt("accountID")); 
						String firstName = rs.getString("firstName"); 
						String lastName = rs.getString("lastName"); 
						String email = rs.getString("email"); 
						String password = rs.getString("password"); 
	 
						// Add into the html table
						output += "<tr><td>" + accountID + "</td>"; 
						output += "<td>" + firstName + "</td>"; 
						output += "<td>" + lastName + "</td>"; 
						output += "<td>" + email + "</td>"; 
	 
						// buttons
						output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
								+ "<td><form method='post' action='registration.jsp'>"
								+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
								+ "<input name='accountID' type='hidden' value='" + accountID 
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
	
	public String updateItem(String ID, String fName, String lName, String emailadd, String pass)
	{ 
		 String output = ""; 
		 
		 try
		 { 
		 
			 	Connection con = connect(); 
		 
			 	if (con == null) 
			 	{return "Error while connecting to the database for updating."; } 
		 
			 	// create a prepared statement
			 	String query = "UPDATE accounts SET firstName=?,lastName=?,email=?,password=? WHERE accountID=?"; 
		 
			 	PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
			 	// binding values
			 	preparedStmt.setString(1, fName); 
			 	preparedStmt.setString(2, lName); 
			 	preparedStmt.setString(3, emailadd);
			 	preparedStmt.setString(4, pass); 
			 	preparedStmt.setInt(5, Integer.parseInt(ID)); 
		 
			 	// execute the statement
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
	
	public String deleteItem(String accountID)
	{ 
	 	String output = ""; 
	 
	 	try
	 	{ 
	 
	 			Connection con = connect(); 
	 
	 			if (con == null) 
	 			{return "Error while connecting to the database for deleting."; } 
	 
	 			// create a prepared statement
	 			String query = "delete from accounts where accountID=?"; 
	 			
	 			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 			// binding values
	 			preparedStmt.setInt(1, Integer.parseInt(accountID)); 
	 
	 			// execute the statement
	 			preparedStmt.execute(); 
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
