package com.brndbot.jsphelper;

import java.util.List;

import com.brndbot.db.DbConnection;
import com.brndbot.db.Font;
import com.brndbot.db.User;

public class AvailableFontRenderer extends Renderer {

	private int userId;
	
	public AvailableFontRenderer(int userId) {
		this.userId = userId;
	}
	
	/* This will return a string of option elements with the value attribute being
	 * the font name, and the element content a prettified version of the name */
	public String getFragment () {
		User u = new User (userId);
		DbConnection con = DbConnection.GetDb();
		try {
			u.loadClientInfo(con);
			int persId = u.getPersonalityID();
			List<String> fonts = Font.getFontsForPersonality (persId, con);
			StringBuilder sb = new StringBuilder();
			for (String font : fonts) {
				sb.append ("<option value='");
				sb.append (font);
				sb.append ("'>");
				sb.append (prettify(font));
				sb.append ("</option>\n");
			}
			return sb.toString();
		}
		finally {
			con.close();
		}
	}
	
	private String prettify (String f) {
		return f;		// TODO make this do something interesting
	}

}
