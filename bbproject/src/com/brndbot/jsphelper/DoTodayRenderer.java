package com.brndbot.jsphelper;

import java.io.IOException;
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

	public DoTodayRenderer(Client c) {
		/* We want to render first the categories, then the models under the selected
		 * category.
		 * 
		 * This will be a table with two rows. The top row has the category buttons.
		 * The bottom row has a table inside each TD, with the models arranged vertically.
		 */
		super ();
		try {
			ModelCollection modelCol = c.getModels ();
			Element tableElem = new Element ("table");
			Element firstRowElem = new Element ("tr");
			tableElem.addContent(firstRowElem);
			List<String> categories = modelCol.getCategories ();
			int idx = 0;
			for (String cat : categories) {
				firstRowElem.addContent (renderCategory (cat, idx++));
			}
			Element secondRowElem = new Element ("tr");
			tableElem.addContent (secondRowElem);
			idx = 0;
			for (String cat : categories) {
				secondRowElem.addContent (renderModels (modelCol, cat, idx++));
			}
			
			outputter.output (tableElem, writer);
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
		Element catCell = new Element ("td");

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
		//catButton.setAttribute ("style", "background-color:#BBBBFF");
		//catButton.addContent ("Promote " + cat);
		Element buttonImage = new Element ("img");
		buttonImage.setAttribute ("alt", "Promote " + cat);
		buttonImage.setAttribute ("src", "ModelButtonServlet?category=" + cat);
		buttonImage.setAttribute ("data-normalsrc", "ModelButtonServlet?category=" + cat);
		buttonImage.setAttribute ("data-hoversrc", "ModelButtonServlet?hover=y&category=" + cat);
		catButton.addContent (buttonImage);
		buttonTD.addContent (catButton);
		buttonTR.addContent (buttonTD);
		catTable.addContent (buttonTR);
		
		// Now a row for the name
		Element nameTR = new Element ("tr");
		Element nameTD = new Element ("td");
		// TODO style nameTD
		nameTD.addContent (cat);
		nameTR.addContent (nameTD);
		catTable.addContent (nameTR);
		
		catCell.addContent (catTable);
		return catCell;
	}
	
	/* This generates the model buttons for one category and returns a TD
	 * element containing a table for it. */
	private Element renderModels (ModelCollection modelCol, String cat, int idx) {
		logger.debug ("renderModels, cat = {} idx = {}", cat, idx);
		Element modelsCell = new Element ("td");
		
		Element modelsTable = new Element ("table");
		modelsTable.setAttribute ("data-category", cat);
		modelsTable.setAttribute ("class", "modelsTable");
		modelsCell.addContent (modelsTable);
		Map<String, Model> catModels = modelCol.getModelsForCategory(cat);
		for (String modelName : catModels.keySet()) {
			Element modelRow = new Element ("tr");
			Element modelCell = new Element ("td");
			modelsTable.addContent(modelRow);
			modelRow.addContent(modelCell);
			Element modelButton = new Element ("button");
			modelButton.setAttribute ("style", "background-color:#FFBBCC");
			modelButton.setAttribute ("class", "modelButton");
			modelButton.setAttribute ("data-model", modelName);
			modelButton.addContent (modelName);
			logger.debug ("Added model for {}", modelName);
			modelCell.addContent (modelButton);
		}
		logger.debug ("returning from renderModels");
		return modelsCell;
	}

}
