/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
import com.brndbot.client.Promotion;
import com.brndbot.promo.Client;
//import com.brndbot.promo.Promotion;
import com.brndbot.system.Assert;
import com.brndbot.system.SessionUtils;
//import com.brndbot.system.Utils;

public class DashboardServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	final static Logger logger = LoggerFactory.getLogger(DashboardServlet.class);
	
	public DashboardServlet ()
    {
        super();
        logger.info ("Testing logger");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	/**
	 *  The value returned is a JSON array of the promotion prototypes for the model
	 *  specified by the "type" parameter.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		logger.debug("--------Entering DashboardServlet----------");

		/* In this new world, the type parameter will be the name of a Promotion,
		 * and so a name rather than a number. */
		//String modelName = Utils.getStringParameter(request, "type");
		HttpSession session = request.getSession();
		int channel = SessionUtils.getIntSession(session, SessionUtils.CHANNEL_KEY);
		String modelName = SessionUtils.getStringSession(session, SessionUtils.CONTENT_KEY);
		Client client = (Client) SessionUtils.getSessionData(request, SessionUtils.CLIENT);
		if (client == null) {
			client = Client.getClient (session);
			SessionUtils.saveSessionData (request, SessionUtils.CLIENT, client);
		}
		
		Assert.that(channel != 0, "Channel is zero in DashboardServlet!");

		//int max_width = ChannelEnum.UNDEFINED.getDefaultImgWidth();
		logger.debug("Channel is: {}", channel);
		logger.debug("Model name is: {}", modelName);
		//ChannelEnum channel_enum = ChannelEnum.create(channel);
		//max_width = channel_enum.getDefaultImgWidth();
/*		else
		{
			throw new RuntimeException("Unexpected channel in DashboardServlet, channel id: " + channel);
		}
*/
		// Make a JSON array of the promotion prototypes under this model
		ClientInterface ci = null;
		try {
			ci = client.getClientInterface();
			if (ci == null)
				logger.error ("Couldn't get ClientInterface");
			else
				logger.debug ("Got ClientInterface {}", ci.getClass().getName());
		} catch (Throwable t) {
			logger.error (t.getClass().getName());
		}
		Map<String,Promotion> protos = ci.getPromotionPrototypes(modelName);
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
		System.out.println ("JSON = " + jsonStr);

		if (jsonStr.length() > 0)
		{
	        response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(jsonStr);
			out.flush();
			response.setStatus(HttpServletResponse.SC_OK);
		}
		else
		{
			logger.error("Unknown BlockType: " + modelName);
			throw new RuntimeException("Unknown BlockType: " + modelName);
		}
		return;
	}
}
