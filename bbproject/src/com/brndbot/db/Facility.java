package com.brndbot.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Facility implements TableModel {

	final static Logger logger = LoggerFactory.getLogger(Facility.class);
	final static String tableName = "facilities";

	@Override
	public String getTableName() {
		return tableName;
	}

	public static JSONArray getAllFacilities () {
		String sql = "SELECT * FROM ltfacilitytype order by FacilityType;";
		DbConnection con = DbConnection.GetDb();
		Statement stmt = con.createStatement();
		ResultSet rs = con.QueryDB(sql, stmt);
		JSONArray json_array = new JSONArray();

        try 
		{
        	int i = 0;
        	while (rs.next())
			{
				JSONObject json_obj = new JSONObject();
				json_obj.put("FacilityID", rs.getInt(1));
				json_obj.put("FacilityType", rs.getString(2));
				json_array.put(i++, json_obj);
			}
        	return json_array;
		} 
		catch (SQLException e) 
		{
			logger.error("Exception: {}", e.getMessage());
			e.printStackTrace();
			return null;
		} 
		catch (JSONException e1)
		{
			logger.error("Exception: {}", e1.getMessage());
			e1.printStackTrace();
			return null;
		}
		finally
		{
			DbUtils.close(stmt, rs);
			con.close();
		}
	}
}
