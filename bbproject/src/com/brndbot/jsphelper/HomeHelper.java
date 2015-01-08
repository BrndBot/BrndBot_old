/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 *
*/
package com.brndbot.jsphelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.client.ClientInterface;
import com.brndbot.client.ClientInterfaceFactory;
import com.brndbot.db.DbConnection;
import com.brndbot.db.User;
import com.brndbot.db.UserLogo;

public class HomeHelper {

	final static Logger logger = LoggerFactory.getLogger(HomeHelper.class);

	final static int MAX_LOGO_HEIGHT = 130;
	final static int MAX_LOGO_WIDTH = 200;
	
	private int userId;
	private User user;
	private String organization;
	private ClientInterface clientInterface;


	public void setUserId (int id) {
		userId = id;
		DbConnection con = null;
		try {
			con= DbConnection.GetDb();
			user = User.getUserNameAndLogo(id, con);
		} finally {
			if (con != null)
				con.close();
		}
		loadClientInterface ();
	}
	
	/** This seeks out the appropriate ClientInterface for the user.
	 *  TODO make it real, getting the class name from the database.
	 *  For now, uses the dummy client interface
	 */
	private void loadClientInterface () {
		final String className = "com.brndbot.dummyclient.DummyClientInterface";
		try {
			clientInterface = ClientInterfaceFactory.getInterfaceForClass(className);
		} catch (Exception e) {
			logger.error ("FATAL: {}", e.getClass().getName());
		}
		if (clientInterface == null) {
			logger.error ("FATAL: Could not load client interface {}", className);
		}
	}
	
	/** This loads up, if necessary, and returns the list of StyleSets for the
	 *  client. It needs to get into the session somewhere, but probably not here. */
	
	
	/** Use c:out on this at the point where "what do you want to do today" should
	 *  be inserted.
	 */
	public String getRenderDoToday () {
		DoTodayRenderer renderer = new DoTodayRenderer (clientInterface);
		return renderer.getFragment();
	}

	/** Use c:out on this at the point where the buttons for viewing lists of
	 *  promo protos should be inserted.
	 */
	public String getRenderModelListButtons () {
		ModelListButtonRenderer renderer = new ModelListButtonRenderer(clientInterface);
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
	
	public String getBoundLogo () {
		return UserLogo.getBoundLogo (userId, MAX_LOGO_HEIGHT, MAX_LOGO_WIDTH);
	}
	
	public int getUserId () {
		return userId;
	}
}
