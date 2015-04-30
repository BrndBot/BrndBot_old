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
import com.brndbot.db.User;
import com.brndbot.promo.Client;
import com.brndbot.system.LoginCookie;
import com.brndbot.system.SessionUtils;

public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	final static Logger logger = LoggerFactory.getLogger(LoginServlet.class);
	
	public LoginServlet ()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		logger.debug("----------Entering LoginServlet----------");

		HttpSession session = request.getSession();

		String missing_str = "";

		// Email address
		String email_address = (String)request.getParameter("brndbotemailcookie"); 
		if (email_address == null || email_address.length() == 0)
		{
			logger.info("Email address missing");
			missing_str = "Email Address is required.";
		}

		// New Password
		String password = (String)request.getParameter("brndbotpassword"); 
		if (password == null || password.length() == 0)
		{
			logger.info("Password missing");
			missing_str += "Password is required.";
		}

		if (missing_str.length() > 0)
		{
			session.setAttribute(SessionUtils.LOGIN_ERROR, missing_str);
			response.sendRedirect("index.jsp");
			return;
		}
		
		// Remove old authentication
		session.removeAttribute (SessionUtils.CLIENT);
		session.removeAttribute (SessionUtils.USER_ID);
		
		DbConnection con = null;

		int user_id = -1;
		User user = null;
		try {
			con = DbConnection.getDb();
			user_id = User.Login(email_address, password, con);
			if (user_id == 0)
			{
				logger.info("Failed login em: " + email_address /* + ", pw: " + password*/);
				response.sendRedirect("index.jsp");
				return;
			}
	
			user = new User (user_id);
			user.loadClientInfo(con);
			session.setAttribute(SessionUtils.IS_PRIV, User.isPrivileged(user_id, con));
		}
		catch (Exception e) {
			logger.error ("Exception in LoginServlet: {}, {}", 
						e.getClass().getName(), e.getMessage());
			response.setStatus (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
		finally {
			if (con != null)
				con.close();
		}

		// Success!
		session.setAttribute(SessionUtils.USER_ID, "" + user_id);
		session.removeAttribute(SessionUtils.LOGIN_ERROR);
		Client client = Client.getClient (session);		// Load up the client
		client.updateUserInfo (user_id);
		SessionUtils.saveSessionData (request, SessionUtils.CLIENT, client.getCacheKey());
		SessionUtils.saveSessionData (request, SessionUtils.ORGANIZATION, client.getOrganizationName());

		response.sendRedirect("home.jsp");
		return;
	}
}
