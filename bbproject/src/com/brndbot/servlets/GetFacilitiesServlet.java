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
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.db.Facility;

/** What ARE facilities??? Does anyone use this servlet?? */
public class GetFacilitiesServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	final static Logger logger = LoggerFactory.getLogger(GetFacilitiesServlet.class);
	
	public GetFacilitiesServlet ()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		logger.debug("--------Entering GetFacilitiesServlet----------");

//		HttpSession session = request.getSession();

//		String sql = "SELECT * FROM ltfacilitytype order by FacilityType;";
//		DbConnection con = DbConnection.GetDb();
//		Statement stmt = con.createStatement();
//		ResultSet rs = con.QueryDB(sql, stmt);
//		JSONArray json_array = new JSONArray();
//
//        try 
//		{
//        	int i = 0;
//        	while (rs.next())
//			{
//				JSONObject json_obj = new JSONObject();
//				json_obj.put("FacilityID", rs.getInt(1));
//				json_obj.put("FacilityType", rs.getString(2));
//				json_array.put(i++, json_obj);
//			}
//		} 
//		catch (SQLException e) 
//		{
//			System.out.println("Exception: " + e.getMessage());
//			e.printStackTrace();
//		} 
//		catch (JSONException e1)
//		{
//			System.out.println("Exception: " + e1.getMessage());
//			e1.printStackTrace();
//		}
//		finally
//		{
//			DbUtils.close(stmt, rs);
//			con.close();
//		}
		JSONArray json_array = Facility.getAllFacilities();
		if (json_array == null) {
	        response.setContentType("application/json; charset=UTF-8");
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} else {
	        response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(json_array);
			out.flush();
			response.setStatus(HttpServletResponse.SC_OK);
		}
		return;
	}
}
