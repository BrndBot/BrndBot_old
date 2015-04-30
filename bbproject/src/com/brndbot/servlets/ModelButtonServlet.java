package com.brndbot.servlets;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.db.DbConnection;
import com.brndbot.db.Organization;
import com.brndbot.db.User;
import com.brndbot.system.BrndbotException;
import com.brndbot.system.SessionUtils;
import com.brndbot.system.SystemProp;
import com.brndbot.system.Utils;

/**
 * This servlet generates image streams for category buttons.
 * (Model buttons have been dropped from the spec.)
 * Paramter: category=catname 
 * 			hover=any means to get the hover-state graphic.
 */
public class ModelButtonServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	final static Logger logger = LoggerFactory.getLogger(ModelButtonServlet.class);

	public ModelButtonServlet ()
    {
        super();
    }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug ("Entering ModelButtonServlet");
		HttpSession session = request.getSession();
		int userId = SessionUtils.getUserId(session);
		String category = Utils.getStringParameter(request, "category");
		boolean hover = Utils.getStringParameter(request, "hover").length() > 0;
		//String model = null;
		if (category != null) {
			returnCategoryButton (request, response, userId, category, hover);
		} else  {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
	}
	
	private void returnCategoryButton 
			(HttpServletRequest request, 
					HttpServletResponse response, 
					int userId, 
					String category,
					boolean hover) {
		final int bufferSize = 4096;
		ServletOutputStream out = null;
		FileInputStream inputStream = null;
		String modelBase = SystemProp.get(SystemProp.LOCAL_ASSETS);
		try {
			StringBuilder path = new StringBuilder (modelBase);
			path.append ("/models/");
			path.append (getOrganizationDir(userId));
			path.append ("/");
			path.append (category);
			// Set the name for the normal or hover button
			if (hover)
				path.append("__Hover.png");
			else
				path.append ("__.png");
			logger.debug ("Getting button image from {}", path);
			inputStream = new FileInputStream(path.toString());
			response.setContentType("image/png");
			out = response.getOutputStream();
			byte[] buffer = new byte[bufferSize];
			for (;;) {
				int len = inputStream.read(buffer);
				if (len <= 0)
					break;
				out.write (buffer, 0, len);
			}
			response.setStatus (HttpServletResponse.SC_OK);
		}
		catch (BrndbotException e) {
			logger.error ("BrndbotException in returnCategoryButton: {}", e.getMessage());
		}
		catch (IOException e) {
			logger.debug ("Button image not found");
			response.setStatus (HttpServletResponse.SC_NOT_FOUND);	
			return;
		}
		finally {
			if (inputStream != null) {
				logger.debug ("Closing input stream");
				try {
					inputStream.close();
				} catch (Exception e) {}
			}
			try {
				out.flush ();
			} catch (Exception e) {}
		}
	}

//	private void returnModelButton 
//			(HttpServletRequest request, HttpServletResponse response, int userId, String model) {
//		response.setStatus (HttpServletResponse.SC_NOT_FOUND);	// TODO stub
//	}
	
	private String getOrganizationDir (int userId) throws BrndbotException {
		DbConnection con = null;
		try {
			con = DbConnection.getDb();
			User user = new User(userId);
			user.loadClientInfo(con);
			int orgId = user.getOrganizationID();
			Organization org = Organization.getById(orgId);
			return org.getDirectoryName();
		}
		finally {
			if (con != null)
				con.close();
		}
	}

}
