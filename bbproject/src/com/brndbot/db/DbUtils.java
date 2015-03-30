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

public class DbUtils 
{
	static public void close(Statement stmt)
	{
		if (stmt != null)
		{
			try 
			{
				stmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
				throw new RuntimeException("Exception closing Statement in DbUtils, error: " + e.getMessage());
			}
			stmt = null;
		}
	}

	static public void close(PreparedStatement pstmt)
	{
		if (pstmt != null)
		{
			try 
			{
				pstmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
				throw new RuntimeException("Exception closing PreparedStatement in DbUtils, error: " + e.getMessage());
			}
			pstmt = null;
		}
	}

	static public void close(ResultSet rs)
	{
		if (rs != null)
		{
			try 
			{
				rs.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
				throw new RuntimeException("Exception closing ResultSet in DbUtils, error: " + e.getMessage());
			}
			rs = null;
		}
	}

	/** Close a Statement and a ResultSet. Null-safe.
	 */
	static public void close(Statement stmt, ResultSet rs)
	{
		close(rs);
		close(stmt);
	}

	/** Close a PreparedStatement and a ResultSet. Null-safe.
	 */
	static public void close(PreparedStatement pstmt, ResultSet rs)
	{
		close(rs);
		close(pstmt);
	}

	static public int getLastInsertID(DbConnection con) 
		throws SQLException
	{
		Statement stmt = con.createStatement();
		ResultSet rs = con.QueryDB("SELECT LAST_INSERT_ID();", stmt);
		int last_id = 0;
		if (rs.next())
		{
			last_id = rs.getInt(1);
			DbUtils.close(stmt, rs);
		}
		else
		{
			stmt.close();
			throw new SQLException("getLastInsertID did not return an ID!"); 
		}
		return last_id;
	}
}
