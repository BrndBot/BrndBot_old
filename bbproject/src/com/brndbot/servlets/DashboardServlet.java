/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.servlets;

import java.io.IOException;
import java.io.PrintWriter;
//import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




//import com.brndbot.block.ChannelEnum;
//import com.brndbot.client.ClientException;
import com.brndbot.client.ClientInterface;
import com.brndbot.client.Model;
import com.brndbot.client.Promotion;
import com.brndbot.promo.Client;
//import com.brndbot.promo.Promotion;
//import com.brndbot.system.Assert;
import com.brndbot.system.SessionUtils;
//import com.brndbot.system.Utils;

/** The purpose of DashboardServlet (which has nothing to do with
 *  any known sense of "dashboard") is to get all the promotion 
 *  prototypes for a given model; in other words, all the instantiations
 *  of the model with promotion-specific data.
 *  
 *  This is called as a Kendo DataSource URL.
 */
public class DashboardServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	final static Logger logger = LoggerFactory.getLogger(DashboardServlet.class);
	
	public DashboardServlet ()
    {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	/**
	 *  The value returned is a JSON array of the promotion prototypes for the model
	 *  specified by the "brndbotcontent" session attribute.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		logger.debug("--------Entering DashboardServlet----------");

		HttpSession session = request.getSession();
		int channel = SessionUtils.getIntSession(session, SessionUtils.CHANNEL_KEY);
		String modelName = SessionUtils.getStringSession(session, SessionUtils.CONTENT_KEY);
		Client client = (Client) SessionUtils.getSessionData(request, SessionUtils.CLIENT);
		if (client == null) {
			client = Client.getClient (session);
			SessionUtils.saveSessionData (request, SessionUtils.CLIENT, client);
		}
		
		if (channel == 0) {
			logger.error ("Channel is zero in DashboardServlet!");
			response.setStatus (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}

		//int max_width = ChannelEnum.UNDEFINED.getDefaultImgWidth();
		logger.debug("Channel is: {}", channel);
		logger.debug("Model name is: {}", modelName);

		// Make a JSON array of the promotion prototypes under this model
		ClientInterface ci = null;
		try {
			ci = client.getClientInterface();
			if (ci == null) {
				logger.error ("Couldn't get ClientInterface");
				response.setStatus (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}
		} catch (Throwable t) {
			logger.error (t.getClass().getName());
			response.setStatus (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
		Model model = client.getModelCollection().getModelByName(modelName);
		Map<String,Promotion> protos = client.getPromotionPrototypes(model);
		logger.debug ("Got {} promotion prototypes", protos.size());
		JSONArray jsonProtos = new JSONArray();
		
		// Assemble JSONized prototypes into a list, giving each a unique ID
		int id = 1;
		try {
			for (Promotion proto : protos.values()) {
				JSONObject jproto = proto.toJSON();
				jproto.put ("ID", id++);
				jproto.put ("protoName", proto.getName());
				jsonProtos.put (jproto);
			}
		} catch (JSONException e) {
			logger.error ("Error getting promo prototypes: {}", e.getClass().getName());
		}
		String jsonStr = jsonProtos.toString();

		if (jsonStr.length() > 0)
		{
			logger.debug ("Returning JSON: " + jsonStr);
	        response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(jsonStr);
			out.flush();
			response.setStatus(HttpServletResponse.SC_OK);
		}
		else
		{
			logger.error("Unknown BlockType: " + modelName);
			response.setStatus (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
