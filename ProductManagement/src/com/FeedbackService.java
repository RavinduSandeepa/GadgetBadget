/**
 * 
 */
package com;

import model.Feedback;
/**
 * @author Hasantha
 *
 */

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

/**
 * @author Hasantha
 *
 */



@Path("/Feedbacks")//@Path annotation is used to bind URI pattern to a Java method. 
public class FeedbackService { 
	 Feedback fedobj = new Feedback(); 
	@GET
	@Path("/") //@Path annotation is used to bind URI pattern to a Java method.
	@Produces(MediaType.TEXT_HTML) 
	
	public String readItems() 
	 { 
		 return fedobj.readItems();
	 } 
	
	  //

	 
	@POST//used to update resource available on the server.
	@Path("/") //@Path annotation is used to bind URI pattern to a Java method.
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) //@Consumes all the response methods accept the specified MIME types by default.
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String insertItem(@FormParam("CustomerID") String CustomerID, 
	 
	 @FormParam("ItemID") String ItemID, @FormParam("FeedbackID") String FeedbackID, @FormParam("FeedBack") String FeedBack) 
	{ 
	 String output = fedobj.insertItem(CustomerID,ItemID,FeedbackID,FeedBack); 
	return output; 
	}

	
  
	
	@PUT//used to update resource available on the server.
	@Path("/") //@Path annotation is used to bind URI pattern to a Java method.
	@Consumes(MediaType.APPLICATION_JSON) //@Consumes all the response methods accept the specified MIME types by default.
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String updateItem(String itemData) 
	{ 
		
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); //Convert the input string to a JSON object 
	
	 String FID = itemObject.get("FID").getAsString(); 
	 String FeedBack = itemObject.get("FeedBack").getAsString(); 
	
	 String output = fedobj.updateItem(FID,FeedBack); 
	return output; 
	}
	
	 
	@DELETE
	@Path("/") //@Path annotation is used to bind URI pattern to a Java method.
	@Consumes(MediaType.APPLICATION_XML) //@Consumes all the response methods accept the specified MIME types by default.
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String deleteItem(String itemData) 
	{ 
	
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); //Convert the input string to an XML document
	 
	
	 String FID = doc.select("FID").text(); //Read the value from the element <itemID>
	 String output = fedobj.deleteItem(FID); 
	return output; 
	}

	
	
}

