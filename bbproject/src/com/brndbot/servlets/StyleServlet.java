package com.brndbot.servlets;

import com.brndbot.client.style.StyleSet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		logger.debug ("Entering StyleServlet");
		
		/* TODO Generalize to all styles 
		 * This version will deliver just one styleset from a fixed location
		 * for initial testing.
		 */
		StyleSet styleSet = new StyleSet ("dummy");
		
		String jsonStr = null;
		try {
			JSONArray jsonStyles = new JSONArray();
			JSONObject jstyle = styleSet.toJSON();
			jsonStyles.put (jstyle);		
			jsonStr = jsonStyles.toString ();
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
