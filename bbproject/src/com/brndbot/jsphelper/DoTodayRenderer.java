package com.brndbot.jsphelper;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.client.Model;
import com.brndbot.client.ModelCollection;
import com.brndbot.promo.Client;

/** This renderer populates the "what do you want to
 *  do today?" list in home.jsp with a button for each of the available
 *  categories.
 *  
 *  Below each button is a sub-table with the models for that category.
 *  It's initially hidden, and will be displayed when the category button
 *  is clicked.
 *  
 */public class DoTodayRenderer extends Renderer {

	final static Logger logger = LoggerFactory.getLogger(DoTodayRenderer.class);
	
	// Half the difference between the width of the category div and the model table div.
	// This is the offset for the category div left side.
	final static int WIDTHDIFF = 100;

	public DoTodayRenderer(Client c) {
		/* We want to render first the categories, then the models under the selected
		 * category.
		 * 
		 * This will be a two rows of positioned divs, because we need to do positioning
		 * tricks.
		 */
		super ();
		try {
			ModelCollection modelCol = c.getModels ();
			Element categoriesDiv = new Element ("div");
			categoriesDiv.setAttribute ("class", "categoriesDiv");
			Element categoriesTable = new Element ("table");
			categoriesDiv.addContent (categoriesTable);
			Element catgegoriesRowElem = new Element ("tr");
			categoriesTable.addContent(catgegoriesRowElem);
			List<String> categories = modelCol.getCategories ();
			int idx = 0;
			for (String cat : categories) {
				catgegoriesRowElem.addContent (renderCategory (cat, idx++));
			}
			
			Element modelItemsDiv = new Element ("div");
			idx = 0;
			for (String cat : categories) {
				modelItemsDiv.addContent (renderModels (modelCol, cat, idx++));
			}
			
			outputter.output (categoriesDiv, writer);
			outputter.output (modelItemsDiv, writer);
		}
		catch (Exception e) {
			logger.error ("Exception generating Do Today: {}   {}",
					e.getClass().getName(), e.getMessage());
		}
	}

	/* This generates the category button and caption for one category
	 * and returns a TD Element for it. */
	private Element renderCategory (String cat, int idx)  {
		logger.debug ("renderCategory, cat = {} idx = {}", cat, idx);
		Element catTD = new Element ("td");
		Element catDiv = new Element ("div");
		catDiv.setAttribute ("class", "categoryDiv");
		catDiv.setAttribute ("style", "left:" + (WIDTHDIFF + idx * 156) + "px");

		// Inside the TD, put a table with one row for the button 
		// and one for the name.
		Element catTable = new Element ("table");
		Element buttonTR = new Element ("tr");
		Element buttonTD = new Element ("td");
		Element catButton = new Element ("button");
		catButton.setAttribute ("id", "Cat" + idx);
		catButton.setAttribute ("class", "categoryButton");
		catButton.setAttribute ("data-category", cat);
		catButton.setAttribute ("onmouseover", "homejs.showHoverImage(this);");
		catButton.setAttribute ("onmouseout", "homejs.showNormalImage(this);");
		Element buttonImage = new Element ("img");
		buttonImage.setAttribute ("alt", "Promote " + cat);
		String encodedCat;
		try {
			encodedCat = URLEncoder.encode (cat, "UTF-8");
		}
		catch (Exception e) {
			logger.error ("URL encoding error");
			return null;
		}
		buttonImage.setAttribute ("src", "ModelButtonServlet?category=" + encodedCat);
		buttonImage.setAttribute ("data-normalsrc", "ModelButtonServlet?category=" + encodedCat);
		buttonImage.setAttribute ("data-hoversrc", "ModelButtonServlet?hover=y&category=" + encodedCat);
		catButton.addContent (buttonImage);
		buttonTD.addContent (catButton);
		buttonTR.addContent (buttonTD);
		catTable.addContent (buttonTR);
		
		// Now a row for the name
		Element nameTR = new Element ("tr");
		Element nameTD = new Element ("td");
		nameTD.setAttribute ("class", "categoryName");
		nameTD.addContent (cat);
		nameTR.addContent (nameTD);
		catTable.addContent (nameTR);
		
		catDiv.addContent (catTable);
		catTD.addContent (catDiv);
		return catTD;
	}
	
	/* This generates the model buttons for one category and returns a div
	 * element containing a table for it. Each div is centered under the tab
	 * column, but is wider than it, so it can't be a TD. */
	private Element renderModels (ModelCollection modelCol, String cat, int idx) {
		logger.debug ("renderModels, cat = {} idx = {}", cat, idx);
		Element modelsDiv = new Element ("div");
		modelsDiv.setAttribute ("class", "modelsDiv");
		modelsDiv.setAttribute ("style", "left:" + (idx * 156) + "px");
		
		Element modelsTable = new Element ("table");
		modelsTable.setAttribute ("data-category", cat);
		modelsTable.setAttribute ("class", "modelsTable");
		modelsDiv.addContent (modelsTable);
		Map<String, Model> catModels = modelCol.getModelsForCategory(cat);
		
		// Add the descriptive text
		Element headerRow = new Element ("tr");
		Element headerCell = new Element ("td");
		modelsTable.addContent (headerRow);
		headerRow.addContent (headerCell);
		headerCell.setAttribute ("class", "modelHeader");
		headerCell.addContent ("Select promotion");
		headerCell.setAttribute ("class", "selectPromoText");
		
		// Add the model buttons
		for (String modelName : catModels.keySet()) {
			Element modelRow = new Element ("tr");
			Element modelCell = new Element ("td");
			modelsTable.addContent(modelRow);
			modelRow.addContent(modelCell);
			Element modelItem = new Element ("span");
			modelItem.setAttribute ("class", "modelButton");
			modelItem.setAttribute ("data-model", modelName);
			modelItem.addContent (modelName);
			logger.debug ("Added model for {}", modelName);
			modelCell.addContent (modelItem);
		}
		logger.debug ("returning from renderModels");
		return modelsDiv;
	}

}
