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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		logger.debug("--------Entering new SaveImageServlet----------");

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
				//throw new RuntimeException("Exception uploading logo.  Error message: " + e1.getMessage());
				responseString = "Exception uploading logo: " + e1.getClass().getName();
				return;
			}
	
			HttpSession session = request.getSession();
			int user_id = SessionUtils.getIntSession(session, SessionUtils.USER_ID);
			if (user_id == 0)
			{
				logger.debug("USER NOT LOGGED IN, SENDING TO LOGIN PAGE");
				response.sendRedirect("index.jsp");
				return;
			}
			logger.debug("User ID: " + user_id);
	
			// Make sure the image type is passed
			int type = SessionUtils.getIntSession(session, SessionUtils.IMAGE_ID_KEY);
			if (type == 0)
			{
				logger.error("No IMAGE TYPE passed (type=" + type + "). Programming error.");
				responseString = "Internal error: No image type passed";
				return;
			}
			logger.debug("Image ID Key: " + type);
			ImageType image_type = ImageType.create(type);
			if (image_type == null)
			{
				//throw new RuntimeException("Unexpected image type: " + type);
				responseString = "The image type (" + type + ") is not supported";
				return;
			}
	
			DbConnection con = DbConnection.GetDb();
			
			String return_name = "";  // URL of filename alone to return in json
			String imgTag = ""; // <img> tag to return in json
	
			try
			{
				@SuppressWarnings("rawtypes")
				Hashtable files = data.getFiles();
	//			UserLogo existing_logo = UserLogo.getLogoByUserID(user_id, con);
	//			System.out.println("Existing logo: " + existing_logo);
	//			logo_name = (existing_logo != null && 
	//				existing_logo.getImage().getImageName().length() > 0 ? 
	//					existing_logo.getImage().getImageName() : "");
	
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
					String assets = SystemProp.get(SystemProp.ASSETS);
					return_name = Utils.Slashies(assets + "\\" + image.getImageName());
					logger.debug("JSON RETURN NAME: " + return_name);
					int bounding_height = 0;
					int bounding_width = 0;
					int saved_img_id = 0;
					if (image_type.equals(ImageType.DEFAULT_LOGO))
					{
						logger.debug("We got a LOGO");
						UserLogo user_logo = new UserLogo(user_id, image);
						bounding_height = UserLogo.MAX_BOUNDING_HEIGHT;
						bounding_width = UserLogo.MAX_BOUNDING_WIDTH;
						saved_img_id = user_logo.save(con);
					}
					else if (image_type.equals(ImageType.USER_UPLOAD) ||
							image_type.equals(ImageType.TEACHER_PHOTO) ||
							image_type.equals(ImageType.ALTERNATE_LOGO))
					{
						logger.debug("We got a " + image_type.getItemText());
	
						//Use logo binding size for now, will be replaced
						bounding_height = UserLogo.MAX_BOUNDING_HEIGHT;
						bounding_width = UserLogo.MAX_BOUNDING_WIDTH;
						saved_img_id = image.save(con);
					}
					logger.debug("Saved Image ID = " + saved_img_id);
					imgTag = UserLogo.getBoundImage(
							image.getImageName(), 
							bounding_height, bounding_width, true);
					logger.debug("Returned from getBoundImage");
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
	
			logger.debug("Building json_obj");
			JSONObject json_obj = new JSONObject();
			try
			{
				json_obj.put("imageName", return_name);
				json_obj.put("imgTag", imgTag);
				response.setContentType("application/json; charset=UTF-8");
				
				responseString = json_obj.toString();
				logger.debug("returning serialized json_obj");
			}
			catch (JSONException e) 
			{
				e.printStackTrace();
				logger.error(e.getMessage());
				responseString = "Internal error: " + e.getClass().getName();
			}
		} finally {
			if (responseString != null) {
				PrintWriter out = response.getWriter();
				out.print(responseString);
				out.flush();
				response.setStatus(HttpServletResponse.SC_OK);
			}
		}
	}
}

