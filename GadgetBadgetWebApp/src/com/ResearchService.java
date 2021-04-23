package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Researcher;

@Path("/Research")
public class ResearchService {
	
	Researcher regObj = new Researcher();
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	{ 
		return regObj.readItems();
		
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertItem(@FormParam("Name") String Name, 
							@FormParam("Address") String Address, 
							@FormParam("email") String email, 
							@FormParam("password") String password,
							@FormParam("ConNum") String ConNum)
	{ 
			String output = regObj.insertItem(Name, Address, email, password,ConNum); 
			return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateItem(String itemData) 
	{ 
			//Convert the input string to a JSON object .
			JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
	
			//Read the values from the JSON object.
			String accountID = itemObject.get("accountID").getAsString(); 
			String Name = itemObject.get("Name").getAsString(); 
			String Address = itemObject.get("Address").getAsString(); 
			String email = itemObject.get("email").getAsString(); 
			String password = itemObject.get("password").getAsString();
			String ConNum = itemObject.get("ConNum").getAsString();
			
			String output = regObj.updateItem(accountID, Name, Address, email, password , ConNum); 
	
			return output; 
	}
	
	@DELETE
	@Path("/") 
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
