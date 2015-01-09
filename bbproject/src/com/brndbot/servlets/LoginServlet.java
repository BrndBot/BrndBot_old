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
import com.brndbot.system.Utils;

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
		String email_address = (String)request.getParameter(LoginCookie.EMAIL_ADDRESS); 
		if (email_address == null || email_address.length() == 0)
		{
			logger.info("Email address missing");
			missing_str = "Email Address is required.";
		}

		// New Password
		String password = (String)request.getParameter(LoginCookie.PASSWORD); 
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
		
		// FIXME Ugly hack***
		Client.reset ();
		
		DbConnection con = DbConnection.GetDb();

		int user_id = User.Login(email_address, password, con);
		if (user_id == 0)
		{
			logger.info("Failed login em: " + email_address /* + ", pw: " + password*/);
			con.close();
			response.sendRedirect("index.jsp");
			return;
		}

		// See if we should save values to cookies
		// INSANELY INSECURE. DISABLE.
//		String cookie_cb = Utils.getStringParameter(request, LoginCookie.USE_COOKIE_CB);
//		LoginCookie cookie = new LoginCookie(request, response);
//		if (cookie_cb.length() > 0)
//		{
//			cookie.saveToCookie(true, email_address, password);
//		}
//		else
//		{
//			cookie.deleteCookie(LoginCookie.USE_COOKIE);
//			cookie.deleteCookie(LoginCookie.EMAIL_ADDRESS);
//			cookie.deleteCookie(LoginCookie.PASSWORD);
//		}

		session.setAttribute(SessionUtils.IS_PRIV, User.isPrivileged(user_id, con));
		con.close();

		// Success!
//		session.setAttribute(ParamConstants.EMPLOYER_TYPE, RecruiterTable.fetchEmployerType(user_id));
		session.setAttribute(SessionUtils.USER_ID, "" + user_id);
		session.removeAttribute(SessionUtils.LOGIN_ERROR);

		response.sendRedirect("home.jsp");
		return;
	}
}
