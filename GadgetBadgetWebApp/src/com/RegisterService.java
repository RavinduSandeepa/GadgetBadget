package com;

import model.Register;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*;


//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Register") 
public class RegisterService {
	
		Register regObj = new Register();
	
		@GET
		@Path("/getAcc") 
		@Produces(MediaType.TEXT_HTML) 
		public String readItems() 
		{ 
			return regObj.readItems();
			
		}
		
		@POST
		@Path("/addAcc") 
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String insertItem(@FormParam("firstName") String firstName, 
								@FormParam("lastName") String lastName, 
								@FormParam("email") String email, 
								@FormParam("password") String password) 
		{ 
				String output = regObj.insertItem(firstName, lastName, email, password); 
				return output; 
		}
		
		@PUT
		@Path("/updateAcc") 
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String updateItem(String itemData) 
		{ 
				//Convert the input string to a JSON object 
				JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
		
				//Read the values from the JSON object
				String accountID = itemObject.get("accountID").getAsString(); 
				String firstName = itemObject.get("firstName").getAsString(); 
				String lastName = itemObject.get("lastName").getAsString(); 
				String email = itemObject.get("email").getAsString(); 
				String password = itemObject.get("password").getAsString(); 
				
				String output = regObj.updateItem(accountID, firstName, lastName, email, password); 
		
				return output; 
		}
		
		@DELETE
		@Path("/deleteAcc") 
		@Consumes(MediaType.APPLICATION_XML) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String deleteItem(String itemData) 
		{ 
				//Convert the input string to an XML document
				Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
		 
				//Read the value from the element <itemID>
				String accountID = doc.select("accountID").text(); 
		 
				String output = regObj.deleteItem(accountID); 
		
				return output; 
		}

}
