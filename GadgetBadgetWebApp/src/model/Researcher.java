package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Researcher {
	
	//A common method to connect to the DB
		private Connection connect() 
			{ 
		
					Connection con = null; 
		
					try
					{ 
						//Class.forName("com.mysql.jdbc.Driver"); 
						Class.forName("com.mysql.jdbc.Driver");;
						
						//Provide the correct details: DBServer/DBName, username, password 
						con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget", "root", "");
						
						//con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					} 
		
					return con; 
			}
		
		public String insertItem(String name, String Address, String emailadd, String pass , String ConNum) 
		{ 

			String output = ""; 

			try
			{ 

				Connection con = connect(); 

				if (con == null) 

				{return "Error while connecting to the database for inserting."; } 

				// create a prepared statement
				String query = " insert into researchers (`accountID`,`Name`,`Address`,`email`,`password`,`ConNum`)"
							+ " values (?, ?, ?, ?, ?,?)"; 

				PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				// binding values
				 preparedStmt.setInt(1, 0); 
				 preparedStmt.setString(2, name); 
				 preparedStmt.setString(3, Address); 
				 preparedStmt.setString(4, emailadd); 
				 preparedStmt.setString(5, pass); 
				 preparedStmt.setString(6, ConNum);

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
		 
					// Prepare the html table to be displayed.
					output = "<table border='1'><tr><th>AccountID</th><th> Name</th>" +
							"<thAddress</th>" + 
							"<th>Email</th>" +
							"<th>Update</th><th>Remove</th></tr>"; 
		 
					String query = "select * from researchers"; 
					Statement stmt = con.createStatement(); 
					ResultSet rs = stmt.executeQuery(query); 
		 
					// iterate through the rows in the result set
					while (rs.next()) 
					{ 
							String accountID = Integer.toString(rs.getInt("accountID")); 
							String Name = rs.getString("Name"); 
							String Address = rs.getString("Address"); 
							String email = rs.getString("email"); 
							String password = rs.getString("password");
							String ConNum = rs.getString("ConNum");
		 
							// Add into the html table
							output += "<tr><td>" + accountID + "</td>"; 
							output += "<td>" + Name + "</td>"; 
							output += "<td>" + Address + "</td>"; 
							output += "<td>" + email + "</td>"; 
							output += "<td>" + password + "</td>";
							output += "<td>" + ConNum + "</td>";
		 
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
		
		public String updateItem(String ID, String Name, String Address, String emailadd, String pass , String ConNum)
		{ 
			 String output = ""; 
			 
			 try
			 { 
			 
				 	Connection con = connect(); 
			 
				 	if (con == null) 
				 	{return "Error while connecting to the database for updating."; } 
			 
				 	// create a prepared statement
				 	String query = "UPDATE researchers SET Name=?,Address=?,email=?,password=?,ConNum=? WHERE accountID=?"; 
			 
				 	PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
				 	// binding values
				 	preparedStmt.setString(1, Name); 
				 	preparedStmt.setString(2, Address); 
				 	preparedStmt.setString(3, emailadd);
				 	preparedStmt.setString(4, pass); 
				 	preparedStmt.setString(5, ConNum);
				 	preparedStmt.setInt(6, Integer.parseInt(ID)); 
			 
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
		 			String query = "delete from  researchers where accountID=?"; 
		 			
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
