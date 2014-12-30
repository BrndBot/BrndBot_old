package com.brndbot.jsphelper;

import java.io.IOException;
import java.util.List;

import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.client.ClientInterface;
import com.brndbot.client.Model;
import com.brndbot.client.ModelCollection;

/** This renders the buttons that lead to a list of
 *  Promotion Prototypes for each model */
public class ModelListButtonRenderer extends Renderer {

	final static Logger logger = LoggerFactory.getLogger(ModelListButtonRenderer.class);

	
	public ModelListButtonRenderer(ClientInterface ci) {
		super ();
		ModelCollection modelCol = ci.getModels();
		// TODO Auto-generated constructor stub
		try {
			List<Model> models = modelCol.getModels();
			for (Model m : models) {
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
		// TODO we probably want a pluralName field in Model
		outputter.output (topDiv, writer);
	}

}
