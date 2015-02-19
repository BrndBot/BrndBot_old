/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadException;

import com.brndbot.db.DbConnection;
import com.brndbot.db.Image;
import com.brndbot.db.ImageException;
import com.brndbot.db.ImageType;
import com.brndbot.db.UserLogo;
import com.brndbot.system.SessionUtils;
import com.brndbot.system.SystemProp;
import com.brndbot.system.Utils;
//import com.brndbot.util.AppEnvironment;

/** This servlet works with the Kendo upload widget to handle a successful upload. */
public class SaveImageServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	final static Logger logger = LoggerFactory.getLogger(SaveImageServlet.class);
	
	public SaveImageServlet ()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	/**
	 *  POST request to initiate an upload.
	 *  
	 *  Uses Javazoom components
	 *  
	 *  @see http://www.javazoom.net/jzservlets/uploadbean/documentation/api/javazoom/upload/UploadFile.html
	 *  @see http://www.javazoom.net/jzservlets/uploadbean/documentation/api/javazoom/upload/MultipartFormDataRequest.html
	 *  
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		logger.debug("saveImageServlet.doPost");

		MultipartFormDataRequest data;
		String responseString = null;
		try {
			try
			{
				data = new MultipartFormDataRequest(request);
			}
			catch (UploadException e1)
			{
				e1.printStackTrace();
				responseString = "Exception uploading image: " + e1.getClass().getName();
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}
	
			HttpSession session = request.getSession();
			int user_id = SessionUtils.getIntSession(session, SessionUtils.USER_ID);
			if (user_id == 0)
			{
				responseString = "Not logged in";
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}
			logger.debug("User ID: " + user_id);
	
			// Make sure the image type is passed. Try first the URL parameter,
			// then the session variable.
			int type = 0;
			try {
				type = Integer.parseInt(request.getParameter("brndbotimageid"));
			} catch (Exception e) {}
			if (type == 0) {
				type = SessionUtils.getIntSession(session, SessionUtils.IMAGE_ID_KEY);
			}
			if (type == 0)
			{
				logger.error("No IMAGE TYPE passed (type=" + type + "). Programming error.");
				response.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
				return;
			}
			logger.debug("Image ID Key: " + type);
			ImageType image_type = ImageType.getByItemNumber(type);
			if (image_type == null)
			{
				responseString = "The image type (" + type + ") is not supported";
				response.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
				return;
			}
	
			DbConnection con = DbConnection.GetDb();
			
			//String imgTag = ""; // <img> tag to return in json
	
			try
			{
				@SuppressWarnings("rawtypes")
				Hashtable files = data.getFiles();
	
				logger.debug("files.size:  " + files.size());
				Image image = null;
				try {
					image = Image.uploadFile(user_id, image_type, files, con);
				} catch (Exception e) {
					logger.error("Exception uploading file: " + e.getClass().getName());
					responseString = "Internal error: " + e.getClass().getName();
				}
				if (image != null)
				{
					responseString = saveImage (image, image_type, user_id, con);
					response.setContentType("application/json; charset=UTF-8");
					response.setStatus(HttpServletResponse.SC_OK);
				}
				else
				{
					// Something failed, check the tomcat log
					logger.error("Upload of image came back NULL!");
					response.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
					if (responseString == null) {
						responseString = "Internal error: Image upload failed";
					}
					return;
				}
			}
			catch (ImageException e1) 
			{
				logger.error("Exception: " + e1);
				e1.printStackTrace();
				responseString = "Internal error: " + e1.getClass().getName();
				return;
			} catch (SQLException e) 
			{
				logger.error("Exception saving image: " + e.getMessage());
				e.printStackTrace();
				responseString = "Internal error: " + e.getClass().getName();
				return;
			}
			finally
			{
				con.close();
			}
	
		} finally {
			if (responseString != null) {
				PrintWriter out = response.getWriter();
				out.print(responseString);
				out.flush();
			}
		}
	}
	
	private String saveImage (Image image, ImageType image_type, int user_id, DbConnection con) 
				throws ImageException, SQLException {
		// Build the URL for the image
		String urlBase = SystemProp.get(SystemProp.ASSETS);
		int bounding_height = 0;
		int bounding_width = 0;
		int saved_img_id = 0;
		String imgTag = "";
		switch (image_type) {
		case DEFAULT_LOGO:
		
			logger.debug("We got a logo");
			UserLogo user_logo = new UserLogo(user_id, image);
			bounding_height = UserLogo.MAX_BOUNDING_HEIGHT;
			bounding_width = UserLogo.MAX_BOUNDING_WIDTH;
			saved_img_id = user_logo.save(con);
			imgTag = UserLogo.getBoundImage(
					image.getImageUrl(), 
					bounding_height, bounding_width, true);
			break;
		
		case ALTERNATE_LOGO:
		
			logger.debug("We got a " + image_type.getItemText());

			//Use logo binding size for now, will be replaced
			bounding_height = UserLogo.MAX_BOUNDING_HEIGHT;
			bounding_width = UserLogo.MAX_BOUNDING_WIDTH;
			saved_img_id = image.save(con);
			imgTag = UserLogo.getBoundImage(
					image.getImageUrl(), 
					bounding_height, bounding_width, true);
			// The "tag" is an <img> element
			break;
		case USER_UPLOAD:
			saved_img_id = image.save(con);
			// Anything else?
			break;
		}
		logger.debug("Saved Image ID = " + saved_img_id);
		logger.debug("Returned from getBoundImage");

		String return_name = Utils.Slashies(urlBase + "\\" + image.getImageUrl());
		logger.debug("JSON return name: " + return_name);

		logger.debug("Building json_obj");
		JSONObject json_obj = new JSONObject();
		String responseString = "";
		try
		{
			json_obj.put("imageName", return_name);
			json_obj.put("imgTag", imgTag);
			
			responseString = json_obj.toString();
			logger.debug("returning serialized json_obj");
		}
		catch (JSONException e) 
		{
			e.printStackTrace();
			logger.error(e.getMessage());
			responseString = "Internal error: " + e.getClass().getName();
			throw new ImageException (responseString);
		}
		return responseString;
	}
}

