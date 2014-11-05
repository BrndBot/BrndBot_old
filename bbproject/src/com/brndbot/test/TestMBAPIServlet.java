package com.brndbot.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brndbot.block.BlockType;
import com.brndbot.mindbody.MBClass;
import com.brndbot.mindbody.MBPolyException;
import com.brndbot.mindbody.MBPolyFactory;

public class TestMBAPIServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public TestMBAPIServlet ()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("--------Entering TestMBAPIServlet ----------");

		try 
		{
			MBPolyFactory factory = new MBPolyFactory();
			MBClass mbClass = (MBClass)factory.createMBPoly(BlockType.CLASS, 1);
			mbClass.init();
//			System.out.println(mbClass.getDataAsJSON(2));
		} 
		catch (MBPolyException e) 
		{
			e.printStackTrace();
		}
		return;
	}
}
