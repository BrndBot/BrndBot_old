package com.brndbot.jsphelper;


import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.block.ChannelEnum;
import com.brndbot.client.style.StyleSet;
import com.brndbot.promo.Client;

/** This class creates the HTML fragment for the channel buttons. */
public class ChannelButtonRenderer extends Renderer {

	final static Logger logger = LoggerFactory.getLogger(ChannelButtonRenderer.class);
	
	boolean hasTwitter = false;
	boolean hasFacebook = false;

	public ChannelButtonRenderer(Client client, String modelName) {
		/* We want to render first the categories, then the models under the selected
		 * category.
		 * 
		 * This will be a table with two rows. The top row has the category buttons.
		 * The bottom row has a table inside each TD, with the models arranged vertically.
		 */
		super ();
		
		Map<String, StyleSet> styleSetMap = null;
		try {
			styleSetMap = client.getStyleSets(modelName);
			for (StyleSet ss : styleSetMap.values()) {
				if ("Facebook".equals (ss.getChannel())) {
					hasFacebook = true;
				}
				if ("Twitter".equals (ss.getChannel())) {
					hasTwitter = true;
				}
			}
		} catch (Exception e) {
			logger.error ("Exception getting style sets: {}", e.getClass().getName());
			e.printStackTrace();
			return;
		}

		try {

			
			/* First version: Just outputs a Twitter button */
			Element tableElem = new Element ("table");
			Element rowElem = new Element ("tr");
			tableElem.addContent (rowElem);
			int idx = 0;
			
			if (hasTwitter)
				rowElem.addContent (renderChannel ("Twitter", idx++));
			if (hasFacebook)
				rowElem.addContent (renderChannel ("Facebook", idx++));
			
			outputter.output (tableElem, writer);
		}
		catch (Exception e) {
			logger.error ("Exception generating channels: {}   {}",
					e.getClass().getName(), e.getMessage());
		}
	}
	
	/* This generates the channel button and caption for one channel
	 * and returns a TD Element for it. */
	private Element renderChannel (String chan, int idx)  {
		logger.debug ("renderChannel, cat = {} idx = {}", chan, idx);
		Element chanCell = new Element ("td");

		// Inside the TD, put a table with one row for the button 
		// and one for the name.
		Element chanTable = new Element ("table");
		Element buttonTR = new Element ("tr");
		Element buttonTD = new Element ("td");
		Element chanButton = generateButton (ChannelEnum.CH_TWITTER, idx);
		buttonTD.addContent (chanButton);
		buttonTR.addContent (buttonTD);
		chanTable.addContent (buttonTR);
		
		// Now a row for the name
		Element nameTR = new Element ("tr");
		Element nameTD = new Element ("td");

		nameTD.addContent (chan);
		nameTR.addContent (nameTD);
		chanTable.addContent (nameTR);
		
		chanCell.addContent (chanTable);
		return chanCell;
	}
	
	private Element generateButton (ChannelEnum chan, int idx) {
		Element chanButton = new Element ("button");
		chanButton.setAttribute ("id", "Cat" + idx);
		chanButton.setAttribute ("class", "channelButton");
		String channelName = Integer.toString(chan.getValue());
		chanButton.setAttribute ("data-channel", channelName);
		chanButton.setAttribute ("onmouseover", "channeljs.showHoverImage(this);");
		chanButton.setAttribute ("onmouseout", "channeljs.showNormalImage(this);");
		Element buttonImage = new Element ("img");
		buttonImage.setAttribute ("alt", "Select " + channelName);
		buttonImage.setAttribute ("data-normalsrc", normalImagePath(chan));
		buttonImage.setAttribute ("data-hoversrc", hoverImagePath(chan));
		buttonImage.setAttribute ("src", normalImagePath(chan));		// TODO temporary fixed button
		chanButton.addContent (buttonImage);
		return chanButton;
		
	}
	
	private String normalImagePath (ChannelEnum ch) {
		String fileName;
		switch (ch) {
		case CH_TWITTER:
			fileName = "twitter.png";
			break;
		case CH_FACEBOOK:
			fileName = "facebook.png";
			break;
		case CH_EMAIL:
			fileName = "email.png";
			break;
		default:
			fileName = "notFound.png";		// should never happen
			break;
		}
		return "images/home/" + fileName;
	}

	/* TODO For the moment, normal and hover return the same path */
	private String hoverImagePath (ChannelEnum ch) {
		String fileName = "notFound.png";
		switch (ch) {
		case CH_TWITTER:
			fileName = "twitter.png";
			break;
		case CH_FACEBOOK:
			fileName = "facebook.png";
			break;
		case CH_EMAIL:
			fileName = "email.png";
			break;
		}
		return "images/home/" + fileName;
	}

}
