/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.db.DbConnection;
import com.brndbot.db.Organization;
import com.brndbot.db.User;
import com.brndbot.system.Utils;

/** This servlet does validation of the signup form.
 *  In spite of its name, it does more than checking if the email address is
 *  already in the database.
 */
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

	/** Currently this returns no data, just success or failure. We need to change it
	 *  to return some simple JSON to indicate multiple failure modes.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		logger.info("Entering EmailExistServlet");

		DbConnection con = null;

		try {
			con = DbConnection.getDb();
			// Gather data
			String userEmail = Utils.getStringParameter(request, "hiddenEmail").toLowerCase();
			String authCode = Utils.getStringParameter(request, "hiddenAuth").toLowerCase();
			logger.debug("authCode = {}", authCode);
	//		String userPassword = Utils.getStringParameter(request, "hiddenPassword");
	
			JSONObject json_obj = new JSONObject();
			String status = "ok";
			int orgID = 0;
			if (User.doesEmailExist(userEmail, con))
			{
				status = "email_exists";
			}
			Organization org = Organization.getByAuthCode(authCode);
			if (org == null) {
				status = "no_org";
			} else {
				orgID = org.getOrganizationID();
			}
			try {
				logger.debug("Putting status and org into JSON");
				// put status into JSON
				json_obj.put("status",status);
				
				// put organization ID into JSON
				json_obj.put("orgid",orgID);
			} catch (JSONException e) {
				logger.error ("JSON error: {}", e.getClass().getName());
			}
	
			logger.debug("Writing JSON to output");
	        response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			String s = json_obj.toString();
			out.println(s);
			out.flush();
			response.setStatus(HttpServletResponse.SC_OK);
		}
		catch (Exception e) {
			logger.error ("Error in EmailExistServlet: {}, {}",
						e.getClass().getName(), e.getMessage());
		}
		finally {
			con.close();
		}

		return;
	}
}
