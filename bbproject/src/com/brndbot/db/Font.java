package com.brndbot.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Font implements TableModel {

	final static Logger logger = LoggerFactory.getLogger(Font.class);
	final static String tableName = "fonts";

//	private int personalityID;
//	private String fontName;
	
	public Font() {
		// TODO Auto-generated constructor stub
	}

	public String getTableName () {
		return tableName;
	}
	
	/* Get all the font names for a brand personality. Fonts with brand personality
	 * 0 (public fonts) are also available. */
	public static List<String> getFontsForPersonality (int persID, DbConnection con) {
		String sql = "SELECT fontname FROM fonts WHERE personality_id = ? OR personality_id = '0' ORDER BY fontname;";
		List<String> fonts = new ArrayList<>();
		PreparedStatement pstmt = null;
		try {
			pstmt = con.createPreparedStatement(sql);
			pstmt.setInt (1, persID);
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next()) {
				fonts.add (rs.getString ("fontname"));
			}
		}
		catch (SQLException e) {
			logger.error("Exception in getFontsForPersonality: {}", e.getClass().getName());
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {}
		}
		return fonts;
	}
}
