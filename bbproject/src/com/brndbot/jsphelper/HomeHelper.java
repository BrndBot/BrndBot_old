/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 *
*/
package com.brndbot.jsphelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.brndbot.db.DbConnection;
//import com.brndbot.db.User;
//import com.brndbot.db.UserLogo;
//import com.brndbot.promo.Client;

public class HomeHelper extends Helper {

	final static Logger logger = LoggerFactory.getLogger(HomeHelper.class);

	private String organization;

	
	/** This loads up, if necessary, and returns the list of StyleSets for the
	 *  client. It needs to get into the session somewhere, but probably not here. */
	
	
	/** Use c:out on this at the point where "what do you want to do today" should
	 *  be inserted.
	 */
	public String getRenderDoToday () {
		if (client == null) {
			logger.error ("getRenderDoToday: client is null");
			return null;
		}
		DoTodayRenderer renderer = new DoTodayRenderer (client);
		return renderer.getFragment();
	}

	/** Use c:out on this at the point where the buttons for viewing lists of
	 *  promo protos should be inserted.
	 */
	public String getRenderModelListButtons () {
		ModelListButtonRenderer renderer = new ModelListButtonRenderer(client);
		return renderer.getFragment ();
	}
	
	public String getOrganization () {
		return organization;
	}
	
	public void setOrganization (String org) {
		organization = org;
	}

	public String getLogoName () {
		if (user == null) {
			return "";
		}
		logger.debug ("Logo name is {}", user.getLogoName());
		return user.getLogoName ();
	}
	
}
