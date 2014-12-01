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
import java.util.ArrayList;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.db.User;
import com.brndbot.util.PWHash;

public class User implements TableModel
{
	
	final static Logger logger = LoggerFactory.getLogger(User.class);
	final static String tableName = "user";
	
	private Integer _user_id;
	private String _email;
	private String _hashed_password;
	private String _company_name;
	private String _company_address;
	private String _company_url;
	private String _logo_name;
	private Integer _org_id;
	private Integer _personality_id;
	private String _facebook;
	private String _twitter;
	private String _linked_in;
	private String _you_tube;
	private String _instagram;
	
	public User () {
		
	}
	
	public User(int user_id)
	{
		_user_id = new Integer(user_id);
	}
	
	public String getTableName () {
		return tableName;
	}

	public Integer getUserID() {
		return _user_id;
	}

	public void setUserID(int user_id) {
		this._user_id = new Integer(user_id);
	}

	
	public void setEmail (String email) {
		this._email = email;
	}
	
	public void setHashedPassword (String hashed_pw) {
		_hashed_password = hashed_pw;
	}
	
	public void setFacebook (String facebook) {
		_facebook = facebook;
	}
	
	public void setTwitter (String twitter) {
		_twitter = twitter;
	}
	
	public void setYouTube (String you_tube) {
		_you_tube = you_tube;
	}
	
	public void setLinkedIn (String linked_in) {
		_linked_in = linked_in;
	}
	
	public void setInstagram (String instagram) {
		_instagram = instagram;
	}
	
	public Integer getOrganizationID() {
		return _org_id;
	}
	
	public void setOrganizationID(int org_id) {
		this._org_id = new Integer(org_id);
	}
	
	public void setPersonalityID(int pers_id) {
		this._personality_id = pers_id;
	}
	
	public Integer getPersonalityID() {
		return _personality_id;
	}
	

	public String getCompanyName() {
		return _company_name;
	}

	public void setCompanyName(String company_name) {
		this._company_name = company_name;
	}
	
	public void setCompanyAddress(String company_address) {
		this._company_address = company_address;
	}
	
	public void setCompanyURL (String company_url) {
		this._company_url = company_url;
	}

	public String getLogoName() {
		return _logo_name;
	}

	public void setLogoName(String logo_name) {
		this._logo_name = logo_name;
	}

	public void saveUser (DbConnection con) {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		//int user_id = 0;
		try
		{
			String sql = "INSERT INTO user " +
					" (EmailAddress, Password, Company, CompanyAddress, URL, FacebookURL, TwitterHandle, LinkedIn, YouTube, Instagram, orgid) " +
					" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			pstmt = con.createPreparedStatement(sql);
			pstmt.setString(1, _email);
			pstmt.setString(2, _hashed_password);
			pstmt.setString(3, _company_name);
			pstmt.setString(4, _company_address);
			pstmt.setString(5, _company_url);
			pstmt.setString(6, _facebook);
			pstmt.setString(7, _twitter);
			pstmt.setString(8, _linked_in);
			pstmt.setString(9, _you_tube);
			pstmt.setString(10, _instagram);
			pstmt.setInt(11, _org_id);
			pstmt.executeUpdate();
			// Get the new user ID
			sql = "select LAST_INSERT_ID() FROM user;";
			stmt = con.createStatement();
			ResultSet rs = con.QueryDB(sql, stmt);
			if (rs.next())
			{
				_user_id = rs.getInt(1);
			}
			logger.debug("USER_ID: " + _user_id);
			if (_user_id == 0)
			{
				throw new RuntimeException("User ID is zero after save!!");
			}
			rs.close();
			stmt.close();
		}
		catch (Exception e)
		{
			logger.error("Exception in saveUser: {}    {}", e.getClass().getName(), e.getMessage());
			return;
		}
		finally
		{
			pstmt = null;
			con.close();
		}
	
	}
	
	
	static public void Delete(int user_id, DbConnection con)
			throws SQLException
	{
		// TODO WHAT!!!!????  This will delete ALL users!!!!
		String sql = "DELETE FROM user";// WHERE UserID = " + user_id + ";";
		con.ExecuteDB(sql, false);
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

	static public ArrayList<Palette> getUserPalette(int user_id, DbConnection con)
	{
		ArrayList<Palette> list = new ArrayList<Palette>();

		String sql = "SELECT Color FROM palettes WHERE UserID = " + user_id + 
			" AND IsSuggested = 0 ORDER BY Sequence;";
		Statement stmt = con.createStatement();
		con.QueryDB(sql, stmt);
		ResultSet rs;
		try 
		{
			rs = stmt.getResultSet();
			if (rs != null)
			{
				while (rs.next())
				{
					list.add(new Palette(rs.getString("Color")));
				}
			}
		} 
		catch (SQLException e) 
		{
			logger.error ("Execption in getUserPalette: {}    {}", 
					e.getClass().getName(), e.getMessage());
			e.printStackTrace();
		}
		if (list.size() == 0)
		{
			logger.debug("APPLYING DEFAULT PALETTE FOR USER_ID: " + user_id);
			// Nothing in the database for this user, return default palette
			list.add(new Palette("#ccc"));
			list.add(new Palette("#ccc"));
			list.add(new Palette("#ff0000"));
			list.add(new Palette("#00ff00"));
			list.add(new Palette("#0000ff"));
			list.add(new Palette("#000000"));
			list.add(new Palette("#000000"));
		}
		return list;
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
