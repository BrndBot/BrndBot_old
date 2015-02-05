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

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.block.ChannelEnum;
import com.brndbot.db.ImageType;
import com.brndbot.system.SessionUtils;
import com.brndbot.system.Utils;

/** How does managing the session from a servlet and serializing everything
 *  as JDOM make sense?  Probably should delete this and replace it with
 *  sane session management. */
public class SetSessionServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	private static final int TIMEOUT_PERIOD = 30 * 60;	// 30 minutes, given in seconds
	
	final static Logger logger = LoggerFactory.getLogger(SetSessionServlet.class);
	
	private static int NEG_ONE = -1;

	public SetSessionServlet ()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	/**
	 *  Security cautions here. To avoid a bootstrapping issue, this can't use AuthenticationFilter.
	 *  It's written so you can set only the image type without being logged in. I don't
	 *  understand why, but I'll leave it that way.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		logger.debug("--------Entering SetSessionServlet----------");

		HttpSession session = request.getSession();
		
		// Check if the duration has been set from the default. If not, set it. 
		int timeout = session.getMaxInactiveInterval();
		if (timeout < TIMEOUT_PERIOD) {
			session.setMaxInactiveInterval (TIMEOUT_PERIOD);
		}
		
		String action = Utils.getStringParameter(request, "action").toLowerCase();

		logger.debug("Session ACTION: " + action);
		if (action.equals(SessionUtils.IMAGE))
		{
			// Set the image type into the session
			int image_id = Utils.getIntParameter(request, SessionUtils.IMAGE_ID_KEY);
			if (image_id < 1)
			{
				session.removeAttribute(SessionUtils.IMAGE_ID_KEY);
			}
			else
			{
				ImageType image_type = ImageType.create(image_id);
				if (image_type == null)
				{
					System.out.println("Invalid image_type: " + image_id);
					throw new RuntimeException("Invalid image_type: " + image_id);
				}
	
				session.setAttribute(SessionUtils.IMAGE_ID_KEY, "" + image_id);
			}

			// Send an empty json reply
			JSONObject json_obj = new JSONObject();
			try
			{
				json_obj.put(SessionUtils.IMAGE_ID_KEY, "" + image_id);
			}
			catch (JSONException e)
			{
				String s = "JSON error returning image_type: " + image_id + "\n" + e.getMessage();
				logger.error(s);
				throw new RuntimeException(s);
			}
	        response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			logger.debug("JSON: {}", json_obj.toString());
			out.println(json_obj);
			out.flush();
			response.setStatus(HttpServletResponse.SC_OK);
			return;
		}

		int user_id = SessionUtils.getIntSession(session, SessionUtils.USER_ID);
		if (user_id == 0)
		{
			//logger.debug("USER NOT LOGGED IN, SENDING TO LOGIN PAGE");
			//response.sendRedirect("index.jsp");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		// See if we're setting the blocks into the session
		if (action.equals(SessionUtils.BLOCKS))
		{
			String json_array = Utils.getStringParameter(request, "hiddenBlocks");
			session.setAttribute(SessionUtils.BLOCKS, json_array);
			JSONObject json_obj = new JSONObject();
			try
			{
//				System.out.println(json_array);
				json_obj.put(SessionUtils.BLOCKS, json_array);
			}
			catch (JSONException e) 
			{
				logger.error("Json exception: {} ", e.getMessage());
				e.printStackTrace();
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			finally
			{
			}

	        response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(json_obj);
			out.flush();
			response.setStatus(HttpServletResponse.SC_OK);
			return;
		}

		if (!action.equals(SessionUtils.SET) && !action.equals(SessionUtils.CLEAR) 
			&& !action.equals(SessionUtils.GET))
		{
			logger.debug("Invalid action: " + action);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		int channel = NEG_ONE;
		String content = "";
		int database_id = NEG_ONE;
		boolean foundOne = false;

		if (action.equals(SessionUtils.CLEAR))
		{
//			System.out.println("CLEARING THE SESSION!");
			session.setAttribute(SessionUtils.CHANNEL_KEY, "" + NEG_ONE);
			session.setAttribute(SessionUtils.CONTENT_KEY, "");
			session.setAttribute(SessionUtils.DATABASE_ID_KEY, "" + NEG_ONE);
			// FUSED_IMAGE_ID_KEY is cleared here, but set directly to session in servlets
			session.setAttribute(SessionUtils.FUSED_IMAGE_ID_KEY, "" + NEG_ONE);
		}
		else if (action.equals(SessionUtils.GET))
		{
			// Fall through but legal
		}
		else  // Set
		{
			// Check channel
			channel = Utils.getIntParameter(request, SessionUtils.CHANNEL_KEY);
			if (channel > 0)
			{
				ChannelEnum ch_enum = ChannelEnum.create(channel);
				System.out.println("Set non-zero channel to: " + channel + ", " + ch_enum.getItemText());
				if (channel != ChannelEnum.EMAIL.getValue() &&
					channel != ChannelEnum.FACEBOOK.getValue() &&
					channel != ChannelEnum.TWITTER.getValue())
				{
					logger.debug("Unexpected channel: " + channel);
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					return;
				}
				session.setAttribute(SessionUtils.CHANNEL_KEY, "" + channel);
				foundOne = true;
			}
			else
			{
				channel = SessionUtils.getIntSession(session, SessionUtils.CHANNEL_KEY);
			}

			// Check content type. In the new world, this is a model name.
			content = Utils.getStringParameter(request, SessionUtils.CONTENT_KEY);
			if (content != null) {
				session.setAttribute(SessionUtils.CONTENT_KEY, "" + content);
				foundOne = true;
			}
//			else
//			{
//				content = Utils.getIntSession(session, SessionUtils.CONTENT_KEY);
//			}

			// Check database id
			/**** THIS SOUNDS SERIOUSLY UNSAFE. DISABLE IT AND SEE WHAT HAPPENS. *****/
			database_id = Utils.getIntParameter(request, SessionUtils.DATABASE_ID_KEY);
			if (database_id > 0)
			{

//				session.setAttribute(SessionUtils.DATABASE_ID_KEY, "" + database_id);
				foundOne = true;
			}
			else
			{
//				System.out.println("Not going to happen Joe, neg_one on the db id");
				database_id = SessionUtils.getIntSession(session, SessionUtils.DATABASE_ID_KEY);
			}
		}

		if (!foundOne && !action.equals(SessionUtils.CLEAR))
		{
			logger.error("Bad args*********\n   channel: " + channel + ", content: " + content + ", database_id: " + database_id);
		}

		channel = SessionUtils.getIntSession(session, SessionUtils.CHANNEL_KEY);
//		content = Utils.getIntSession(session, SessionUtils.CONTENT_KEY);
		database_id = SessionUtils.getIntSession(session, SessionUtils.DATABASE_ID_KEY);
		logger.debug("Get channel: " + channel);
		logger.debug("Get content: " + content);
		logger.debug("Get database_id: " + database_id);

		JSONObject json_obj = new JSONObject();
		try
		{
			json_obj.put(SessionUtils.CHANNEL_KEY, "" + channel);
			json_obj.put(SessionUtils.CONTENT_KEY, "" + content);
//			json_obj.put(SessionUtils.DATABASE_ID_KEY, "" + database_id);
		}
		catch (JSONException e) 
		{
			logger.error("Json exception: " + e.getMessage());
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		finally
		{
		}

        response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(json_obj);
		out.flush();
		response.setStatus(HttpServletResponse.SC_OK);

		return;
	}
}
