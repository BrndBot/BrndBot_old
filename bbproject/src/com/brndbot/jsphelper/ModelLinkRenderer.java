package com.brndbot.jsphelper;

import java.io.IOException;
import java.util.Map;

import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.client.ClientInterface;
import com.brndbot.client.Model;
import com.brndbot.client.ModelCollection;

/** This class inserts divs for each model in the right sidebar
 *  of the editor */
public class ModelLinkRenderer extends Renderer {

	final static Logger logger = LoggerFactory.getLogger(ModelLinkRenderer.class);
	
	public ModelLinkRenderer(ClientInterface ci) {
		super ();
		logger.debug ("ModelLinkRenderer constructor");
		ModelCollection modelCol = ci.getModels();
		try {
			Map<String,Model> models = modelCol.getAllModels();
			logger.debug ("Starting model loop");
			for (Model m : models.values()) {
				renderModel (m);
			}
		}
		catch (Exception e) {
			logger.error ("Exception generating Editor sidebar: {}   {}",
					e.getClass().getName(), e.getMessage());
		}
	}

	private void renderModel (Model m) throws IOException {
		logger.debug ("Rendering model named {}", m.getName());
		Element topDiv = new Element ("div");
		topDiv.setAttribute ("class", "formSpacerLite addLink");
		topDiv.setAttribute ("id", "newlink_" + m.getName());
		topDiv.addContent (m.getDescription());
		outputter.output (topDiv, writer);
	}
}
