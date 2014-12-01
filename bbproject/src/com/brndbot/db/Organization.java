/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */


package com.brndbot.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Representation of an organization as stored in the database.
 * 
 *  Fields in Organization table:
 *  	ID
 *  	Organization name
 *  	auth code
 *  
 *  Add to user table:
 *  	Organization ID
 *  	
 */
public class Organization implements TableModel {

	final static Logger logger = LoggerFactory.getLogger(Organization.class);
	final static String tableName = "user";

	private Integer _id;
	private String _name;
	private String _authCode;
	
	public String getTableName () {
		return tableName;
	}

	public static Organization getByAuthCode(String authCode) {
		Organization org = null;
		DbConnection con = DbConnection.GetDb();
		String sql = "SELECT id, Name FROM organization WHERE Authcode = ?;";
		PreparedStatement pstmt = con.createPreparedStatement(sql);
		ResultSet rs = null;
		try
		{
			pstmt.setString (1, authCode);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				org = new Organization();
				org.setAuthCode(authCode);
				org.setOrganizationID(rs.getInt(1));
				org.setName(rs.getString(2));
			}
		} 
		catch (SQLException e) 
		{
			logger.error ("Error in getByAuthCode query, authCode = {}", authCode);
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		finally
		{
			DbUtils.close(pstmt, rs);
		}
		return org;
	}
	
	public void setOrganizationID (int id) {
		_id = id;
	}
	
	public Integer getOrganizationID () {
		return _id;
	}
	
	public void setName (String name) {
		_name = name;
	}
	
	public String getName () {
		return _name;
	}
	
	public void setAuthCode (String authCode) {
		_authCode = authCode;
	}
	
	public String getAuthCode () {
		return _authCode;
	}
	
	/* Create an Organization in the database. Both the name and authcode
	 * must be non-null.
	 */
	public int save(DbConnection con) throws SQLException
	{
		logger.debug("Saving organization");
		PreparedStatement pstmt = con.createPreparedStatement("INSERT INTO organization (" +
				"Name, Authcode) " +
				"VALUES (?, ?);");
		pstmt.setString (1, _name);
		pstmt.setString (2, _authCode);
		pstmt.executeUpdate();
		pstmt.close();
		logger.debug ("returning from save");
		return DbUtils.getLastInsertID(con);
	}
}
