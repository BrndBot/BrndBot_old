/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.db;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.db.User;
import com.brndbot.util.PWHash;

public class User implements TableModel
{
	
	final static Logger logger = LoggerFactory.getLogger(User.class);
	final static String tableName = "user";
	
	private Integer userId;
	private String _email;
	private String hashedPassword;
	private String companyName;
	private String companyAddress;
	private String companyUrl;
	private String logoName;
	private Integer orgId;
	private Integer personalityId;
	private String facebook;
	private String twitter;
	private String clientClass;
//	private String _linked_in;
//	private String _you_tube;
//	private String _instagram;
	
	public User () {
		
	}
	
	public User(int user_id)
	{
		userId = new Integer(user_id);
	}
	
	public String getTableName () {
		return tableName;
	}

	public Integer getUserID() {
		return userId;
	}

	public void setUserID(int user_id) {
		this.userId = new Integer(user_id);
	}

	
	public void setEmail (String email) {
		this._email = email;
	}
	
	public void setHashedPassword (String hashed_pw) {
		hashedPassword = hashed_pw;
	}
	
	public void setFacebook (String fbook) {
		facebook = fbook;
	}
	
	public void setTwitter (String twt) {
		twitter = twt;
	}
	
//	public void setYouTube (String you_tube) {
//		_you_tube = you_tube;
//	}
//	
//	public void setLinkedIn (String linked_in) {
//		_linked_in = linked_in;
//	}
	
//	public void setInstagram (String instagram) {
//		_instagram = instagram;
//	}
	
	/** Returns the full name of the class, which must be 
	 *  an implementation of ClientInterface, that the user's
	 *  organization gets its data from
	 */
	public String getClientClass () {
		if (clientClass != null) {
			return clientClass;
		} else {
			return null;		// TODO stub need to query organization
		}
	}
	
	/** Loads up information needed by Client. Getters can then
	 *  be called on organization id and personality id.
	 */
	public void loadClientInfo (DbConnection con) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			// Get a Statement object
			String sql = "SELECT orgid,personalityid FROM user WHERE UserID = ?;";
			pstmt = con.createPreparedStatement(sql);
			pstmt.setInt(1, userId);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				orgId = rs.getInt(1);
				personalityId = rs.getInt (2);
			} else {
				logger.error ("loadClientInfo: No result found for user ID {}", userId);
			}
		}
		catch (SQLException e)
		{
			logger.error("Exception in loadClientInfo: {}", e.getClass().getName());
			e.printStackTrace();
		}
		finally
		{
			if (pstmt != null)
			{
				try 
				{
					pstmt.close();
				} catch (SQLException e) {
					logger.error("Error closing: {}", e.getClass().getName());
					e.printStackTrace();
				}
			}
			DbUtils.close(rs);
		}
	
	}
	
	public Integer getOrganizationID() {
		return orgId;
	}
	
	public void setOrganizationID(int org_id) {
		this.orgId = new Integer(org_id);
	}
	
	public void setPersonalityID(int pers_id) {
		this.personalityId = pers_id;
	}
	
	public Integer getPersonalityID() {
		return personalityId;
	}
	

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String company_name) {
		this.companyName = company_name;
	}
	
	public void setCompanyAddress(String company_address) {
		this.companyAddress = company_address;
	}
	
	public void setCompanyURL (String company_url) {
		this.companyUrl = company_url;
	}

	public String getLogoName() {
		return logoName;
	}

	public void setLogoName(String logo_name) {
		this.logoName = logo_name;
	}

	public void saveUser (DbConnection con) {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		//int user_id = 0;
		try
		{
			String sql = "INSERT INTO user " +
					" (EmailAddress, Password, Company, CompanyAddress, URL, FacebookURL, TwitterHandle, orgid) " +
					" VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
			pstmt = con.createPreparedStatement(sql);
			pstmt.setString(1, _email);
			pstmt.setString(2, hashedPassword);
			pstmt.setString(3, companyName);
			pstmt.setString(4, companyAddress);
			pstmt.setString(5, companyUrl);
			pstmt.setString(6, facebook);
			pstmt.setString(7, twitter);
			pstmt.setInt(8, orgId);
			pstmt.executeUpdate();
			// Get the new user ID
			sql = "select LAST_INSERT_ID() FROM user;";
			stmt = con.createStatement();
			ResultSet rs = con.QueryDB(sql, stmt);
			if (rs.next())
			{
				userId = rs.getInt(1);
			}
			logger.debug("USER_ID: " + userId);
			if (userId == 0)
			{
				throw new RuntimeException("User ID is zero after save!!");
			}
			rs.close();
			stmt.close();
		}
		catch (Exception e) {
			logger.error("Exception in saveUser: {}    {}", e.getClass().getName(), e.getMessage());
			return;
		}
		finally {
			pstmt = null;
			//con.close();
		}
	
	}
	


	static public boolean doesEmailExist(String email, DbConnection con)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean ret = false;
		try
		{
			// Get a Statement object
			String sql = "SELECT UserID FROM user WHERE EmailAddress = ?;";
			pstmt = con.createPreparedStatement(sql);
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();
			ret = (rs.next());
		}
		catch (SQLException e)
		{
			logger.error("Exception in doesEmailExist: {}", e.getClass().getName());
			System.out.println(e);
		}
		finally
		{
			if (pstmt != null)
			{
				try 
				{
					pstmt.close();
				} catch (SQLException e) {
					logger.error("Error closing: {}", e.getClass().getName());
					e.printStackTrace();
				}
			}
			DbUtils.close(rs);
			con.close();
		}
		return ret;
	}


	static public User getUserNameAndLogo(int user_id, DbConnection con)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try 
		{
			String sql = "SELECT a.Company, c.ImageName FROM user as a, userlogo as b, images as c" + 
				" WHERE a.UserID = ? AND a.UserID = b.UserID and b.ImageID = c.ImageID;";
			pstmt = con.createPreparedStatement(sql);
			pstmt.setInt(1, user_id);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				user = new User(user_id);
				user.setCompanyName(rs.getString(1));
				user.setLogoName(rs.getString(2));
			}
			else
				logger.error("Nothing found:\n" + sql + "\nUserID: " + user_id);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbUtils.close(rs);
			DbUtils.close(pstmt);
		}

		return user;
	}
	
	/* Attempt to log in with a user name and password. 
	 * Returns 0 if login is invalid. */
	static public int Login(String email_address, String password, DbConnection con)
    {
    	int user_id = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try 
		{
			pstmt = con.createPreparedStatement("SELECT UserID, Password FROM user WHERE EmailAddress = ?;");
			pstmt.setString(1, email_address);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				String pwHash = rs.getString("Password");
				if (PWHash.validatePassword(password, pwHash))
				{
					user_id = rs.getInt(1);
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		finally
		{
			DbUtils.close(rs);
			DbUtils.close(pstmt);
		}
		return user_id;
    }



	/**
	 * What constitutes a "privileged" user? Is privilege really just a binary state? 
	 */
	static public boolean isPrivileged(int user_id, DbConnection con)
	{
		Statement stmt = con.createStatement();
		String sql = "SELECT IsPriv FROM user WHERE " +
			"UserID = " + user_id + ";";
		ResultSet rs = con.QueryDB(sql, stmt);
		try
		{
			if (rs.next())
			{
				return rs.getBoolean(1);
			}
		}
		catch (SQLException e) 
		{
			logger.error ("Exception in isPrivileged: {}    {}",
					e.getClass().getName(),
					e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			DbUtils.close(stmt, rs);
		}
		return false;
	}


}
