package model;

import java.sql.*;
public class SellerModel {

	//A common method to connect to the DB
		private Connection connect() 
			{ 
		
					Connection con = null; 
		
					try
					{ 
						//Class.forName("com.mysql.jdbc.Driver"); 
						Class.forName("com.mysql.cj.jdbc.Driver");
						
						//Provide the correct details: DBServer/DBName, username, password 
						con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sellers", "root", "");
						
						
					} 
					catch (Exception e) 
					{ 
						e.printStackTrace();
					} 
		
					return con; 
			}
		
		public String insertItem(String Fname, String Lname, String email, String contact,String Companyname) 
		{ 

			String output = ""; 

			try
			{ 

				Connection con = connect(); 

				if (con == null) 

				{return "Error while connecting to the database for inserting."; } 

				// create a prepared statement
				String query = " insert into sellerss (`SellersId`,`FirstName`,`LastName`,`Email`,`ContactNumber`,`CompanyName`)"
							+ " values (?, ?, ?, ?, ?,?)"; 

				PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				// binding values
				 preparedStmt.setInt(1, 0); 
				 preparedStmt.setString(2, Fname); 
				 preparedStmt.setString(3, Lname); 
				 preparedStmt.setString(4, email); 
				 preparedStmt.setString(5, contact); 
				 preparedStmt.setString(6, Companyname); 

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
					output = "<table border='1'><tr><th>SellersId</th><th>First Name</th>" +
							"<th>Last Name</th>" + 
							"<th>Email</th>" +
							"<th>ContactNumber</th>" +
							"<th>CompanyName</th>" +
							"<th>Update</th><th>Remove</th></tr>"; 
		 
					String query = "select * from sellerss"; 
					Statement stmt = con.createStatement(); 
					ResultSet rs = stmt.executeQuery(query); 
		 
					// iterate through the rows in the result set
					while (rs.next()) 
					{ 
							String SellersId = Integer.toString(rs.getInt("SellersId")); 
							String FirstName = rs.getString("FirstName"); 
							String LastName = rs.getString("LastName"); 
							String Email = rs.getString("Email"); 
							String ContactNumber = rs.getString("ContactNumber");
							String CompanyName = rs.getString("CompanyName"); 
		 
							// Add into the html table
							output += "<tr><td>" + SellersId + "</td>"; 
							output += "<td>" + FirstName + "</td>"; 
							output += "<td>" + LastName + "</td>"; 
							output += "<td>" + Email + "</td>"; 
							output += "<td>" + ContactNumber + "</td>"; 
							output += "<td>" + CompanyName + "</td>"; 
		 
							// buttons
							output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
									+ "<td><form method='post' action='registration.jsp'>"
									+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
									+ "<input name='SellersId' type='hidden' value='" + SellersId 
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
		
		public String updateItem(String ID, String Fname, String Lname, String email, String contact , String Companyname)
		{ 
			 String output = ""; 
			 
			 try
			 { 
			 
				 	Connection con = connect(); 
			 
				 	if (con == null) 
				 	{return "Error while connecting to the database for updating."; } 
			 
				 	// create a prepared statement
				 	String query = "UPDATE sellerss SET FirstName=?,LastName=?,Email=?,ContactNumber=?,CompanyName=? WHERE SellersId=?"; 
			 
				 	PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
				 	// binding values
				 	 
					 preparedStmt.setString(1, Fname); 
					 preparedStmt.setString(2, Lname); 
					 preparedStmt.setString(3, email); 
					 preparedStmt.setString(4, contact); 
					 preparedStmt.setString(5, Companyname); 
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
		
		public String deleteItem(String SellersId )
		{ 
		 	String output = ""; 
		  
		 	try
		 	{ 
		 
		 			Connection con = connect(); 
		 
		 			if (con == null) 
		 			{return "Error while connecting to the database for deleting."; } 
		 
		 			// create a prepared statement
		 			String query = "delete from sellerss where SellersId =?"; 
		 			
		 			PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 			// binding values
		 			preparedStmt.setInt(1, Integer.parseInt(SellersId)); 
		 
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