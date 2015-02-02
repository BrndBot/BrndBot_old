/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.db.DbConnection;
import com.brndbot.db.UserLogo;
import com.brndbot.system.SessionUtils;
import com.brndbot.system.Utils;

public class RemoveLogoServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	final static Logger logger = LoggerFactory.getLogger(RemoveLogoServlet.class);
	
	public RemoveLogoServlet ()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		logger.debug("--------Entering RemoveLogoServlet----------");

		// Get rid of previous values
		DbConnection con = DbConnection.GetDb();

		HttpSession session = request.getSession();
		int user_id = SessionUtils.getIntSession(session, SessionUtils.USER_ID);

		// Check for attachment
		UserLogo existing_logo = UserLogo.getLogoByUserID(user_id, con);
//		System.out.println("Existing logo: " + existing_logo);
		UserLogo user_logo = new UserLogo(user_id);
		user_logo.setUserID(user_id);

		// See if we need to delete the previous entry
		if (existing_logo != null)
		{
			//System.out.println("Deleting old logo");
			UserLogo.deleteLogo(user_id, con);
		}
		con.close();
		return;
	}
}
