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

import model.SellerModel;


	

	@Path("/Sellers")
	
	public class SellerService {
		
		SellerModel selObj = new SellerModel();
		
		@GET
		@Path("/") 
		@Produces(MediaType.TEXT_HTML) 
		public String readItems() 
		{ 
			return selObj.readItems();
			
		}
		
		@POST
		@Path("/") 
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String insertItem(@FormParam("FirstName") String FirstName, 
								@FormParam("LastName") String LastName, 
								@FormParam("Email") String Email, 
								@FormParam("ContactNumber") String ContactNumber, 
								@FormParam("CompanyName") String CompanyName)
		{ 
				String output = selObj.insertItem(FirstName, LastName, Email, ContactNumber,CompanyName); 
				return output; 
		}
		
		@PUT
		@Path("/") 
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String updateItem(String itemData) 
		{ 
				//Convert the input string to a JSON object 
				JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
		
				//Read the values from the JSON object ....
				String SellersId = itemObject.get("SellersId").getAsString(); 
				String FirstName = itemObject.get("FirstName").getAsString(); 
				String LastName = itemObject.get("LastName").getAsString(); 
				String Email = itemObject.get("Email").getAsString(); 
				String ContactNumber = itemObject.get("ContactNumber").getAsString(); 
				String CompanyName = itemObject.get("CompanyName").getAsString(); 
				
				String output = selObj.updateItem(SellersId, FirstName, LastName, Email, ContactNumber,CompanyName); 
		
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
				String SellersId = doc.select("SellersId").text(); 
		 
				String output = selObj.deleteItem(SellersId); 
		
				return output; 
		}

	}
