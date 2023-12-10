package com.paf.rangeapi;

import java.util.List;

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
import com.paf.rangeapi.models.range;
import com.paf.rangeapi.models.rangeDAO;

@Path("ranges")
public class rangeResource {

	rangeDAO rangeDAO = new rangeDAO();

	// Retrieve
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getRanges() {
		return rangeDAO.getRange();
	}

	// Insert
	@POST
	@Path("range")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertRange(@FormParam("rangeID") String rangeID, @FormParam("upperLimit") String upperLimit,
			@FormParam("lowerLimit") String lowerLimit, @FormParam("unitPrice") String unitPrice) {
		String output = rangeDAO.insertRange(rangeID, upperLimit, lowerLimit, unitPrice);
		return output;
	}

	// Update Function
	@PUT
	@Path("range")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String rangeData) {
		
		// Convert the input string to a JSON object
		JsonObject jasonObject = new JsonParser().parse(rangeData).getAsJsonObject();
		
		// Read the values from the JSON object
		String rangeID = jasonObject.get("rangeID").getAsString();
		String upperLimit = jasonObject.get("upperLimit").getAsString();
		String lowerLimit = jasonObject.get("lowerLimit").getAsString();
		String unitPrice = jasonObject.get("unitPrice").getAsString();

		String output = rangeDAO.updateRange(rangeID, upperLimit, lowerLimit, unitPrice);
		return output;
	}

	// Delete Function
	@DELETE
	@Path("range")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteRange(String rangeData) {
		
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(rangeData, "", Parser.xmlParser());
		
		// Read the value from the element <itemID>
		String rangeID = doc.select("rangeID").text();
		String output = rangeDAO.deleteRange(rangeID);
		return output;
	}
}
