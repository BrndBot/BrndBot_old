package com.brndbot.servlets;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.db.DbConnection;
import com.brndbot.db.Image;
import com.brndbot.db.ImageType;
import com.brndbot.system.SessionUtils;


public class SavePromotionServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	final static Logger logger = LoggerFactory.getLogger(SavePromotionServlet.class);

	public SavePromotionServlet() {
        super();
	}
	
	/** Only POST requests are allowed, for multiple obvious reasons */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		int userID = SessionUtils.getIntSession(session, SessionUtils.USER_ID);
		String imgData = request.getParameter ("imgBase64");
		byte[] imgByteData = imgData.getBytes();
		ByteArrayInputStream reqstrm = new ByteArrayInputStream (imgByteData);
		// Discard everything through the first comma.
		int bytesTossed = 0;
		StringBuffer debugStr = new StringBuffer ();
		for (;;) {
			int ch = reqstrm.read();
			debugStr.append ((char) ch);
			if (ch < 0) {
				logger.error ("No valid data found");
				logger.error ("Debug string: {}", debugStr);
				return;
			}
			++bytesTossed;
			if ((char) ch == ',') {
				logger.debug ("Tossed {} bytes out", bytesTossed);
				break;
			}
		}
		// Now start decoding from the next byte.
		InputStream instrm = new Base64InputStream(reqstrm);
		
		// Load the data into a byte array.
		ByteArrayOutputStream baos = new ByteArrayOutputStream ();
		try {
			byte[] buf = new byte[2048];
			for (;;) {
				int bytesRead = instrm.read(buf);
				logger.debug ("Read {} bytes", bytesRead);
				if (bytesRead <= 0)
					break;
				baos.write (buf, 0, bytesRead);
			}
		}
		catch (Exception e) {
			logger.error ("Error reading bytes in memory??");
		}
		finally {
			instrm.close();
		}
		byte[] imgBytes = baos.toByteArray();
		
		// Have to run it through an input stream again to make a
		// BufferedImage, unfortunately.
		ByteArrayInputStream bais = new ByteArrayInputStream (imgBytes);
		BufferedImage bimg = ImageIO.read (bais);
		
		Image img = new Image ();
		img.setUserID(userID);
		img.setImageName("Saved promotion");
		img.setImageSize(imgBytes.length);
		img.setImageType (ImageType.FUSED_IMAGE);
		img.setImageWidth (bimg.getWidth());
		img.setImageHeight (bimg.getHeight());
		img.setMimeType("application/jpeg");
		
		DbConnection con = DbConnection.GetDb();
		try {
			Image.deleteFusedImages(userID, con);		// delete old presentation
			logger.debug ("saving image");
			img.setImage(imgBytes);
			logger.debug ("Image data: {}", img.toString());
			img.save (con);
		}
		catch (SQLException e) {
			logger.error ("SQLException: {}", e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		finally {
			con.close();
		}
	}

	
	protected void doPostOld(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		InputStream reqstrm = request.getInputStream();
		// Discard everything through the first comma.
		int bytesTossed = 0;
		StringBuffer debugStr = new StringBuffer ();
		for (;;) {
			int ch = reqstrm.read();
			debugStr.append ((char) ch);
			if (ch < 0) {
				logger.error ("No valid data found");
				logger.error ("Debug string: {}", debugStr);
				return;
			}
			++bytesTossed;
			if ((char) ch == ',') {
				logger.debug ("Tossed {} bytes out", bytesTossed);
				break;
			}
		}
		// Now start decoding from the next byte.
		InputStream instrm = new Base64InputStream(reqstrm);
		try {
			byte[] buf = new byte[2048];
			for (;;) {
				int bytesRead = instrm.read(buf);
				logger.debug ("Read {} bytes", bytesRead);
				if (bytesRead <= 0)
					break;
				StringBuilder firstFewBytes = new StringBuilder();
				firstFewBytes.append (Integer.toString (buf[0]));
				firstFewBytes.append (" ");
				firstFewBytes.append (Integer.toString (buf[1]));
				firstFewBytes.append (" ");
				firstFewBytes.append (Integer.toString (buf[2]));
				firstFewBytes.append (" ");
				firstFewBytes.append (Integer.toString (buf[3]));
				logger.debug ("First few bytes: {}", firstFewBytes.toString());
			}
		}
		finally {
			instrm.close ();
		}
	}

}
