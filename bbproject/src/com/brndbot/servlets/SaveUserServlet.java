/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.db.DbConnection;
import com.brndbot.db.User;
import com.brndbot.system.SessionUtils;
import com.brndbot.system.Utils;
import com.brndbot.util.PWHash;

public class SaveUserServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	final static Logger logger = LoggerFactory.getLogger(SaveUserServlet.class);
	
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
		logger.debug("--------Entering SaveUserServlet----------");

		DbConnection con = DbConnection.GetDb();

		// Gather data
		String userEmail = Utils.getStringParameter(request, "hiddenEmail").toLowerCase();
		String userPassword = Utils.getStringParameter(request, "hiddenPassword");
		String hashedPassword = PWHash.getHash(userPassword);

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
		String orgId = Utils.getStringParameter(request,  "hiddenOrgId");

		HttpSession session = request.getSession();

		// Add record to database
		User user = new User();
		user.setEmail(userEmail);
		user.setHashedPassword(hashedPassword);
		user.setCompanyName(companyName);
		user.setCompanyAddress(companyAddress);
		user.setCompanyURL(companyURL);
		user.setFacebook(facebookURL);
		user.setTwitter(twitterHandle);
		user.setLinkedIn(linkedIn);
		user.setYouTube(youTube);
		user.setInstagram(instagram);
		user.setOrganizationID(Integer.parseInt(orgId));
		user.saveUser (con);
		int user_id = user.getUserID();
		
//		PreparedStatement pstmt = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		try
//		{
//			String sql = "INSERT INTO user " +
//					" (EmailAddress, Password, Company, CompanyAddress, URL, FacebookURL, TwitterHandle, LinkedIn, YouTube, Instagram, orgid) " +
//					" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
//			pstmt = con.createPreparedStatement(sql);
//			pstmt.setString(1, userEmail);
//			pstmt.setString(2, hashedPassword);
//			pstmt.setString(3, companyName);
//			pstmt.setString(4, companyAddress);
//			pstmt.setString(5, companyURL);
//			pstmt.setString(6, facebookURL);
//			pstmt.setString(7, twitterHandle);
//			pstmt.setString(8, linkedIn);
//			pstmt.setString(9, youTube);
//			pstmt.setString(10, instagram);
//			pstmt.setInt(11, Integer.parseInt(orgId));
//			pstmt.executeUpdate();
//			// Get the new user ID
//			sql = "select LAST_INSERT_ID() FROM user;";
//			stmt = con.createStatement();
//			rs = con.QueryDB(sql, stmt);
//			if (rs.next())
//			{
//				user_id = rs.getInt(1);
//			}
//			logger.debug("USER_ID: " + user_id);
//			if (user_id == 0)
//			{
//				throw new RuntimeException("User ID is zero after save!!");
//
//			}
//			rs.close();
//			stmt.close();
//		}
//		catch (Exception e)
//		{
//			logger.error("EXCEPTION: {}    {}", e.getClass().getName(), e.getMessage());
//			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//			session.removeAttribute(SessionUtils.USER_ID);
//			return;
//		}
//		finally
//		{
//			pstmt = null;
//			con.close();
//		}
		logger.debug("Saved user_id: " + user_id);
		session.setAttribute(SessionUtils.USER_ID, "" + user_id);
		response.setStatus(HttpServletResponse.SC_OK);
		return;
	}
}
