package com.brndbot.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.brndbot.db.DbConnection;
import com.brndbot.system.SessionUtils;
import com.brndbot.db.Image;

/** ImagesServlet retrieves all images in the database which belong
 *  to a user and are of type USER_UPLOAD. Return the as JSON of
 *  some kind.
 */
public class ImagesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	final static Logger logger = LoggerFactory.getLogger(ImagesServlet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		logger.debug ("Starting ImagesServlet post");
		HttpSession session = request.getSession();
		int userId = SessionUtils.getUserId(session);
		DbConnection con = DbConnection.GetDb();
		List<Image> images = Image.getAllUserImages(userId, con);
		
		JSONArray returnArray = new JSONArray();
		try {
			for (Image img : images) {
				JSONObject item = new JSONObject();
				item.put ("id", img.getImageID());
				returnArray.put (item);
			}
			String jsonStr = returnArray.toString ();
			if (jsonStr.length() > 0)
			{
				// Even if array is zero length, jsonStr should have brackets
		        response.setContentType("application/json; charset=UTF-8");
		        logger.debug (jsonStr);
				PrintWriter out = response.getWriter();
				out.println(jsonStr);
				out.flush();
				response.setStatus(HttpServletResponse.SC_OK);
			}			
			else
			{
				logger.error("Error creating JSON for user images");
				response.setStatus (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}	
		}
		catch (JSONException e) {
			logger.error ("JSON exception creating JSON for user images");
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
