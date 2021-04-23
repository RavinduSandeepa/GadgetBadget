package model;

import java.sql.*;

public class Payee {
	
			//A common method to connect to the DB
			private Connection connect() 
				{ 
			
						Connection con = null; 
			
						try
						{ 
							//Class.forName("com.mysql.jdbc.Driver"); 
							Class.forName("com.mysql.cj.jdbc.Driver");
							
							//Provide the correct details: DBServer/DBName, username, password 
							con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accountsdb","root","");
							
							//con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
						} 
						catch (Exception e) 
						{
							e.printStackTrace();
						} 
			
						return con; 
				}
			
			public String insertItem(int accID,String type, String noc, String cn, String ed,String cvv) 
			{ 
 
				String output = ""; 
 
				try
				{ 
 
					Connection con = connect(); 
 
					if (con == null) 
 
					{return "Error while connecting to the database for inserting."; } 
 
					// create a prepared statement
					String query = " insert into carddetails (`accountId`,`cardType`,`nameOnCard`,`cardNo`,`expireDate`,`cvv`)"
								+ " values (?, ?, ?, ?, ?, ?)"; 
 
					PreparedStatement preparedStmt = con.prepareStatement(query); 
					
					// binding values
					 preparedStmt.setInt(1, accID); 
					 preparedStmt.setString(2, type); 
					 preparedStmt.setString(3, noc); 
					 preparedStmt.setString(4, cn); 
					 preparedStmt.setString(5, ed);
					 preparedStmt.setString(6, cvv);

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
						output = "<table border='1'><tr><th>AccountID</th><th>Card Type</th>" +
								"<th>Name On Card</th>" + 
								"<th>Card No</th>" +
								"<th>Expire Date</th>" +
								"<th>CVV</th></tr>"; 
			 
						String query = "select * from carddetails"; 
						Statement stmt = con.createStatement(); 
						ResultSet rs = stmt.executeQuery(query); 
			 
						// iterate through the rows in the result set
						while (rs.next()) 
						{ 
								String accountID = Integer.toString(rs.getInt("accountId")); 
								String cardType = rs.getString("cardType"); 
								String nameOnCard = rs.getString("nameOnCard"); 
								String cardNo = rs.getString("cardNo"); 
								String expireDate = rs.getString("expireDate");
								String cvv = rs.getString("cvv");
			 
								// Add into the html table
								output += "<tr><td>" + accountID + "</td>"; 
								output += "<td>" + cardType + "</td>"; 
								output += "<td>" + nameOnCard + "</td>"; 
								output += "<td>" + cardNo + "</td>";
								output += "<td>" + expireDate + "</td>";
								output += "<td>" + cvv + "</td></tr>"; 
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
			
			public String updateItem(String ID, String type, String noc, String cn, String ed,String cvv)
			{ 
				 String output = ""; 
				 
				 try
				 { 
				 
					 	Connection con = connect(); 
				 
					 	if (con == null) 
					 	{return "Error while connecting to the database for updating."; } 
				 
					 	// create a prepared statement
					 	String query = "UPDATE carddetails SET cardType=?,nameOnCard=?,cardNo=?,expireDate=?,cvv=? WHERE accountId=?"; 
				 
					 	PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
					 	// binding values
					 	preparedStmt.setString(1, type); 
					 	preparedStmt.setString(2, noc); 
					 	preparedStmt.setString(3, cn);
					 	preparedStmt.setString(4, ed);
					 	preparedStmt.setString(5, cvv);
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
			 			String query = "delete from carddetails where accountId=?"; 
			 			
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
