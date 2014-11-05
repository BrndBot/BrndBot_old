package com.brndbot.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.brndbot.db.DbConnection;
import com.brndbot.db.DbUtils;
import com.brndbot.system.SessionUtils;
import com.brndbot.system.Utils;

public class GetFacilitiesServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

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
		System.out.println("--------Entering GetFacilitiesServlet----------");

		HttpSession session = request.getSession();
		int user_id = Utils.getIntSession(session, SessionUtils.USER_ID);
		if (user_id == 0)
		{
			System.out.println("USER NOT LOGGED IN, SENDING TO LOGIN PAGE");
			response.sendRedirect("index.jsp");
			return;
		}

		String sql = "SELECT * FROM ltfacilitytype order by FacilityType;";
		DbConnection con = DbConnection.GetDb();
		Statement stmt = con.createStatement();
		ResultSet rs = con.QueryDB(sql, stmt);
		JSONArray json_array = new JSONArray();

        try 
		{
        	int i = 0;
        	while (rs.next())
			{
				JSONObject json_obj = new JSONObject();
				json_obj.put("FacilityID", rs.getInt(1));
				json_obj.put("FacilityType", rs.getString(2));
				json_array.put(i++, json_obj);
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		} 
		catch (JSONException e1)
		{
			System.out.println("Exception: " + e1.getMessage());
			e1.printStackTrace();
		}
		finally
		{
			DbUtils.close(stmt, rs);
			con.close();
		}

        response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(json_array);
		out.flush();
		response.setStatus(HttpServletResponse.SC_OK);
		return;
	}
}
