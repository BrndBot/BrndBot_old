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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.db.DbConnection;
import com.brndbot.system.Utils;
import com.brndbot.user.User;

public class EmailExistServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	final static Logger logger = LoggerFactory.getLogger(EmailExistServlet.class);
	
	public EmailExistServlet ()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		logger.info("Entering EmailExistServlet");

		DbConnection con = DbConnection.GetDb();

		// Gather data
		String userEmail = Utils.getStringParameter(request, "hiddenEmail").toLowerCase();
//		String userPassword = Utils.getStringParameter(request, "hiddenPassword");

		// Add record to database
		if (User.doesEmailExist(userEmail, con))
		{
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
		else
		{
			response.setStatus(HttpServletResponse.SC_OK);
		}
		con.close();

		return;
	}
}
