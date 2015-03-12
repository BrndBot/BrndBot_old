package com.brndbot.servlets;

import com.brndbot.client.ClientInterface;
//import com.brndbot.client.Model;
import com.brndbot.client.style.StyleSet;
import com.brndbot.promo.Client;
import com.brndbot.system.SessionUtils;

import java.io.IOException;
import java.io.PrintWriter;
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

/** This servlet can be called to return the set of available styles 
 *  in JSON form. */
public class StyleServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	final static Logger logger = LoggerFactory.getLogger(StyleServlet.class);
	
	public StyleServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 *  The value returned is a JSON array of the stylesets for the model
	 *  specified by the "brndbotcontent" session attribute.
	 */	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		logger.debug ("Entering StyleServlet");
		
		HttpSession session = request.getSession();
		String modelName = SessionUtils.getStringSession(session, SessionUtils.CONTENT_KEY);
		Client client = (Client) SessionUtils.getSessionData(request, SessionUtils.CLIENT);
		logger.debug ("Client: {}", client);
		/* TODO Generalize to all styles 
		 * This version will deliver just one styleset from a fixed location
		 * for initial testing.
		 */
		//StyleSet styleSet = new StyleSet ("dummy");
		
		// Make a JSON array of the promotion prototypes under this model
		ClientInterface ci = null;
		try {
			ci = client.getClientInterface();
			logger.debug ("Client Interface: {}", ci);
			if (ci == null) {
				logger.error ("Couldn't get ClientInterface");
				response.setStatus (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}
			else
				logger.debug ("Got ClientInterface {}", ci.getClass().getName());
		} catch (Throwable t) {
			logger.error (t.getClass().getName());
			response.setStatus (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
		Map<String, StyleSet> styleSetMap = null;
		try {
			logger.debug ("Calling getStyleSets");
			styleSetMap = client.getStyleSets(modelName);
		} catch (Exception e) {
			logger.error ("Exception getting style sets: {}", e.getClass().getName());
			e.printStackTrace();
			response.setStatus (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
		if (styleSetMap == null || styleSetMap.isEmpty()) {
			logger.error ("No stylesets found for model {}", modelName);
			response.setStatus (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
		logger.debug ("Returned from getStyleSets, got {} stylesets", styleSetMap.size());

		String jsonStr = "";
		try {
			JSONArray jsonStyles = new JSONArray();
			for (StyleSet styleSet : styleSetMap.values()) {
				logger.debug ("Converting styleset to JSON");
				JSONObject jstyle = styleSet.toJSON();
				jsonStyles.put (jstyle);		
				jsonStr = jsonStyles.toString ();
				logger.debug (jsonStr);
			}
		}
		catch (JSONException e) {
			logger.error ("JSONException in StyleServlet: {}", e.getMessage ());
			response.setStatus (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
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
			logger.error("Error converting stylesets to JSON");
			response.setStatus (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}
