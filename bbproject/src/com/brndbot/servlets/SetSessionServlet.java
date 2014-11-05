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

import com.brndbot.block.BlockType;
import com.brndbot.block.ChannelEnum;
import com.brndbot.system.SessionUtils;
import com.brndbot.system.Utils;
import com.brndbot.user.ImageType;

public class SetSessionServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	private static int NEG_ONE = -1;

	public SetSessionServlet ()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("\n--------Entering SetSessionServlet----------");

		HttpSession session = request.getSession();
		String action = Utils.getStringParameter(request, "action").toLowerCase();

		System.out.println("Session ACTION: " + action);
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
				System.out.println(s);
				throw new RuntimeException(s);
			}
	        response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			System.out.println("JSON: " + json_obj.toString());
			out.println(json_obj);
			out.flush();
			response.setStatus(HttpServletResponse.SC_OK);
			return;
		}

		int user_id = Utils.getIntSession(session, SessionUtils.USER_ID);
		if (user_id == 0)
		{
			System.out.println("USER NOT LOGGED IN, SENDING TO LOGIN PAGE");
			response.sendRedirect("index.jsp");
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
				System.out.println("Json exception: " + e.getMessage());
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
			System.out.println("Invalid action: " + action);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		int channel = NEG_ONE;
		int content = NEG_ONE;
		int database_id = NEG_ONE;
		boolean foundOne = false;

		if (action.equals(SessionUtils.CLEAR))
		{
//			System.out.println("CLEARING THE SESSION!");
			session.setAttribute(SessionUtils.CHANNEL_KEY, "" + NEG_ONE);
			session.setAttribute(SessionUtils.CONTENT_KEY, "" + NEG_ONE);
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
				if (channel != ChannelEnum.EMAIL.getValue().intValue() &&
					channel != ChannelEnum.FACEBOOK.getValue().intValue() &&
					channel != ChannelEnum.TWITTER.getValue().intValue())
				{
					System.out.println("Unexpected channel: " + channel);
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					return;
				}
				session.setAttribute(SessionUtils.CHANNEL_KEY, "" + channel);
				foundOne = true;
			}
			else
			{
				channel = Utils.getIntSession(session, SessionUtils.CHANNEL_KEY);
			}

			// Check content type
			content = Utils.getIntParameter(request, SessionUtils.CONTENT_KEY);
			if (content > 0)
			{
//				BlockType ch_enum = BlockType.create(content);
//				System.out.println("Set non-zero content to: " + content + ", " + ch_enum.getItemText());
				if (content != BlockType.CLASS.getValue().intValue() &&
					content != BlockType.WORKSHOP.getValue().intValue() &&
					content != BlockType.STAFF.getValue().intValue() &&
					content != BlockType.SCHEDULE.getValue().intValue() &&
					content != BlockType.SALE.getValue().intValue())
				{
					System.out.println("Bad block type: " + content);
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					return;
				}
				session.setAttribute(SessionUtils.CONTENT_KEY, "" + content);
				foundOne = true;
			}
			else
			{
				content = Utils.getIntSession(session, SessionUtils.CONTENT_KEY);
			}

			// Check database id
			database_id = Utils.getIntParameter(request, SessionUtils.DATABASE_ID_KEY);
//			System.out.println("RAW DB ID: " + database_id);
			if (database_id > 0)
			{
//				System.out.println("Set non-zero database_id to: " + database_id);

				session.setAttribute(SessionUtils.DATABASE_ID_KEY, "" + database_id);
				foundOne = true;
			}
			else
			{
//				System.out.println("Not going to happen Joe, neg_one on the db id");
				database_id = Utils.getIntSession(session, SessionUtils.DATABASE_ID_KEY);
			}
		}

		if (!foundOne && !action.equals(SessionUtils.CLEAR))
		{
			System.out.println("Bad args*********\n   channel: " + channel + ", content: " + content + ", database_id: " + database_id);
		}

		channel = Utils.getIntSession(session, SessionUtils.CHANNEL_KEY);
		content = Utils.getIntSession(session, SessionUtils.CONTENT_KEY);
		database_id = Utils.getIntSession(session, SessionUtils.DATABASE_ID_KEY);
		System.out.println("Get channel: " + channel);
		System.out.println("Get content: " + content);
		System.out.println("Get database_id: " + database_id);

		JSONObject json_obj = new JSONObject();
		try
		{
			json_obj.put(SessionUtils.CHANNEL_KEY, "" + channel);
			json_obj.put(SessionUtils.CONTENT_KEY, "" + content);
			json_obj.put(SessionUtils.DATABASE_ID_KEY, "" + database_id);
		}
		catch (JSONException e) 
		{
			System.out.println("Json exception: " + e.getMessage());
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
