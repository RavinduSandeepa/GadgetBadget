package com;

import model.Payee;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*;


//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Pay") 
public class PaymentService {
	
	Payee payObj = new Payee();
	
	@GET
	@Path("/getCard") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	{ 
		return payObj.readItems();
		
	}
	
	@POST
	@Path("/addCard") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertItem(@FormParam("accountID") int accId, 
							@FormParam("cardType") String cardType, 
							@FormParam("nameOnCard") String nameOnCard, 
							@FormParam("cardNo") String cardNo,
							@FormParam("expireDate") String expireDate,
							@FormParam("cvv") String CVV)
	{ 
			String output = payObj.insertItem(accId, cardType, nameOnCard, cardNo, expireDate, CVV); 
			return output; 
	}
	
	@PUT
	@Path("/updateCard") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateItem(String itemData) 
	{ 
			//Convert the input string to a JSON object 
			JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
	
			//Read the values from the JSON object
			String accountID = itemObject.get("accountID").getAsString(); 
			String cardType = itemObject.get("cardType").getAsString(); 
			String nameOnCard = itemObject.get("nameOnCard").getAsString(); 
			String cardNo = itemObject.get("cardNo").getAsString();
			String expireDate = itemObject.get("expireDate").getAsString();
			String cvv = itemObject.get("cvv").getAsString(); 
			
			String output = payObj.updateItem(accountID, cardType, nameOnCard, cardNo, expireDate, cvv); 
	
			return output; 
	}
	
	@DELETE
	@Path("/deleteCard") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String itemData) 
	{ 
			//Convert the input string to an XML document
			Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
	 
			//Read the value from the element <itemID>
			String accountID = doc.select("accountID").text(); 
	 
			String output = payObj.deleteItem(accountID); 
	
			return output; 
	}

}
