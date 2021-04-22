package com;

import model.Ship;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*;


//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Ship") 
public class ShippingService {
	
	Ship shipObj = new Ship();
	
	@GET
	@Path("/getShipD") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	{ 
		return shipObj.readItems();
		
	}
	
	@POST
	@Path("/addShipD") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertItem(@FormParam("accountID") int accountID,
							@FormParam("firstName") String firstName,
							@FormParam("lastName") String lastName,
							@FormParam("userName") String userName,
							@FormParam("email") String email, 
							@FormParam("address1") String address1,
							@FormParam("address2") String address2,
							@FormParam("country") String country,
							@FormParam("state") String state,
							@FormParam("zipCode") String zipCode) 
	{ 
			String output = shipObj.insertItem(accountID, firstName, lastName, userName, email, address1, address2, country, state, zipCode); 
			return output; 
	}
	
	@PUT
	@Path("/updateShipD") 
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
			String userName = itemObject.get("userName").getAsString();
			String email = itemObject.get("email").getAsString(); 
			String address1 = itemObject.get("address1").getAsString();
			String address2 = itemObject.get("address2").getAsString();
			String country = itemObject.get("country").getAsString();
			String state = itemObject.get("state").getAsString();
			String zipCode = itemObject.get("zipCode").getAsString();
			
			String output = shipObj.updateItem(accountID, firstName, lastName, userName, email, address1, address2, country, state, zipCode); 
	
			return output; 
	}
	
	@DELETE
	@Path("/deleteShipD") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String itemData) 
	{ 
			//Convert the input string to an XML document
			Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
	 
			//Read the value from the element <itemID>
			String accountID = doc.select("accountID").text(); 
	 
			String output = shipObj.deleteItem(accountID); 
	
			return output; 
	}

}
