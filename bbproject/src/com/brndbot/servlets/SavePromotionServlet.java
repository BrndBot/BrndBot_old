package com.brndbot.servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SavePromotionServlet extends HttpServlet {

	final static Logger logger = LoggerFactory.getLogger(SavePromotionServlet.class);

	public SavePromotionServlet() {
        super();
	}
	
	/** Only POST requests are allowed, for multiple obvious reasons */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		InputStream reqstrm = request.getInputStream();
		// Discard everything through the first comma.
		int bytesTossed = 0;
		for (;;) {
			int ch = reqstrm.read();
			if (ch < 0) {
				logger.error ("No valid data found");
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
