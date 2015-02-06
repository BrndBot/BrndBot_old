package com.brndbot.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.brndbot.db.DbConnection;
import com.brndbot.db.Image;
import com.brndbot.db.MimeTypedInputStream;
//import com.brndbot.system.LoginCookie;
import com.brndbot.system.SessionUtils;

public class ImageServlet extends HttpServlet {


	/** This servlet looks in the database for an image with a 
	 *  specified ID that belongs to the user, and returns it
	 *  with the indicated MIME type. The "img" parameter specifies
	 *  the database ID of the image.
	 *  
	 *  An image may be stored in the database as a mediumblob, or its
	 *  "name" may be a relative path. Only images in the database
	 *  can be accessed by this servlet. This
	 *  gives it a maximum size of 16 megabytes.
	 */
	private static final long serialVersionUID = 1L;

	public ImageServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		final int bufferSize = 2048;
		HttpSession session = request.getSession();
		int userId = SessionUtils.getUserId(session);
		String imageIdStr = (String)request.getParameter("img"); 
		int imageId = 0;
		try {
			imageId = Integer.parseInt(imageIdStr);
		}
		catch (NumberFormatException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		DbConnection con = DbConnection.GetDb();

		MimeTypedInputStream imgStream = Image.getImageStream(userId, imageId, con);
		if (imgStream == null) {
			con.close();
			response.setStatus (HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		response.setContentType (imgStream.getMimeType());
		ServletOutputStream out = response.getOutputStream();
		byte[] buffer = new byte[bufferSize];
		for (;;) {
			int len = imgStream.read(buffer);
			if (len == 0)
				break;
			out.write (buffer, 0, len);
		}
		imgStream.close();
		con.close();
		out.flush ();
		response.setStatus (HttpServletResponse.SC_OK);
	}
}
