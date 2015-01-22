package com.brndbot.jsphelper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.client.ClientInterface;
import com.brndbot.client.Model;
import com.brndbot.client.ModelCollection;
import com.brndbot.promo.Client;
import com.brndbot.system.SystemProp;

/** This renderer populates the "what do you want to
 *  do today?" list with a button for each of the available
 *  models.
 *  
 *  TODO generalize so it can render multiple rows. May require
 *  having it start one div level higher.
 */
public class DoTodayRenderer extends Renderer {

	final static Logger logger = LoggerFactory.getLogger(DoTodayRenderer.class);

	
	public DoTodayRenderer(Client c) {
		super ();
		ModelCollection modelCol = c.getModels ();
		try {
			Map<String, Model> models = modelCol.getAllModels();
			for (Model m : models.values()) {
				int idx = 0;
				renderModel (m, idx);
			}
		}
		catch (Exception e) {
			logger.error ("Exception generating Do Today: {}   {}",
					e.getClass().getName(), e.getMessage());
		}
	}


	
	private void renderModel (Model m, int idx) throws IOException {
		Element badgeDiv = new Element ("div");
		badgeDiv.setAttribute ("id", "Badge" + idx);
		badgeDiv.setAttribute ("class", "homeBadge");
		Element imgDiv = new Element ("div");
		badgeDiv.addContent (imgDiv);
		Element badgeGraphic = new Element ("img");
		badgeGraphic.setAttribute ("src", "images/" + m.getButtonImage());
		badgeGraphic.setAttribute ("class", "homeBadgeButton");
		badgeGraphic.setAttribute ("data-model", m.getName());
//		badgeGraphic.setAttribute ("onclick", "badgeClick(this);");
		imgDiv.addContent (badgeGraphic);
		Element promptDiv = new Element ("div");
		badgeDiv.addContent (promptDiv);
		promptDiv.addContent ("Promote " + m.getName());
		outputter.output (badgeDiv, writer);
	}
}
