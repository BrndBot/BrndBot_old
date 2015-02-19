/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** There's no database code for this class?? This looks like a stub.
 *  Add other fields, at least.
 *  
 *   A user has one palette, and a palette has one color.
 *   Seems like an odd kind of palette.
 *   
 *   It looks from the user code as if a palette isn't a palette at all,
 *   but a single color of the palette which is all "palettes" for the
 *   user ID.
 */
public class Palette implements TableModel
{
	final static Logger logger = LoggerFactory.getLogger(Palette.class);
	final static String tableName = "palettes";

	private String _color;
	private Integer _user_id;
	private Integer _sequence;		// whatever a sequence is
	private boolean _is_suggested;
	
	
	public Palette()
	{
		// Set a default color
		_color = "ffffff";
	}
	
	public Palette(String color)
	{
		_color = color;
	}
	
	/** Returns the name of the underlying database table. */
	public String getTableName () {
		return tableName;
	}
	
	public Integer getUserId () {
		return _user_id;
	}
	
	public void setUserId (int userId) {
		_user_id = userId;
	}
	
	public void setIsSuggested (boolean isSuggested) {
		_is_suggested = isSuggested;
	}
	
	public Integer getSequence () {
		return _sequence;
	}
	
	public void setSequence(int sequence) {
		_sequence = sequence;
	}

	public String getColor()
	{
		return _color;
	}
	
	public void setColor(String color) { 
		_color = color; 
	}
	
	/** Write a new Palette to the database */
	public void save(DbConnection con) throws SQLException {
		String sql = "insert into palettes (UserID, Sequence, IsSuggested, Color) values (?, ?, ?, ?)";
//				+ user_id + ", " + i + ", " + (isSuggested ? "1" : "0") + ", \"" + palette[i] + "\");"
		PreparedStatement pstmt = con.createPreparedStatement (sql);
		pstmt.setInt(1, _user_id);
		pstmt.setInt(2, _sequence);
		pstmt.setInt(3, (_is_suggested ? 1 : 0));
		pstmt.setString(4, _color);
		pstmt.execute();
		//ResultSet rs = con.QueryDB(sql, pstmt);
		//rs.close();
	}
	
	
	/** Delete all of a user's palettes */
	static public void deletePalettes(int user_id, DbConnection con) 
			throws SQLException
	{
		logger.info("Deleting all palettes for user");
		String sql = "DELETE FROM palettes WHERE UserID = " + user_id + ";";
		con.ExecuteDB(sql, false);
	}
	
	/** Delete only suggested or non-suggested palettes */
	static public void deletePalettes(int user_id, boolean isSuggested, DbConnection con) 
			throws SQLException
	{
		logger.info("Deleting some palettes for user");
		String sql = "DELETE FROM palettes WHERE UserID = " + user_id + 
				" and IsSuggested = " +
				(isSuggested ? "1" : "0");
		con.ExecuteDB(sql, false);
	}
}
