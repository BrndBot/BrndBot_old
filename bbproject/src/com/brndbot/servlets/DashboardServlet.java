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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.brndbot.block.BlockType;
import com.brndbot.block.ChannelEnum;
import com.brndbot.mindbody.MBClass;
import com.brndbot.mindbody.MBPolyException;
import com.brndbot.mindbody.MBPolyFactory;
import com.brndbot.mindbody.MBStaff;
import com.brndbot.mindbody.MBWorkshop;
import com.brndbot.system.Assert;
import com.brndbot.system.SessionUtils;
import com.brndbot.system.Utils;

public class DashboardServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	final static Logger logger = LoggerFactory.getLogger(DashboardServlet.class);
	
	public DashboardServlet ()
    {
        super();
        logger.info ("Testing logger");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("--------Entering DashboardServlet----------");

		int typeOfData = Utils.getIntParameter(request, "type");
		HttpSession session = request.getSession();
		int channel = Utils.getIntSession(session, SessionUtils.CHANNEL_KEY);
		Assert.that(channel != 0, "Channel is zero in DashboardServlet!");

		int max_width = ChannelEnum.UNDEFINED.getDefaultImgWidth().intValue();
		System.out.println("Channel is: " + channel);
		ChannelEnum channel_enum = ChannelEnum.create(channel);
		max_width = channel_enum.getDefaultImgWidth().intValue();
/*		else
		{
			throw new RuntimeException("Unexpected channel in DashboardServlet, channel id: " + channel);
		}
*/
		String jsonStr = "";
		System.out.println("BlockType new: " + typeOfData);
		try
		{
			MBPolyFactory factory = new MBPolyFactory();
			if (typeOfData == BlockType.CLASS.getValue().intValue())
			{
				MBClass mbClass = 
					(MBClass)factory.createMBPoly(BlockType.CLASS, 1);
				mbClass.init();
				jsonStr = mbClass.getDataAsJSON(100, max_width);
			}
			else if (typeOfData == BlockType.WORKSHOP.getValue().intValue())
			{
				MBWorkshop mbWorkshop = 
						(MBWorkshop)factory.createMBPoly(BlockType.WORKSHOP, 1);
				mbWorkshop.init();
				jsonStr = mbWorkshop.getDataAsJSON(100, max_width);
			}
			else if (typeOfData == BlockType.STAFF.getValue().intValue())
			{
				MBStaff mbStaff = 
						(MBStaff)factory.createMBPoly(BlockType.STAFF, 1);
				mbStaff.init();
				jsonStr = mbStaff.getDataAsJSON(100, max_width);
			}
		} 
		catch (MBPolyException e) 
		{
			System.out.println("exception: " + e.getMessage());
			e.printStackTrace();
		}

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
			System.out.println("Unknown BlockType: " + typeOfData);
//			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			throw new RuntimeException("Unknown BlockType: " + typeOfData);
		}
		return;
	}
}
