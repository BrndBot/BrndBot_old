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
 *  do today?" list with a button for each of the available
 *  categories and shows a row of models for a clicked category.
 *  
 */
public class DoTodayRenderer extends Renderer {

	final static Logger logger = LoggerFactory.getLogger(DoTodayRenderer.class);

	/* We want to render first the categories, then the models under the selected
	 * category.
	 * 
	 * The bottom row will change, so we can't
	 * do it in Java. Has to be in JavaScript. So just render the top row 
	 * (the categories) here.
	 */
	public DoTodayRenderer(Client c) {
		super ();
		ModelCollection modelCol = c.getModels ();
		try {
			// This really shouldn't work from a layout standpoint. 
			// But see what it does.
			List<String> categories = modelCol.getCategories ();
			int idx = 0;
			for (String cat : categories) {
				renderCategory (cat, idx++);
			}
			
			// Now create templates for each row. These include the tr tag,
			// since it's slightly more convenient to copy a single element.
			for (String cat : categories) {
				Element catTemplate = new Element ("template");
				catTemplate.setAttribute("data-category", cat);
				Element modelRow = new Element ("tr");
				Map<String, Model> catModels = modelCol.getModelsForCategory(cat);
				for (String modelName : catModels.keySet()) {
					Element modelCell = new Element ("td");
					modelRow.addContent(modelCell);
					Element modelButton = new Element ("button");
					modelButton.setAttribute ("style", "background-color:#FFBBCC");
					modelButton.setAttribute ("class", "modelButton");
					modelButton.setAttribute ("data-model", modelName);
					modelButton.addContent (modelName);
					modelCell.addContent (modelButton);
				}
				catTemplate.addContent(modelRow);
				outputter.output (catTemplate, writer);
			}
		}
		catch (Exception e) {
			logger.error ("Exception generating Do Today: {}   {}",
					e.getClass().getName(), e.getMessage());
		}
	}

	private void renderCategory (String cat, int idx) throws IOException {
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
		//catButton.setAttribute ("style", "background-color:#BBBBFF");
		//catButton.addContent ("Promote " + cat);
		Element buttonImage = new Element ("img");
		buttonImage.setAttribute ("alt", "Promote " + cat);
		buttonImage.setAttribute ("src", "ModelButtonServlet?category=" + cat);
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
		outputter.output (catCell, writer);
	}

	// This will have to move into JavaScript.
//	private void renderModel (Model m, int idx) throws IOException {
//		Element badgeDiv = new Element ("div");
//		badgeDiv.setAttribute ("id", "Badge" + idx);
//		badgeDiv.setAttribute ("class", "homeBadge");
//		Element imgDiv = new Element ("div");
//		badgeDiv.addContent (imgDiv);
//		Element badgeGraphic = new Element ("img");
//		badgeGraphic.setAttribute ("src", "images/" + m.getButtonImage());
//		badgeGraphic.setAttribute ("class", "homeBadgeButton");
//		badgeGraphic.setAttribute ("data-model", m.getName());
//		imgDiv.addContent (badgeGraphic);
//		Element promptDiv = new Element ("div");
//		badgeDiv.addContent (promptDiv);
//		promptDiv.addContent ("Promote " + m.getName());
//		outputter.output (badgeDiv, writer);
//	}
}
