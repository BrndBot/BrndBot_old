package com.brndbot.jsphelper;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.client.style.StyleSet;

public class ChannelHelper extends Helper {

	final static Logger logger = LoggerFactory.getLogger(ChannelHelper.class);

	private String modelName;
	
	/** Use c:out on this at the point where "what do you want to do today" should
	 *  be inserted.
	 */
	public String getRenderChannelButtons () {
		if (client == null) {
			logger.error ("getRenderChannelButtons: client is null");
			return null;
		}
		ChannelButtonRenderer renderer = new ChannelButtonRenderer (client, modelName);
		return renderer.getFragment();
	}
	
	public void setModelName (String model) {
		modelName = model;
	}
	
	public int getNumberAvailableChannels () {
		if (client == null)
			return 0;
		Map<String, StyleSet> styleSetMap = client.getStyleSets(modelName);
		if (styleSetMap == null)
			return 0;
		return styleSetMap.keySet().size();

	}
}
