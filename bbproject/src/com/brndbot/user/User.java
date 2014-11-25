/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.user;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.db.DbConnection;
import com.brndbot.db.DbUtils;
import com.brndbot.user.User;
import com.brndbot.util.PWHash;

public class User 
{
	
	final static Logger logger = LoggerFactory.getLogger(User.class);

	private Integer _user_id;
	private String _company_name;
	private String _logo_name;
	private Integer _org_id;
	
	public User(int user_id)
	{
		_user_id = new Integer(user_id);
	}

	public Integer getUserID() {
		return _user_id;
	}

	public void setUserID(int _user_id) {
		this._user_id = new Integer(_user_id);
	}
	
	public Integer getOrganizationID() {
		return _org_id;
	}
	
	public void setOrganizationID(int _org_id) {
		this._org_id = new Integer(_org_id);
	}
		

	public String getCompanyName() {
		return _company_name;
	}

	public void setCompanyName(String _company_name) {
		this._company_name = _company_name;
	}

	public String getLogoName() {
		return _logo_name;
	}

	public void setLogoName(String _logo_name) {
		this._logo_name = _logo_name;
	}

	static public void Delete(int user_id, DbConnection con)
			throws SQLException
	{
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

/*    static public User getUser (int user_id)
    {
    	Integer uID = new Integer(user_id);
		User user = User.getUserByID(user_id);

		// For now, toss an exception
    	if (recruiter == null)
    	{
    		logger.error("Recruiter is null in RecruiterMemCache: Recruiter ID=" + recruiter_id);
    		return null;
    	}

    	RecruiterTable new_recruiter = new RecruiterTable();
        new_recruiter = recruiter.makeClone();
        return new_recruiter;
    }
*/
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
	
	/* This version assumes a hashed password in the database. */
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

	static public void deletePalettes(int user_id, DbConnection con) 
			throws SQLException
	{
		logger.info("Deleting all palettes");
		String sql = "DELETE FROM palettes";// WHERE UserID = " + user_id + ";";
		con.ExecuteDB(sql, false);
	}
}
