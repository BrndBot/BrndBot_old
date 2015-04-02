/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.system;

//import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionUtils
{
	

	final private static Logger logger = LoggerFactory.getLogger(SessionUtils.class);
			
	// Session actions
	public static String SET = "set";
	public static String CLEAR = "clear";
	public static String GET = "get";
	public static String BLOCKS = "blocks";
	public static String IMAGE = "image";
	public static String TEST = "test";

	// Session data keys
	public static String CONTENT_KEY = "brndbotcontent";
	public static String CHANNEL_KEY = "brndbotchannel";
	public static String DATABASE_ID_KEY = "brndbotdbid"; 
	public static String IMAGE_ID_KEY = "brndbotimageid"; 
	public static String FUSED_IMAGE_ID_KEY = "brndbotfusedid";
	public static String LOGIN_ERROR = "brndboterror";
	public static String CLIENT = "brndbotclient";
	
	// Simple system-wide constants
	static final public String USER_ID = "brndbotuser_id";
	static final public String IS_PRIV = "brndbotis_priv";
	

	public static void saveSessionData 
				(HttpServletRequest req, String name, Object data) {
		HttpSession session = req.getSession ();
		session.setAttribute (name, data);
	}
	
	public static Object getSessionData
			(HttpServletRequest req, String name) {
		if (SessionUtils.CLIENT.equals (name)) {
			logger.debug ("Getting client object");
		}
		HttpSession session = req.getSession(false);
		if (session == null) {
			logger.debug ("No session");
			return null;
		}
		if (session.getAttribute (name) != null)
			logger.debug ("Attribute {} is not null", name);
		else
			logger.debug ("Addtribute {} is null", name);
		return session.getAttribute (name);
	}
	
	public static int getUserId (HttpSession session) {
		return getIntSession(session, SessionUtils.USER_ID);
	}

	/** Return a session attribute which was saved as a boolean.
	 *  Returns false if it's missing. */
	static public Boolean getBooleanSession(
			HttpSession session,
			String attribute) throws NumberFormatException
	{
		Boolean temp = (Boolean)session.getAttribute(attribute);
		if (temp == null)
		{
			temp = new Boolean(false);
		}
		return temp;
	}
	
	/** Return a session attribute as a String. Never returns null.
	 *  Returns the empty string if it's missing. */
	static public String getStringSession(
			HttpSession session,
			String parameter)
	{
		String str = Utils.SafeString((String)session.getAttribute(parameter)); 
		return str;
	}

	/** Returns a session attribute as an integer.
	 *  Returns 0 if it's unparsable or missing. */
	static public int getIntSession(
			HttpSession session,
			String attribute) throws NumberFormatException
	{
		String temp = Utils.SafeString((String)session.getAttribute(attribute));
		int ret = 0;
		try
		{
			ret = Integer.parseInt(temp);
		}
		catch (NumberFormatException e)
		{ }

		return ret;
	}


}
