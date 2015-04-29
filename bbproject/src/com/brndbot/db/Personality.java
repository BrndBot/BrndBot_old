package com.brndbot.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** The table which defines brand personalities */
public class Personality implements TableModel {

	final static Logger logger = LoggerFactory.getLogger(Personality.class);
	final static String tableName = "personality";
	
	private Integer id;
	private Integer orgId;
	private String name;
	
	public String getTableName () {
		return tableName;
	}
	
	/** Returns the ID of the personality */
	public int getId () {
		return id;
	}
	
	/** Returns the name of the personality */
	public String getName () {
		return name;
	}
	
	/** Returns the ID of the organization to which the personality belongs */
	public int getOrgId () {
		return orgId;
	}
	
	/** Returns the Personality with the given ID, or null. */
	public static Personality getById (int id) {
		PreparedStatement pstmt = null;
		DbConnection con = null;
		ResultSet rs = null;
		try {
			con = DbConnection.GetDb();
			String sql = "SELECT name, orgid FROM personality WHERE id = ?;";
			pstmt = con.createPreparedStatement(sql);
			pstmt.setInt (1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Personality pers = new Personality ();
				pers.id = id;
				pers.name = rs.getString (1);
				pers.orgId = rs.getInt(2);
				return pers;
			}
		}
		catch (Exception e) {
			logger.error ("Error in getPersonalityById, id = {}", id);
			throw new RuntimeException (e);
		}
		return null;
	}
	
	
	/** Returns all Personalities that have a specified organization ID */
	public static List<Personality> getPersonalitiesByOrgId (int orgId) {
		PreparedStatement pstmt = null;
		DbConnection con = null;
		List<Personality> results = new ArrayList<>();
		ResultSet rs = null;
		try {
			con = DbConnection.GetDb();
			String sql = "SELECT id, name FROM personality WHERE orgid = ?;";
			pstmt = con.createPreparedStatement(sql);
			pstmt.setInt (1, orgId);
			rs = pstmt.executeQuery();
			while (rs.next())	{
				Personality pers = new Personality ();
				pers.id = rs.getInt (1);
				pers.name = rs.getString (2);
				pers.orgId = orgId;
				results.add(pers);
			}
		}
		catch (Exception e) {
			logger.error ("Error in getPersonalitiesByOrgId, org = {}", orgId);
			throw new RuntimeException (e);
		}
		finally {
			DbUtils.close(pstmt, rs);
			if (con != null)
				con.close();
		}
		return results;
	}
}
