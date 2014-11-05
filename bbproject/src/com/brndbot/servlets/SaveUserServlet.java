package com.brndbot.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.brndbot.db.DbConnection;
import com.brndbot.system.SessionUtils;
import com.brndbot.system.Utils;

public class SaveUserServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public SaveUserServlet ()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("--------Entering SaveUserServlet----------");

		DbConnection con = DbConnection.GetDb();

		// Gather data
		String userEmail = Utils.getStringParameter(request, "hiddenEmail").toLowerCase();
		String userPassword = Utils.getStringParameter(request, "hiddenPassword");
		
		if (!userPassword.equals("mbdemo"))
		{
			System.out.println("Wrong password for signup!");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

//		String userConfirmPassword = Utils.getStringParameter(request, "userConfirmPassword");
		String companyName = Utils.getStringParameter(request, "hiddenCompany");
//		String companyFacilities = Utils.getStringParameter(request, "hiddenFacilities");
		String companyAddress = Utils.getStringParameter(request, "hiddenAddress");
		String companyURL = Utils.getStringParameter(request, "hiddenUrl").toLowerCase();
		String facebookURL = Utils.getStringParameter(request, "hiddenFacebookUrl").toLowerCase();
		String twitterHandle = Utils.getStringParameter(request, "hiddenTwitterHandle");
		String linkedIn = Utils.getStringParameter(request, "hiddenLinkedIn");
		String youTube = Utils.getStringParameter(request, "hiddenYouTube");
		String instagram = Utils.getStringParameter(request, "hiddenInstagram");

		HttpSession session = request.getSession();

		// Add record to database
		PreparedStatement pstmt = null;
		Statement stmt = null;
		int user_id = 0;
		ResultSet rs = null;
		try
		{
			String sql = "INSERT INTO user (EmailAddress, Password, Company, CompanyAddress, URL, FacebookURL, TwitterHandle, LinkedIn, YouTube, Instagram) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			pstmt = con.createPreparedStatement(sql);
			pstmt.setString(1, userEmail);
			pstmt.setString(2, userPassword);
			pstmt.setString(3, companyName);
			pstmt.setString(4, companyAddress);
			pstmt.setString(5, companyURL);
			pstmt.setString(6, facebookURL);
			pstmt.setString(7, twitterHandle);
			pstmt.setString(8, linkedIn);
			pstmt.setString(9, youTube);
			pstmt.setString(10, instagram);
			pstmt.executeUpdate();
			// Get the new user ID
			sql = "select LAST_INSERT_ID() FROM user;";
			stmt = con.createStatement();
			rs = con.QueryDB(sql, stmt);
			if (rs.next())
			{
				user_id = rs.getInt(1);
			}
			System.out.println("USER_ID: " + user_id);
			if (user_id == 0)
			{
				throw new RuntimeException("User ID is zero after save!!");
/*				sql = "select UserID from user ORDER BY UserID desc limit 1";
				System.out.println("Faking the user id mech");
				Statement stmt3 = null;
				stmt3 = con.createStatement();
				ResultSet rs3 = con.QueryDB(sql, stmt3);
				if (rs3.next())
				{
					user_id = rs3.getInt(1);
				}
				rs3.close();
				stmt3.close();
*/
			}
			rs.close();
			stmt.close();
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION: " + e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			session.removeAttribute(SessionUtils.USER_ID);
			return;
		}
		finally
		{
			pstmt = null;
			con.close();
		}
		System.out.println("Saving user_id: " + user_id);
		session.setAttribute(SessionUtils.USER_ID, "" + user_id);
		response.setStatus(HttpServletResponse.SC_OK);
		return;
	}
}
