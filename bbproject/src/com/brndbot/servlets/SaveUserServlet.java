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
import com.brndbot.system.SessionUtils;
import com.brndbot.system.Utils;
import com.brndbot.util.PWHash;

/**
 *  TODO This seems like a security hole. What's to stop a visitor from creating a 
 *  user here? At least we should re-check the authorization code.
 */
public class SaveUserServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	final static Logger logger = LoggerFactory.getLogger(SaveUserServlet.class);
	
	public SaveUserServlet ()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		logger.debug("--------Entering SaveUserServlet----------");

		DbConnection con = DbConnection.GetDb();

		// Gather data
		String userEmail = Utils.getStringParameter(request, "hiddenEmail").toLowerCase();
		String userPassword = Utils.getStringParameter(request, "hiddenPassword");
		String hashedPassword = PWHash.getHash(userPassword);

//		String userConfirmPassword = Utils.getStringParameter(request, "userConfirmPassword");
		String companyName = Utils.getStringParameter(request, "hiddenCompany");
//		String companyFacilities = Utils.getStringParameter(request, "hiddenFacilities");
		String companyAddress = Utils.getStringParameter(request, "hiddenAddress");
		String companyURL = Utils.getStringParameter(request, "hiddenUrl").toLowerCase();
		String facebookURL = Utils.getStringParameter(request, "hiddenFacebookUrl").toLowerCase();
		String twitterHandle = Utils.getStringParameter(request, "hiddenTwitterHandle");
//		String linkedIn = Utils.getStringParameter(request, "hiddenLinkedIn");
//		String youTube = Utils.getStringParameter(request, "hiddenYouTube");
//		String instagram = Utils.getStringParameter(request, "hiddenInstagram");
		String orgId = Utils.getStringParameter(request,  "hiddenOrgId");

		HttpSession session = request.getSession();

		// Add record to database
		User user = new User();
		user.setEmail(userEmail);
		user.setHashedPassword(hashedPassword);
		user.setCompanyName(companyName);
		user.setCompanyAddress(companyAddress);
		user.setCompanyURL(companyURL);
		user.setFacebook(facebookURL);
		user.setTwitter(twitterHandle);
		user.setOrganizationID(Integer.parseInt(orgId));
		user.saveUser (con);
		int user_id = user.getUserID();
		

		logger.debug("Saved user_id: " + user_id);
		session.setAttribute(SessionUtils.USER_ID, "" + user_id);
		Client client = Client.getClient (session);		// Load up the client
		try {
			logger.debug ("Created Client with interface {}", client.getClientInterface().getClass().getName());
		} catch (Exception e) {
			logger.error ("Problem creating Client: {}", e.getClass().getName());
			e.printStackTrace();
		}
		response.setStatus(HttpServletResponse.SC_OK);
		return;
	}
}
