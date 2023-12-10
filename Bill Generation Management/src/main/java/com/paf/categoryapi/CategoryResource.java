package com.paf.categoryapi;

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
import com.paf.categoryapi.models.Category;
import com.paf.categoryapi.models.CategoryDAO;

@Path("categories")
public class CategoryResource {

	CategoryDAO CategoryDAO = new CategoryDAO();

	// Retrieve Function
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getCategories() {
		return CategoryDAO.getAllCategories();
	}

	// Insert Function
	@POST
	@Path("category")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("categoryID") String categoryID, @FormParam("categoryName") String categoryName,
			@FormParam("fixedCharge") String fixedCharge, @FormParam("unitCharge") String unitCharge,
			@FormParam("taxCharge") String taxCharge, @FormParam("relief") String relief) {
		String output = CategoryDAO.insertCategory(categoryID, categoryName, fixedCharge, unitCharge, taxCharge,
				relief);
		return output;
	}

	// Update Function
	@PUT
	@Path("category")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String categoryData) {

		// Convert the input string to a JSON object
		JsonObject jasonObject = new JsonParser().parse(categoryData).getAsJsonObject();

		// Read the values from the JSON object
		String categoryID = jasonObject.get("categoryID").getAsString();
		String categoryName = jasonObject.get("categoryName").getAsString();
		String fixedCharge = jasonObject.get("fixedCharge").getAsString();
		String unitCharge = jasonObject.get("unitCharge").getAsString();
		String taxCharge = jasonObject.get("taxCharge").getAsString();
		String relief = jasonObject.get("relief").getAsString();

		String output = CategoryDAO.updateCategory(categoryID, categoryName, fixedCharge, unitCharge, taxCharge,
				relief);

		return output;
	}

	// Delete Function
	@DELETE
	@Path("category")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String categoryData) {
		
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(categoryData, "", Parser.xmlParser());
		
		// Read the value from the element <itemID>
		String categoryID = doc.select("categoryID").text();
		String output = CategoryDAO.deleteCategory(categoryID);
		return output;
	}

}
