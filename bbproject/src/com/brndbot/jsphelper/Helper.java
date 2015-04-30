/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2015
 *
*/
package com.brndbot.jsphelper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.db.DbConnection;
import com.brndbot.db.User;
import com.brndbot.db.UserLogo;
import com.brndbot.promo.Client;
import com.brndbot.promo.ClientCache;

/** Base class for helper classes for a JSP 
 */
public abstract class Helper {

	final static Logger logger = LoggerFactory.getLogger(Helper.class);

	final static int MAX_LOGO_HEIGHT = 130;
	final static int MAX_LOGO_WIDTH = 200;

	int userId;
	User user;
	Client client;

	/* The client key must be set to retrieve the session's client object */
	public void setClientKey (int key) {
		ClientCache cc = Client.getClientCache();
		client = cc.getClient (key);
		if (client == null) {
			logger.error ("No Client found for key {}", key);
		}
	}
	
	public void setUserId (int id) {
		logger.debug ("setUserId: {}", id);
		userId = id;
		DbConnection con = null;
		try {
			con= DbConnection.getDb();
			user = User.getUserNameAndLogo(id, con);
		} catch (Exception e) {
			logger.error ("Error in setting user ID: {}", e.getClass().getName());
		} finally {
			if (con != null)
				con.close();
		}
	}
	
	public String getBoundLogo () {
		return UserLogo.getBoundLogo (userId, MAX_LOGO_HEIGHT, MAX_LOGO_WIDTH);
	}

	public int getUserId () {
		return userId;
	}

}
