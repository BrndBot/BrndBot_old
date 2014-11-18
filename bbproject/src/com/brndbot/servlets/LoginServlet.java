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

import com.brndbot.db.DbConnection;
import com.brndbot.system.LoginCookie;
import com.brndbot.system.SessionUtils;
import com.brndbot.system.Utils;
import com.brndbot.user.User;

public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

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
		System.out.println("----------Entering LoginServlet----------");

		HttpSession session = request.getSession();

		String missing_str = "";

		// Email address
		String email_address = (String)request.getParameter(LoginCookie.EMAIL_ADDRESS); 
		if (email_address == null || email_address.length() == 0)
		{
			System.out.println("Email address missing");
			missing_str = "Email Address is required.";
		}

		// New Password
		String password = (String)request.getParameter(LoginCookie.PASSWORD); 
		if (password == null || password.length() == 0)
		{
			System.out.println("Password missing");
			missing_str += "Password is required.";
		}

		if (missing_str.length() > 0)
		{
			session.setAttribute(LoginCookie.ERROR, missing_str);
			response.sendRedirect("index.jsp");
			return;
		}
		DbConnection con = DbConnection.GetDb();

		int user_id = User.Login(email_address, password, con);
		if (user_id == 0)
		{
			System.out.println("Failed login em: " + email_address /* + ", pw: " + password*/);
			con.close();
			response.sendRedirect("index.jsp");
			return;
		}

		// See if we should save values to cookies
		String cookie_cb = Utils.getStringParameter(request, LoginCookie.USE_COOKIE_CB);
		LoginCookie cookie = new LoginCookie(request, response);
		if (cookie_cb.length() > 0)
		{
			cookie.saveToCookie(true, email_address, password);
		}
		else
		{
			cookie.deleteCookie(LoginCookie.USE_COOKIE);
			cookie.deleteCookie(LoginCookie.EMAIL_ADDRESS);
			cookie.deleteCookie(LoginCookie.PASSWORD);
		}

		session.setAttribute(SessionUtils.IS_PRIV, User.IsPrivileged(user_id, con));
		con.close();

		// Success!
//		session.setAttribute(ParamConstants.EMPLOYER_TYPE, RecruiterTable.fetchEmployerType(user_id));
		session.setAttribute(SessionUtils.USER_ID, "" + user_id);
		session.removeAttribute(LoginCookie.ERROR);

		response.sendRedirect("home.jsp");
		return;
	}
}
