package com.brndbot.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.db.Organization;
import com.brndbot.db.Personality;
import com.brndbot.promo.Client;
import com.brndbot.system.SessionUtils;

/** This servlet provides information on available brand personalities information for
 *  the specified organization. The user has just signed up, so is there a Client
 *  object available at this point?
 */
public class PersonalityServlet extends HttpServlet {

	final static Logger logger = LoggerFactory.getLogger(PersonalityServlet.class);


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}
	
	/* This servlet can return a list of all personalities or set one in the client, depending
	 * on the "action" URL parameter. */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug ("Entering PersonalityServlet");
		String action = request.getParameter ("action");
		if ("get".equals (action)) {
			doGetPersonalities (request, response);
		}
		else if ("select".equals (action)) {
			doSelectPersonality (request, response);
		}
		else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
	
	/** A request with the parameter action=get
	 *  will return JSON data with info on
	 *  the available brand personalities. I'm assuming the client is available 
	 *  at this point.
	 */
	private void doSelectPersonality(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer clientKey = (Integer) SessionUtils.getSessionData(request, SessionUtils.CLIENT);
		Client client = Client.getByKey(clientKey);
		String orgName = client.getOrganizationName();
		Organization org = Organization.getByName(orgName);
		List<Personality> personalities = Personality.getPersonalitiesByOrgId(org.getId());
		
		/* OK,now for building the JSON...  */
		try {
			JSONArray jsonPers = new JSONArray ();
			for (Personality per : personalities) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put ("id", per.getId());
				jsonObj.put ("name", per.getName());
				jsonObj.put ("image", "later");
				jsonPers.put(jsonObj);
			}
			String jsonStr = jsonPers.toString();
			if (jsonStr.length() > 0)
			{
		        response.setContentType("application/json; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println(jsonStr);
				out.flush();
				response.setStatus(HttpServletResponse.SC_OK);
			}
			else {
				logger.error("Error creating JSON from personalities ");
				response.setStatus (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}		
		}
		catch (JSONException e) {
			logger.debug ("Exception in creating JSON: {}", e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
	
	/** A request with the parameter action=get
	 *  will return JSON data with info on
	 *  the available brand personalities. I'm assuming the client is available 
	 *  at this point.
	 */
	private void doGetPersonalities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer clientKey = (Integer) SessionUtils.getSessionData(request, SessionUtils.CLIENT);
		Client client = Client.getByKey(clientKey);
		String idStr = request.getParameter ("id");
		int id = -1;
		if (idStr != null) {
			try {
				id = Integer.parseInt(idStr);
			}
			catch (Exception e) {}
		}
		if (id <= 0) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		client.setBrandPersonality (id);
		
	}
}
