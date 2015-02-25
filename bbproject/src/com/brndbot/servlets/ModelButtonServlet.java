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
import com.brndbot.system.SessionUtils;
import com.brndbot.system.SystemProp;
import com.brndbot.system.Utils;

/**
 * This servlet generates image streams for category and model buttons.
 * It can have a parameter of category=catname or model=modelname
 */
public class ModelButtonServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	final static Logger logger = LoggerFactory.getLogger(ModelButtonServlet.class);

	public ModelButtonServlet ()
    {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug ("Entering ModelButtonServlet");
		HttpSession session = request.getSession();
		int userId = SessionUtils.getUserId(session);
		String category = Utils.getStringParameter(request, "category");
		String model = null;
		if (category == null) {
			model = Utils.getStringParameter(request, "model");
		}
		if (category != null) {
			returnCategoryButton (request, response, userId, category);
		} else if (model != null) {
			returnModelButton (request, response, userId, model);
		} else  {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
	}
	
	// The only difference between returnCategoryButton and ReturnModelButton is
	// how the path is obtained. TODO Refactor.
	private void returnCategoryButton 
			(HttpServletRequest request, HttpServletResponse response, int userId, String category) {
		final int bufferSize = 4096;
		String modelBase = SystemProp.get(SystemProp.LOCAL_ASSETS);
		StringBuilder path = new StringBuilder (modelBase);
		path.append ("/models/");
		path.append (getOrganizationDir(userId));
		path.append ("/");
		path.append (category);
		path.append ("__.png");
		logger.debug ("Getting button image from {}", path);
		FileInputStream inputStream = null;
		ServletOutputStream out = null;
		try {
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

	private void returnModelButton 
			(HttpServletRequest request, HttpServletResponse response, int userId, String model) {
		response.setStatus (HttpServletResponse.SC_NOT_FOUND);	// TODO stub
	}
	
	private String getOrganizationDir (int userId) {
		DbConnection con = DbConnection.GetDb();
		User user = new User(userId);
		user.loadClientInfo(con);
		int orgId = user.getOrganizationID();
		Organization org = Organization.getById(orgId);
		return org.getDirectoryName();
	}

}
