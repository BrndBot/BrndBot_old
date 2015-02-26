package com.brndbot.jsphelper;

import java.io.IOException;
//import java.util.List;
import java.util.Map;

import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.brndbot.client.ClientInterface;
import com.brndbot.client.Model;
import com.brndbot.client.ModelCollection;
import com.brndbot.promo.Client;

/** This renders the buttons that lead to a list of
 *  Promotion Prototypes for each model for home.jsp
 *  ("What do you want to do today?") */
public class ModelListButtonRenderer extends Renderer {

	final static Logger logger = LoggerFactory.getLogger(ModelListButtonRenderer.class);

	
	public ModelListButtonRenderer(Client c) {
		super ();
		logger.debug ("ModelListButtonRenderer constructor");
		if (c == null)
			logger.error ("Client is null");
		ModelCollection modelCol = c.getModels();
		try {
			Map<String,Model> models = modelCol.getAllModels();
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
		Element topDiv = new Element ("div");
		topDiv.setAttribute ("class", "unit eachButton");
		Element button = new Element ("button");
		button.setAttribute ("id", "view" + m.getName());
		button.setAttribute ("class", "greenNoHoverButton");
		topDiv.addContent (button);
		button.addContent (m.getName() + "s");
		outputter.output (topDiv, writer);
	}

}
