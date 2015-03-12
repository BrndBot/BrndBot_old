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

	private Integer id;
	private String name;
	private String directoryName;
	private String authCode;
	private String moduleClass;
	
	public String getTableName () {
		return tableName;
	}

	public static Organization getByAuthCode(String authCode) {
		Organization org = null;
		DbConnection con = DbConnection.GetDb();
		String sql = "SELECT id, Name, Directory FROM organization WHERE Authcode = ?;";
		PreparedStatement pstmt = con.createPreparedStatement(sql);
		ResultSet rs = null;
		try
		{
			pstmt.setString (1, authCode);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				org = new Organization();
				org.authCode = authCode;
				org.id = rs.getInt(1);
				org.name = rs.getString(2);
				org.directoryName = rs.getString(3);
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
			con.close();
		}
		return org;
	}
	
	public static Organization getById (int orgId) {
		Organization org = null;
		DbConnection con = DbConnection.GetDb();
		String sql = "SELECT Name, moduleclass, Directory FROM organization WHERE id = ?;";
		PreparedStatement pstmt = con.createPreparedStatement(sql);
		ResultSet rs = null;
		try
		{
			pstmt.setInt (1, orgId);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				org = new Organization();
				org.id = orgId;
				org.name = rs.getString(1);
				org.moduleClass = rs.getString(2);
				org.directoryName = rs.getString(3);
			} else {
				logger.error ("No organization found for ID {}", orgId);
			}
		} 
		catch (SQLException e) 
		{
			logger.error ("Error in getByid query, id = {}", orgId);
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		finally
		{
			DbUtils.close(pstmt, rs);
			con.close();
		}
		return org;
	}
	
	public void setOrganizationID (int orgid) {
		id = orgid;
	}
	
	public Integer getOrganizationID () {
		return id;
	}
	
	public void setName (String nam) {
		name = nam;
	}
	
	/** Returns the human-readable organization name */
	public String getName () {
		return name;
	}

	/** Returns the name used for organization directories */
	public String getDirectoryName () {
		return directoryName;
	}

	/** getModuleClass should return the name of an implementation
	 *  of ClientInterface */
	public String getModuleClass () {
		return moduleClass;
	}
	
	public void setAuthCode (String code) {
		authCode = code;
	}
	
	public String getAuthCode () {
		return authCode;
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
		pstmt.setString (1, name);
		pstmt.setString (2, authCode);
		pstmt.executeUpdate();
		pstmt.close();
		logger.debug ("returning from save");
		return DbUtils.getLastInsertID(con);
	}
}
