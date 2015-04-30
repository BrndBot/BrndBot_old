package com.brndbot.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.db.DbConnection;
import com.brndbot.db.Image;
import com.brndbot.db.MimeTypedInputStream;
import com.brndbot.system.BrndbotException;
//import com.brndbot.system.LoginCookie;
import com.brndbot.system.SessionUtils;

public class ImageServlet extends HttpServlet {


	/** This servlet looks in the database for an image with a 
	 *  specified ID that belongs to the user, and returns it
	 *  with the indicated MIME type. The "img" parameter specifies
	 *  the database ID of the image.
	 *  
	 *  If the img parameter is "default", get the default image.
	 *  If the img parameter is "logo", get the user's default logo.
	 *  If the img parameter is "fused", gets the user's fused image
	 *  (composed promotion).
	 *  
	 *  An image may be stored in the database as a mediumblob, or its
	 *  "name" may be a relative path. Only images in the database
	 *  can be accessed by this servlet. This
	 *  gives it a maximum size of 16 megabytes.
	 *  
	 *  If the parameter "meta" is present, it returns JSON with the
	 *  metadata rather than image data.
	 *  
	 */
	private static final long serialVersionUID = 1L;

	final static Logger logger = LoggerFactory.getLogger(ImageServlet.class);

	final static int bufferSize = 2048;


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		logger.debug ("Starting ImageServlet post");
		boolean useDefaultImage = false;
		boolean userLogo = false;
		boolean useFusedImage = false;
		HttpSession session = request.getSession();
		int userId = SessionUtils.getUserId(session);
		String imageIdStr = (String)request.getParameter("img"); 
		int imageId = 0;
		if ("default".equals (imageIdStr)) 
			useDefaultImage = true;
		else if ("logo".equals (imageIdStr))
			userLogo = true;
		else if ("fused".equals (imageIdStr))
			useFusedImage = true;
		else {
			try {
				imageId = Integer.parseInt(imageIdStr);
			}
			catch (NumberFormatException e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
		}
		String metaParam = (String) request.getParameter ("meta");
		if (metaParam != null) {
			try {
				doMetadata (request, response, userId, imageId, userLogo, useDefaultImage, useFusedImage);
			}
			catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}
		}
		else {
			logger.debug ("Getting image, id = {}", imageId);
			doImage (request, response, userId, imageId, userLogo, useDefaultImage, useFusedImage);
		}
	}
	
	private void doImage (HttpServletRequest request, 
			HttpServletResponse response, 
			int userId,
			int imageId, 
			boolean userLogo, 
			boolean useDefaultImage,
			boolean useFusedImage) 
					throws ServletException, IOException {
		DbConnection con = null;
		try {
			con = DbConnection.getDb();
	
			MimeTypedInputStream imgStream;
			if (useDefaultImage) 
				imgStream = Image.getDefaultImageStream (userId, con);
			else if (userLogo)
				imgStream = Image.getLogoImageStream (userId, con);
			else if (useFusedImage)
				imgStream = Image.getFusedImageStream(userId, con);
			else
				imgStream = Image.getImageStream(userId, imageId, con);
			if (imgStream == null) {
				// If we couldn't get anything, try for the global default image.
				imgStream = Image.getGlobalDefaultStream (con);
				if (imgStream == null) {
					logger.error ("Couldn't get image stream");
					con.close();
					response.setStatus (HttpServletResponse.SC_NOT_FOUND);
					return;
				}
			}
			response.setContentType (imgStream.getMimeType());
			
			ServletOutputStream out = response.getOutputStream();
			byte[] buffer = new byte[bufferSize];
			for (;;) {
				int len = imgStream.read(buffer);
				if (len <= 0)
					break;
				out.write (buffer, 0, len);
			}
			imgStream.close();
			out.flush ();
			response.setStatus (HttpServletResponse.SC_OK);
		}
		catch (Exception e) {
			logger.error ("Exception in ImageServlet: {}, {}", e.getClass().getName(), e.getMessage());
			if (con != null)
				con.close();
			response.setStatus (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		finally {
			if (con != null)
				con.close();
		}
	}
	
	private void doMetadata (HttpServletRequest request, 
			HttpServletResponse response, 
			int userId,
			int imageId, 
			boolean userLogo, 
			boolean useDefaultImage,
			boolean useFusedImage) 
					throws ServletException, IOException, BrndbotException {
		DbConnection con = DbConnection.getDb();

		Image image;
		if (useDefaultImage) {
			image = Image.getDefaultImage(userId, con);
		}
		else if (useFusedImage) {
			logger.debug ("Getting metadata for fused image");
			image = Image.getFusedImage(userId, con);
		}
		else {
			// works for both regular images and logos
			logger.debug ("Getting image id = {}", imageId);
			image = Image.getImageByID(imageId, userId, con);
		}
		if (image == null) {
			logger.debug ("Couldn't get image, looking for global default");
			// If we couldn't get anything, try for the global default image.
			image = Image.getGlobalDefaultImage (con);
			if (image == null) {
				logger.error ("Couldn't get image stream");
				con.close();
				response.setStatus (HttpServletResponse.SC_NOT_FOUND);
				return;
			}
		}
		response.setContentType ("application/json");
		
		JSONObject json = new JSONObject ();
		try {
			json.put ("mimeType", image.getMimeType());
			json.put ("width", Integer.toString (image.getImageWidth()));
			json.put ("height", Integer.toString (image.getImageHeight()));
		}
		catch (JSONException e) {
			logger.error ("Error in building JSON metadata");
			response.setStatus (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			con.close();
			return;
		}

		String jsonStr = json.toString();
		logger.debug ("Returning JSON {}", jsonStr);

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
			logger.error("Error creating JSON for image");
			response.setStatus (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		con.close();
	}

}
