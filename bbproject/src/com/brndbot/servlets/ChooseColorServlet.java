/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.system.SystemProp;

/** This class queries for a set of preferred colors by sending a request
 *  to a service. 
 *  
 *  Originally this was done with a JavaScript AJAX call directly to the 
 *  service, but this causes problems with browser cross-site prohibitions.
 */
public class ChooseColorServlet extends HttpServlet {

	private final static Logger logger = LoggerFactory.getLogger(ChooseColorServlet.class);
	private static String phpServerPage;

	public ChooseColorServlet() {
        super();
        phpServerPage = SystemProp.get(SystemProp.PHP_SERVER_PAGE);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		logger.debug("Entering ChooseColorServlet");
		String urlParam = request.getParameter ("name");
		if (urlParam == null) {
			response.setStatus (HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		String urlstr = URLDecoder.decode (urlParam, "UTF-8");
		
		// Need to do some checks on this URL so we can't be used as a
		// proxy for attacks. For now, making sure there are no parms will do.
		if (urlstr.indexOf ('?') > 0) {
			response.setStatus (HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		URL url = new URL(phpServerPage + "?name=" + urlstr);
		logger.debug ("Decoded URL = {}",url.toString());
		try {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection ();
			conn.setConnectTimeout (10000);
			conn.setReadTimeout (20000);
			conn.connect();
			response.setContentType("application/json");
			InputStream instrm = conn.getInputStream();
			OutputStream outstrm = response.getOutputStream();
			final int bufferSize = 4096;
			byte[] buf = new byte[bufferSize];
			for (;;) {
				int bytesRead = instrm.read(buf);
				if (bytesRead <= 0)
					break;
				String s = new String (buf, "UTF-8");
				System.out.println (s);
				outstrm.write (buf, 0, bytesRead);
			}
			outstrm.flush();
			response.setStatus (HttpServletResponse.SC_OK);
		}
		catch (Exception e) {
			response.setStatus (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
	}
}
