/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */


package com.brndbot.user;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.db.DbConnection;
import com.brndbot.db.DbUtils;

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
public class Organization {

	final static Logger logger = LoggerFactory.getLogger(Organization.class);

	private Integer _id;
	private String _name;
	private String _authCode;
	
	/** How do we do this? If auth codes are hashed, can't do a straightforward
	 *  lookup. Auth codes are widely distributed anyway, so performance may
	 *  be more important than this degree of security.
	 */
	public static Organization getByAuthCode(String authCode) {
		return null;		// TODO stub
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
