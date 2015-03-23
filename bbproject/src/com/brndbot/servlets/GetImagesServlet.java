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
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.db.DbConnection;
import com.brndbot.db.Image;
import com.brndbot.db.ImageType;
import com.brndbot.system.SessionUtils;
//import com.brndbot.system.Utils;

public class GetImagesServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	final static Logger logger = LoggerFactory.getLogger(GetImagesServlet.class);

	public GetImagesServlet ()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	/** This gets a JSON array of references to images 
	 *  that are listed in the database for the user.
	 *  
	 *  The URL parameter brndbotimageid is a small integer representing the kind of image.
	 *  Default is user upload (2).
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		logger.debug("Entering GetImagesServlet");

		HttpSession session = request.getSession();
		int user_id = SessionUtils.getUserId(session);

		ImageType image_type = null;
		String param = request.getParameter("brndbotimageid");
		if (param != null) {
			try {
				int intType = Integer.parseInt (param);
				image_type = ImageType.getByItemNumber(intType);
			}
			catch (Exception e) {}
		}
		if (image_type == null)
			image_type = ImageType.USER_UPLOAD;

		DbConnection con = DbConnection.GetDb();
		JSONArray json_array = null;
		try {
			json_array = Image.getImagesForDisplay(user_id, image_type, con);
		}
		finally {
			con.close();
		}
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String s = json_array.toString();
		out.println(s);
		out.flush();
		response.setStatus(HttpServletResponse.SC_OK);
		return;
	}
}
